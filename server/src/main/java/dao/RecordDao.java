package dao;

import object.po.Record;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RecordDao {
    void insert(Record record) throws SQLException;
    ArrayList<Record> selectByUsername(String username) throws SQLException;
}
