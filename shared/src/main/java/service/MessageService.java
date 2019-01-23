package service;


import object.po.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface MessageService extends Remote {
    ArrayList<Message> getByUsername(String username) throws RemoteException;
    void clear(String username) throws RemoteException;
}
