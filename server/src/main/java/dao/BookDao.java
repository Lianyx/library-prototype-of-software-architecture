package dao;

import object.po.Book;

import java.sql.SQLException;

public interface BookDao {
    void insert(Book book) throws SQLException;
    Book selectById(String id) throws SQLException;
    void update(Book book) throws SQLException;
}
