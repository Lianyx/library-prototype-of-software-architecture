package service;

import object.po.Book;
import object.po.Record;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface BookService extends Remote {
    void addBook(Book book) throws RemoteException;
    void updateBook(Book book) throws RemoteException;
    ArrayList<Book> searchBook(String keyword) throws RemoteException;
}
