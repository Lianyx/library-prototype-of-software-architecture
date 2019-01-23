package dao;

import object.enun.Permission;
import object.po.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

import static dao.util.Util.*;

public class UserDaoImpl implements UserDao {
    private PreparedStatement
            insertQuery,
            selectByUsernameQuery,
            selectAllQuery,
            searchQuery,
            updateQuery;
    private PreparedStatement
            insertPermissionQuery,
            selectPermissionsByUsername,
            deletePermissionsQuery;

    public UserDaoImpl() throws SQLException {
        this.insertQuery = getStatement("INSERT INTO User (username, password, role, debt) VALUES (?, ?, ?, ?);");
        this.selectByUsernameQuery = getStatement("" +
                "SELECT *\n" +
                "FROM User\n" +
                "WHERE username = ?;");
        this.selectAllQuery = getStatement("select * from User");
        this.searchQuery = getStatement("select * from User where username like ?");
        this.updateQuery = getStatement("" +
                "UPDATE User\n" +
                "SET password = ?, role = ?, debt = ?\n" +
                "WHERE username = ?");

        this.insertPermissionQuery = getStatement("INSERT INTO UserPermission (username, permission) VALUES (?, ?);");
        this.selectPermissionsByUsername = getStatement("" +
                "SELECT permission as x\n" +
                "FROM UserPermission\n" +
                "WHERE username = ?;");
        this.deletePermissionsQuery = getStatement("" +
                "delete from UserPermission where username = ?");
    }

    @Override
    public void insert(User user) throws SQLException {
        voidQuery(insertQuery, user.getUsername(), user.getPassword(), user.getRole(), user.getDebt());
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
        setPermission(user);
        return user;
    }

    @Override
    public ArrayList<User> selectAll() throws SQLException {
        ArrayList<User> users = retrieveQuery(User.class, selectAllQuery);
        for (User user : users) {
            setPermission(user);
        }
        return users;
    }

    @Override
    public ArrayList<User> search(String keyword) throws SQLException {
        ArrayList<User> users = retrieveQuery(User.class, searchQuery, "%" + keyword + "%");
        for (User user: users) {
            setPermission(user);
        }
        return users;
    }

    @Override
    public void update(User user) throws SQLException {
        voidQuery(updateQuery, user.getPassword(), user.getRole(), user.getDebt(), user.getUsername());
        voidQuery(deletePermissionsQuery, user.getUsername());
        for (Permission permission : user.getPermissions()) {
            voidQuery(insertPermissionQuery, user.getUsername(), permission);
        }
    }

    private void setPermission(User user) throws SQLException {
        ArrayList<String> strings = retrieveQuery(String.class, selectPermissionsByUsername, user.getUsername());
        Set<Permission> pms = strings.stream().map(UserDaoImpl::strToPM).collect(Collectors.toSet());
        user.setPermissions(pms);
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
