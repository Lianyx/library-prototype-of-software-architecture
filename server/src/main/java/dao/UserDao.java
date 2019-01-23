package dao;

import object.po.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDao {
    void insert(User user) throws SQLException;
    User selectByUsername(String id) throws SQLException;
    ArrayList<User> selectAll() throws SQLException;
    ArrayList<User> search(String keyword) throws SQLException;
    void update(User user) throws SQLException;
}
