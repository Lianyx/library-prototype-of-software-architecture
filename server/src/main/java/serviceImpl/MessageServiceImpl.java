package serviceImpl;

import annotation.RMIRemote;
import dao.MessageDao;
import object.po.Message;
import service.MessageService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

import static dao.util.DaoFactory.getService;

@RMIRemote
public class MessageServiceImpl extends UnicastRemoteObject implements MessageService {
    private MessageDao messageDao;

    public MessageServiceImpl() throws RemoteException {
        this.messageDao = getService(MessageDao.class);
    }

    @Override
    public ArrayList<Message> getByUsername(String username) throws RemoteException {
        try {
            return messageDao.selectByUsername(username);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void clear(String username) throws RemoteException {
        try {
            messageDao.deleteByUsername(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
