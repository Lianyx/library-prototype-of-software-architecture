package dao;

import object.po.User;

import java.sql.SQLException;

public interface UserDao {
    void insert(User user) throws SQLException;
    User selectByUsername(String id) throws SQLException;
}
