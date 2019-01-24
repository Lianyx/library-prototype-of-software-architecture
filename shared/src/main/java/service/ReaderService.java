package service;

import java.io.FileNotFoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import com.healthmarketscience.rmiio.*;

public interface ReaderService extends Remote {
    void sendFile(RemoteInputStream data, String bookId, String suffix) throws RemoteException;
    RemoteInputStream getFile(String bookId) throws RemoteException, FileNotFoundException;
}
