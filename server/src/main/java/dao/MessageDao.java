package dao;

import object.po.Message;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MessageDao {
    void insert(Message message) throws SQLException;
    ArrayList<Message> selectByUsername(String username) throws SQLException;
    void deleteByUsername(String username) throws SQLException;
}
