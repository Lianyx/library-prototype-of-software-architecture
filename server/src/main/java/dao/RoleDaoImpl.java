package dao;

import object.po.Category;
import object.po.Role;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static dao.util.Util.*;
import static dao.util.DaoFactory.getService;

public class RoleDaoImpl implements RoleDao {
    private PreparedStatement insertQuery, selectByIdQuery;
    private PreparedStatement insertCategoriesQuery, selectCategoriesByTypeQuery;

    public RoleDaoImpl() throws SQLException {
        this.insertQuery = getStatement("INSERT INTO Role (type, maximum, dayLimit) VALUES (?, ?, ?);");
        this.selectByIdQuery = getStatement("" +
                "SELECT *\n" +
                "FROM Role\n" +
                "WHERE id = ?;");

        this.insertCategoriesQuery = getStatement("INSERT INTO RoleCategory (type, categoryId) VALUES (?, ?);");
        this.selectCategoriesByTypeQuery = getStatement("" +
                "SELECT categoryId\n" +
                "FROM RoleCategory\n" +
                "WHERE type = ?;");
    }

    @Override
    public void insert(Role role) throws SQLException {
        voidQuery(insertQuery, role.getType(), role.getMaximum(), role.getDayLimit());
        for (Category category : role.getCategories()) {
            voidQuery(insertCategoriesQuery, category.getId(), category.getName());
        }
    }

    @Override
    public Role selectById(String id) throws SQLException {
        List<Role> roles = retrieveQuery(Role.class, selectByIdQuery, id);
        if (roles.isEmpty()) {
            return null;
        }
        Role role = roles.get(0);

        ArrayList<String> categoryIds = retrieveQuery(String.class, selectCategoriesByTypeQuery, role.getType());
        CategoryDao service = getService(CategoryDao.class);
        Set<Category> collect = categoryIds.stream().map(s -> {
            try {
                return service.selectById(s);
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }).collect(Collectors.toSet());
        role.setCategories(collect);

        return role;
    }
}
