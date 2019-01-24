package dao;

import object.po.Message;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import static dao.util.Util.*;

public class MessageDaoImpl implements MessageDao {
    private PreparedStatement
            insertQuery,
            selectByUsernameQuery,
            deleteByUsernameQuery;

    public MessageDaoImpl() throws SQLException {
        this.insertQuery = getStatement("INSERT INTO Message (type, toUsername, content, time) VALUES (?, ?, ?, ?);");
        this.selectByUsernameQuery = getStatement("" +
                "SELECT *\n" +
                "FROM Message\n" +
                "WHERE type IN (SELECT role\n" +
                "               FROM User\n" +
                "               WHERE username = ?)\n" +
                "      OR toUsername = ?;");
        this.deleteByUsernameQuery = getStatement("" +
                "DELETE\n" +
                "FROM Message\n" +
                "WHERE type IN (SELECT role\n" +
                "               FROM User\n" +
                "               WHERE username = ?)\n" +
                "      OR toUsername = ?;");
    }

    @Override
    public void insert(Message message) throws SQLException {
        voidQuery(insertQuery, message.getType(), message.getToUsername(), message.getContent(), message.getTime());
    }

    @Override
    public ArrayList<Message> selectByUsername(String username) throws SQLException {
        return retrieveQuery(Message.class, selectByUsernameQuery, username, username);
    }

    @Override
    public void deleteByUsername(String username) throws SQLException {
        voidQuery(deleteByUsernameQuery, username, username);
    }
}
