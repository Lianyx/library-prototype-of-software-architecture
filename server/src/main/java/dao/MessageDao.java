package dao;

import object.po.Message;

import java.sql.SQLException;

public interface MessageDao {
    void insert(Message message) throws SQLException;
}
