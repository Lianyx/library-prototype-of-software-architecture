package dao;

import object.po.Category;

import java.sql.SQLException;

public interface CategoryDao {
    void insert(Category category) throws SQLException;
    Category selectById(String id) throws SQLException;
}
