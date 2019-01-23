package dao;

import object.po.Record;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import static dao.util.Util.*;

public class RecordDaoImpl implements RecordDao {
    private PreparedStatement insertQuery, selectByUsernameQuery;

    public RecordDaoImpl() throws SQLException {
        this.insertQuery = getStatement("INSERT INTO Record (username, bookId, operationType, time) VALUES (?, ?, ?, ?);");
        this.selectByUsernameQuery = getStatement("" +
                "SELECT *\n" +
                "FROM Record\n" +
                "WHERE username = ?;");
    }

    @Override
    public void insert(Record record) throws SQLException {
//        voidQuery(insertQuery, record.getUsername(), record.getBookId(), record.getOperationType(), record.getTime());
    }

    @Override
    public ArrayList<Record> selectByUsername(String username) throws SQLException {
        return retrieveQuery(Record.class, selectByUsernameQuery, username);
    }
}
