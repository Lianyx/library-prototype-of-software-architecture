package dao;

import object.enun.Permission;
import object.po.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static dao.util.Util.*;

public class UserDaoImpl implements UserDao {
    private PreparedStatement insertQuery, selectByUsernameQuery;
    private PreparedStatement insertPermissionQuery, selectPermissionsByUsername;

    public UserDaoImpl() throws SQLException {
        this.insertQuery = getStatement("INSERT INTO User (username, password, roleId) VALUES (?, ?, ?);");
        this.selectByUsernameQuery = getStatement("" +
                "SELECT *\n" +
                "FROM User\n" +
                "WHERE username = ?;");

        this.insertPermissionQuery = getStatement("INSERT INTO UserPermission (username, permission) VALUES (?, ?);");
        this.selectPermissionsByUsername = getStatement("" +
                "SELECT permission\n" +
                "FROM UserPermission\n" +
                "WHERE username = ?;");
    }

    @Override
    public void insert(User user) throws SQLException {
        voidQuery(insertQuery, user.getUsername(), user.getPassword(), user.getRole());
        for (Permission permission : user.getPermissions()) {
            voidQuery(insertPermissionQuery, user.getUsername(), permission);
        }
    }

    @Override
    public User selectByUsername(String id) throws SQLException {
        ArrayList<User> users = retrieveQuery(User.class, selectByUsernameQuery, id);
        if (users.isEmpty()) {
            return null;
        }
        User user = users.get(0);

        ArrayList<String> strings = retrieveQuery(String.class, selectPermissionsByUsername, user.getUsername());
        Set<Permission> pms = strings.stream().map(UserDaoImpl::strToPM).collect(Collectors.toSet());
        user.setPermissions(pms);

        return user;
    }

    // 重复代码
    private static Permission strToPM(String str) {
        for (Permission pm : Permission.values()) {
            if (pm.toString().equals(str)) {
                return pm;
            }
        }
        return null;
    }
}
