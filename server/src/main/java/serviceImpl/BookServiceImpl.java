package serviceImpl;

import annotation.RMIRemote;
import object.po.Book;
import service.BookService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

@RMIRemote
public class BookServiceImpl extends UnicastRemoteObject implements BookService {

    public BookServiceImpl() throws RemoteException {
    }

    @Override
    public void addBook(Book book) throws RemoteException {

    }

    @Override
    public void updateBook(Book book) throws RemoteException {

    }

    @Override
    public ArrayList<Book> searchBook(String keyword) throws RemoteException {
        return null;
    }
}
