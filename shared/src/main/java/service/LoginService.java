package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LoginService extends Remote {
    void login(String username, String password) throws RemoteException;
    // TODO
}
