package service;

import object.po.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LoginService extends Remote {
    User login(String username, String password) throws RemoteException;
}
