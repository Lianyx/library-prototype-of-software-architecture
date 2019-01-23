package serviceImpl;

import annotation.RMIRemote;
import object.po.Message;
import service.MessageService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

@RMIRemote
public class MessageServiceImpl extends UnicastRemoteObject implements MessageService {
    public MessageServiceImpl() throws RemoteException {
    }

    @Override
    public ArrayList<Message> getByUsername(String username) throws RemoteException {
        return null;
    }

    @Override
    public void clear(String username) throws RemoteException {

    }
}
