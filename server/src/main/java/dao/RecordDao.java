package dao;

import object.po.Record;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RecordDao {
    void insert(Record record) throws SQLException;
    ArrayList<Record> selectByUsername(String username) throws SQLException;
    ArrayList<Record> search(String keyword) throws SQLException;

    ArrayList<Record> selectUnreturnedByUsername(String username) throws SQLException;
    ArrayList<Record> selectUnreturnedByBookId(String bookId) throws SQLException;
    Record selectUnreturnedByUsernameAndBookId(String username, String bookId) throws SQLException;

    void update(Record record) throws SQLException;
    void updatePenalty() throws SQLException;
}
