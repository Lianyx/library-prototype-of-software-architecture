package service;

import object.po.Book;
import object.po.Record;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface BorrwoService extends Remote {
    void borrowBook(String username, String bookId) throws RemoteException;
    void returnBook(String username, String bookId) throws RemoteException;
    // return 沒什么exception

    ArrayList<Record> getBorrowRecords(String username) throws RemoteException;
}
