package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TestService extends Remote {
    String getStr(int n) throws RemoteException;
}
