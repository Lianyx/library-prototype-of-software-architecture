package dao;

import object.po.Book;
import object.po.Category;
import object.po.Record;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dao.util.DaoFactory.getService;
import static dao.util.Util.*;

public class BookDaoImpl implements BookDao {
    private PreparedStatement
            insertQuery,
            selectByIdQuery,
            updateQuery,
            searchQuery,
            selectAllCategories;

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
        this.selectAllCategories = getStatement("" +
                "select * from Category;");
    }

    @Override
    public void insert(Book book) throws SQLException {
        voidQuery(insertQuery, book.getId(), book.getName(), book.getAuthor(), book.getEbookPath(), book.getEbookType(), book.getCategory());
    }

    @Override
    public Book selectById(String id) throws SQLException {
        ArrayList<Book> books = retrieveQuery(Book.class, selectByIdQuery, id);
        setAvailable(books);
        if (books.isEmpty()) {
            return null;
        }
        return books.get(0);
    }

    @Override
    public ArrayList<Book> search(String keyword) throws SQLException {
        ArrayList<Book> books = retrieveQuery(Book.class, searchQuery, "%" + keyword + "%", "%" + keyword + "%");
        setAvailable(books);
        return books;
    }

    @Override
    public void update(Book book) throws SQLException {
        voidQuery(updateQuery, book.getName(), book.getAuthor(), book.getEbookPath(), book.getEbookType(), book.getCategory(), book.getId());
    }

    @Override
    public ArrayList<Category> selectAllCategories() throws SQLException {
        return retrieveQuery(Category.class, selectAllCategories);
    }

    private void setAvailable(ArrayList<Book> books) throws SQLException {
        RecordDao recordDao = getService(RecordDao.class);
        for (Book book : books) {
            ArrayList<Record> records = recordDao.selectUnreturnedByBookId(book.getId());
            book.setAvailable(records.isEmpty()); // 如果加上本数，那就不是这样写了
        }
    }
}
