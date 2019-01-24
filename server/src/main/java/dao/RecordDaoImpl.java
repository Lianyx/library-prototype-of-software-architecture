package dao;

import object.po.Book;
import object.po.Record;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import static dao.util.DaoFactory.getService;
import static dao.util.Util.*;

public class RecordDaoImpl implements RecordDao {
    private PreparedStatement
            insertQuery,
            selectByUsernameQuery,
            selectUnreturnedByUsernameQuery,
            selectUnreturnedByUsernameAndBookIdQuery,
            selectUnreturnedByBookIdQuery,
            updateQuery;

    public RecordDaoImpl() throws SQLException {
        this.insertQuery = getStatement("INSERT INTO Record (username, bookId, borrowTime, returnTime) VALUES (?, ?, ?, ?, ?);");
        this.selectByUsernameQuery = getStatement("" +
                "SELECT *\n" +
                "FROM Record\n" +
                "WHERE username = ?;");
        this.selectUnreturnedByUsernameQuery = getStatement("" +
                "select * from Record where username = ? and returnTime is NULL;");
        this.updateQuery = getStatement("" +
                "UPDATE Record\n" +
                "SET returnTime = ?\n" +
                "WHERE username = ? AND bookId = ? AND borrowTime = ?;");
        this.selectUnreturnedByUsernameAndBookIdQuery = getStatement("" +
                "select * from Record where username = ? and bookId = ? and returnTime is NULL;");
        this.selectUnreturnedByBookIdQuery = getStatement("" +
                "select * from Record where bookId = ? and returnTime is NULL;");
    }

    @Override
    public void insert(Record record) throws SQLException {
        voidQuery(insertQuery, record.getUsername(), record.getBookId(), record.getBorrowTime(), record.getReturnTime());
    }

    @Override
    public ArrayList<Record> selectByUsername(String username) throws SQLException {
        ArrayList<Record> records = retrieveQuery(Record.class, selectByUsernameQuery, username);
        setBookName(records);
        return records;
    }

    @Override
    public ArrayList<Record> selectUnreturnedByUsername(String username) throws SQLException {
        ArrayList<Record> records = retrieveQuery(Record.class, selectUnreturnedByUsernameQuery, username);
        setBookName(records);
        return records;
    }

    @Override
    public ArrayList<Record> selectUnreturnedByBookId(String bookId) throws SQLException {
        ArrayList<Record> records = retrieveQuery(Record.class, selectUnreturnedByBookIdQuery, bookId);;
        setBookName(records);
        return records;
    }

    @Override
    public Record selectUnreturnedByUsernameAndBookId(String username, String bookId) throws SQLException {
        ArrayList<Record> records = retrieveQuery(Record.class, selectUnreturnedByUsernameAndBookIdQuery, username, bookId);
        setBookName(records); // 应该只会有一个
        return records.get(0);
    }

    @Override
    public void update(Record record) throws SQLException {
        voidQuery(updateQuery, record.getReturnTime(), record.getUsername(), record.getBookId(), record.getBorrowTime());
    }

    private void setBookName(ArrayList<Record> records) throws SQLException {
        BookDao bookDao = getService(BookDao.class);
        for (Record record : records) {
            Book book = bookDao.selectById(record.getBookId());
            record.setBookName(book.getName());
        }
    }
}
