package service;

import object.po.Book;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface BorrwoService extends Remote {
    ArrayList<Book> searchBook(String keyword) throws RemoteException;
    void borrowBook(String username, String bookId) throws RemoteException;
    void returnBook(String username, String bookId) throws RemoteException;
}
