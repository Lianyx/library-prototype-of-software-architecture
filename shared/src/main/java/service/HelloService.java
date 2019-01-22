package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloService extends Remote {
    String helloTo(String name) throws RemoteException;
}
