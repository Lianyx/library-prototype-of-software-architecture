package dao;

import object.po.Role;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RoleDao {
    void insert(Role role) throws SQLException;
    Role selectByType(String id) throws SQLException;
    ArrayList<Role> selectAll() throws SQLException;
}
