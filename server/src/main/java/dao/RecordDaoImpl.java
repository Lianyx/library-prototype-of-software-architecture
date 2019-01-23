package dao;

import object.po.Book;
import object.po.Record;
import service.BookService;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import static dao.util.DaoFactory.getService;
import static dao.util.Util.*;

public class RecordDaoImpl implements RecordDao {
    private PreparedStatement insertQuery, selectByUsernameQuery;

    public RecordDaoImpl() throws SQLException {
        this.insertQuery = getStatement("INSERT INTO Record (username, bookId, borrowTime, returnTime) VALUES (?, ?, ?, ?, ?);");
        this.selectByUsernameQuery = getStatement("" +
                "SELECT *\n" +
                "FROM Record\n" +
                "WHERE username = ?;");
    }

    @Override
    public void insert(Record record) throws SQLException {
        voidQuery(insertQuery, record.getUsername(), record.getBookId(), record.getBorrowTime(), record.getReturnTime());
    }

    @Override
    public ArrayList<Record> selectByUsername(String username) throws SQLException {
        ArrayList<Record> records = retrieveQuery(Record.class, selectByUsernameQuery, username);
        records.forEach(r -> {
            try {
                Book book = getService(BookDao.class).selectById(r.getBookId());
                r.setBookName(book.getName());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return records;
    }
}
