package service;

import object.po.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserService extends Remote {
    void updateUser(User user) throws RemoteException;
}
