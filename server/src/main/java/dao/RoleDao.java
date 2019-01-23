package dao;

import object.po.Role;

import java.sql.SQLException;

public interface RoleDao {
    void insert(Role role) throws SQLException;
    Role selectById(String id) throws SQLException;
}
