package dao;

import object.po.Category;
import object.po.Role;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static dao.util.Util.*;
import static dao.util.DaoFactory.getService;

public class RoleDaoImpl implements RoleDao {
    private PreparedStatement insertQuery, selectByIdQuery, selectAllQuery;
    private PreparedStatement insertCategoriesQuery, selectCategoriesByTypeQuery;

    public RoleDaoImpl() throws SQLException {
        this.insertQuery = getStatement("INSERT INTO Role (type, maximum, dayLimit) VALUES (?, ?, ?);");
        this.selectAllQuery = getStatement("select * from Role;");
        this.selectByIdQuery = getStatement("" +
                "SELECT *\n" +
                "FROM Role\n" +
                "WHERE type = ?;");

        this.insertCategoriesQuery = getStatement("INSERT INTO RoleCategory (type, categoryId) VALUES (?, ?);");
        this.selectCategoriesByTypeQuery = getStatement("" +
                "SELECT categoryId as x\n" +
                "FROM RoleCategory\n" +
                "WHERE type = ?;");
    }

    @Override
    public void insert(Role role) throws SQLException {
        voidQuery(insertQuery, role.getType(), role.getMaximum(), role.getDayLimit());
        for (Category category : role.getCategories()) {
            voidQuery(insertCategoriesQuery, role.getType(), category.getId());
        }
    }

    @Override
    public Role selectById(String id) throws SQLException {
        List<Role> roles = retrieveQuery(Role.class, selectByIdQuery, id);
        if (roles.isEmpty()) {
            return null;
        }
        Role role = roles.get(0);
        setCategories(role);
        return role;
    }

    @Override
    public ArrayList<Role> selectAll() throws SQLException {
        ArrayList<Role> roles = retrieveQuery(Role.class, selectAllQuery);
        for (Role role : roles) {
            setCategories(role);
        }
        return roles;
    }

    private void setCategories(Role role) throws SQLException {
        ArrayList<String> categoryIds = retrieveQuery(String.class, selectCategoriesByTypeQuery, role.getType());
        CategoryDao service = getService(CategoryDao.class);

        Set<Category> collect = new HashSet<>();
        for (String cid : categoryIds) {
            Category category = service.selectById(cid);
            collect.add(category);
        }
        role.setCategories(collect);
    }
}
