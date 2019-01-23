package serviceImpl;

import annotation.RMIRemote;
import object.po.Record;
import service.RecordService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

@RMIRemote
public class RecordServiceImpl extends UnicastRemoteObject implements RecordService {
    public RecordServiceImpl() throws RemoteException {
    }

    @Override
    public void borrowBook(String username, String bookId) throws RemoteException {

    }

    @Override
    public void returnBook(String username, String bookId) throws RemoteException {

    }

    @Override
    public ArrayList<Record> getBorrowRecords(String username) throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<Record> searchRecords(String keyword) throws RemoteException {
        return null;
    }
}
