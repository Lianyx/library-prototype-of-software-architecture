package dao;

import object.po.Role;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static dao.util.Util.*;

public class RoleDaoImpl implements RoleDao {
    private PreparedStatement insertQuery, selectByIdQuery;
    private PreparedStatement insertCategoriesQuery, selectCategoriesByRoleIdQuery;

    public RoleDaoImpl() throws SQLException {
        this.insertQuery = getStatement("INSERT INTO Role (id, maximum, period) VALUES (?, ?, ?);");
        this.selectByIdQuery = getStatement("" +
                "SELECT *\n" +
                "FROM Role\n" +
                "WHERE id = ?;");

        this.insertCategoriesQuery = getStatement("INSERT INTO RoleCategory (roleId, categoryId) VALUES (?, ?);");
    }

    @Override
    public void insert(Role role) throws SQLException {
        voidQuery(insertQuery, role.getType(), role.getMaximum(), role.getDayLimit());

    }

    @Override
    public Role selectById(String id) throws SQLException {
        List<Role> roles = retrieveQuery(Role.class, selectByIdQuery, id);
        return roles.isEmpty() ? null : roles.get(0);
    }
}
