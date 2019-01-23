package serviceImpl;

import annotation.RMIRemote;
import service.ReaderService;

import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

@RMIRemote
public class ReaderServiceImpl extends UnicastRemoteObject implements ReaderService {
    public ReaderServiceImpl() throws RemoteException {
    }

    @Override
    public File getFile(String bookId) throws RemoteException {
        return null;
    }
}
