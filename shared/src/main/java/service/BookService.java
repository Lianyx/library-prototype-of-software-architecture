package service;

import object.enun.BookType;
import object.po.Book;
import object.po.Category;
import object.po.Record;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface BookService extends Remote {
    void addBook(Book book) throws RemoteException;
    void updateBook(Book book) throws RemoteException;
    ArrayList<Book> searchBook(String keyword) throws RemoteException;
    ArrayList<Category> getAllCategories() throws RemoteException;
    List<BookType> getAllEBookTypes() throws RemoteException;
}
