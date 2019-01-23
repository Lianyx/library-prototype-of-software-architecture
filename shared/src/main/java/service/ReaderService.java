package service;

import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ReaderService extends Remote {
    File getFile(String bookId) throws RemoteException;
}
