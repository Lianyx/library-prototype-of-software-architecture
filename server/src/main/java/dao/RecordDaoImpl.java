package dao;

import object.po.Record;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import static dao.util.Util.*;

public class RecordDaoImpl implements RecordDao {
    private PreparedStatement
            insertQuery,
            selectByUsernameQuery,
            searchQuery,
            selectUnreturnedByUsernameQuery,
            selectUnreturnedByUsernameAndBookIdQuery,
            selectUnreturnedByBookIdQuery,
            updateQuery,
            updatePenaltyQuery;

    public RecordDaoImpl() throws SQLException {
        this.insertQuery = getStatement("INSERT INTO Record (username, bookId, borrowTime, returnTime, penalty) VALUES (?, ?, ?, ?, ?);");
        this.selectByUsernameQuery = getStatement("" +
                "SELECT\n" +
                "  Record.bookId,\n" +
                "  Record.username,\n" +
                "  Record.borrowTime,\n" +
                "  Record.returnTime,\n" +
                "  Record.penalty,\n" +
                "  Book.name AS bookName\n" +
                "FROM Record, Book\n" +
                "WHERE Record.bookId = Book.id and username = ?");
        this.searchQuery = getStatement("" +
                "SELECT\n" +
                "  Record.bookId,\n" +
                "  Record.username,\n" +
                "  Record.borrowTime,\n" +
                "  Record.returnTime,\n" +
                "  Record.penalty,\n" +
                "  Book.name AS bookName\n" +
                "FROM Record, Book\n" +
                "WHERE Record.bookId = Book.id\n" +
                "      AND (username LIKE ? OR Book.name LIKE ?);");

        this.selectUnreturnedByUsernameQuery = getStatement("" +
                "SELECT\n" +
                "  Record.bookId,\n" +
                "  Record.username,\n" +
                "  Record.borrowTime,\n" +
                "  Record.returnTime,\n" +
                "  Record.penalty,\n" +
                "  Book.name AS bookName\n" +
                "FROM Record, Book\n" +
                "WHERE Record.bookId = Book.id\n" +
                "  AND username = ? AND returnTime is NULL;");
        // TODO
        this.updateQuery = getStatement("" +
                "UPDATE Record\n" +
                "SET returnTime = ?, penalty = ?\n" +
                "WHERE username = ? AND bookId = ? AND borrowTime = ?;");
        this.selectUnreturnedByUsernameAndBookIdQuery = getStatement("" +
                "SELECT\n" +
                "  Record.bookId,\n" +
                "  Record.username,\n" +
                "  Record.borrowTime,\n" +
                "  Record.returnTime,\n" +
                "  Record.penalty,\n" +
                "  Book.name AS bookName\n" +
                "FROM Record, Book\n" +
                "WHERE Record.bookId = Book.id" +
                "   and username = ? and bookId = ? and returnTime is NULL;");
        this.selectUnreturnedByBookIdQuery = getStatement("" +
                "SELECT\n" +
                "  Record.bookId,\n" +
                "  Record.username,\n" +
                "  Record.borrowTime,\n" +
                "  Record.returnTime,\n" +
                "  Record.penalty,\n" +
                "  Book.name AS bookName\n" +
                "FROM Record, Book\n" +
                "WHERE Record.bookId = Book.id" +
                "  and bookId = ? and returnTime is NULL;");

        this.updatePenaltyQuery = getStatement("" +
                "UPDATE Record, User, Role, Penalty\n" +
                "SET penalty = (DATEDIFF(NOW(), Record.borrowTime) - Role.dayLimit) * penalty.penaltyPerDay\n" +
                "WHERE returnTime IS NULL\n" +
                "      AND User.username = Record.username\n" +
                "      AND User.role = Role.type\n" +
                "      AND Role.dayLimit < DATEDIFF(NOW(), Record.borrowTime);");
    }

    @Override
    public void insert(Record record) throws SQLException {
        voidQuery(insertQuery, record.getUsername(), record.getBookId(), record.getBorrowTime(), record.getReturnTime(), record.getPenalty());
    }

    @Override
    public ArrayList<Record> selectByUsername(String username) throws SQLException {
        return retrieveQuery(Record.class, selectByUsernameQuery, username);
    }

    @Override
    public ArrayList<Record> search(String keyword) throws SQLException {
        return retrieveQuery(Record.class, searchQuery, "%" + keyword + "%", "%" + keyword + "%");
    }

    @Override
    public ArrayList<Record> selectUnreturnedByUsername(String username) throws SQLException {
        return retrieveQuery(Record.class, selectUnreturnedByUsernameQuery, username);
    }

    @Override
    public ArrayList<Record> selectUnreturnedByBookId(String bookId) throws SQLException {
        return retrieveQuery(Record.class, selectUnreturnedByBookIdQuery, bookId);
    }

    @Override
    public Record selectUnreturnedByUsernameAndBookId(String username, String bookId) throws SQLException {
        ArrayList<Record> records = retrieveQuery(Record.class, selectUnreturnedByUsernameAndBookIdQuery, username, bookId);
        return records.get(0);
    }

    @Override
    public void update(Record record) throws SQLException {
        voidQuery(updateQuery, record.getReturnTime(), record.getPenalty(), record.getUsername(), record.getBookId(), record.getBorrowTime());
    }

    @Override
    public void updatePenalty() throws SQLException {
        voidQuery(updatePenaltyQuery);
    }

    //    private void setBookName(ArrayList<Record> records) throws SQLException {
//        BookDao bookDao = getService(BookDao.class);
//        for (Record record : records) {
//            Book book = bookDao.selectById(record.getBookId());
//            record.setBookName(book.getName());
//        }
//    }
}
