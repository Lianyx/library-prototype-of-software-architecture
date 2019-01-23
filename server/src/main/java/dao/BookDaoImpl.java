package dao;

import object.po.Book;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dao.util.Util.*;

public class BookDaoImpl implements BookDao {
    private PreparedStatement
            insertQuery,
            selectByIdQuery,
            updateQuery,
            searchQuery;

    public BookDaoImpl() throws SQLException {
        this.insertQuery = getStatement(
                "INSERT INTO Book (id, name, author, ebookPath, ebookType, categoryId) VALUES (?, ?, ?, ?, ?, ?);");
        this.selectByIdQuery = getStatement("" +
                "SELECT *\n" +
                "FROM Book\n" +
                "WHERE id = ?;");
        this.updateQuery = getStatement("" +
                "update Book\n" +
                "SET name = ?, author = ?, ebookPath = ?, ebookType = ?, categoryId = ?\n" +
                "where id = ?;");
        this.searchQuery = getStatement("" +
                "select * from Book where name like ? or author like ?;");
    }

    @Override
    public void insert(Book book) throws SQLException {
        voidQuery(insertQuery, book.getId(), book.getName(), book.getAuthor(), book.getEbookPath(), book.getEbookType(), book.getCategory());
    }

    @Override
    public Book selectById(String id) throws SQLException {
        List<Book> books = retrieveQuery(Book.class, selectByIdQuery, id);
        if (books.isEmpty()) {
            return null;
        }
        return books.get(0);
    }

    @Override
    public ArrayList<Book> search(String keyword) throws SQLException {
        return retrieveQuery(Book.class, searchQuery, "%" + keyword + "%", "%" + keyword + "%");
    }

    @Override
    public void update(Book book) throws SQLException {
        voidQuery(updateQuery, book.getName(), book.getAuthor(), book.getEbookPath(), book.getEbookType(), book.getCategory(), book.getId());
    }
}
