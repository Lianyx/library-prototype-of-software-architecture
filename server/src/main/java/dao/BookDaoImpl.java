package dao;

import object.po.Book;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static dao.util.Util.*;

public class BookDaoImpl implements BookDao {
    private PreparedStatement insertQuery, selectByIdQuery;

    public BookDaoImpl() throws SQLException {
        this.insertQuery = getStatement(
                "INSERT INTO Book (id, name, author, ebookDir, categoryId) VALUES (?, ?, ?, ?, ?);");
        this.selectByIdQuery = getStatement("" +
                "SELECT *\n" +
                "FROM Book\n" +
                "WHERE id = ?;");
    }

    @Override
    public void insert(Book book) throws SQLException {
        voidQuery(insertQuery, book.getId(), book.getName(), book.getAuthor(), book.getEbookPath(), book.getCategory());
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
    public void update(Book book) {

    }
}
