package serviceImpl;

import annotation.RMIRemote;
import dao.BookDao;
import object.enun.BookType;
import object.exception.AlreadyExistException;
import object.po.Book;
import object.po.Category;
import service.BookService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static dao.util.DaoFactory.getService;

@RMIRemote
public class BookServiceImpl extends UnicastRemoteObject implements BookService {
    private BookDao bookDao;

    public BookServiceImpl() throws RemoteException {
        bookDao = getService(BookDao.class);
    }

    @Override
    public void addBook(Book book) throws RemoteException {
        try {
            if (null != bookDao.selectById(book.getId())) {
                throw new AlreadyExistException();
            }
            bookDao.insert(book);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateBook(Book book) throws RemoteException {
        try {
            bookDao.update(book);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Book> searchBook(String keyword) throws RemoteException {
        try {
            return bookDao.search(keyword);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Category> getAllCategories() throws RemoteException {
        try {
            return bookDao.selectAllCategories();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<BookType> getAllEBookTypes() throws RemoteException {
        return Arrays.asList(BookType.NULL, BookType.PDF, BookType.HTML);
    }
}
