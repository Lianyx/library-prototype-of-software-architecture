package dao;

import object.po.Message;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static dao.util.Util.*;

public class MessageDaoImpl implements MessageDao {
    private PreparedStatement insertQuery;

    public MessageDaoImpl() throws SQLException {
        this.insertQuery = getStatement("INSERT INTO Message (type, toUsername, content, time) VALUES (?, ?, ?, ?);");
    }

    @Override
    public void insert(Message message) throws SQLException {
        voidQuery(insertQuery, message.getType(), message.getToUsername(), message.getContent(), message.getTime());
    }
}
