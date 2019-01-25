package dao;

import object.po.Book;
import object.po.Category;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookDao {
    void insert(Book book) throws SQLException;
    Book selectById(String id) throws SQLException;
    void update(Book book) throws SQLException;
    ArrayList<Book> search(String keyword) throws SQLException;
    ArrayList<Category> selectAllCategories() throws SQLException;
}
