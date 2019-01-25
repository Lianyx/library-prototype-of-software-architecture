package serviceImpl;

import annotation.RMIRemote;
import dao.BookDao;
import dao.RecordDao;
import dao.UserDao;
import object.exception.BorrowAccessException;
import object.exception.ExceedMaximumException;
import object.exception.UnavailableException;
import object.po.Book;
import object.po.Record;
import object.po.Role;
import object.po.User;
import service.RecordService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static dao.util.DaoFactory.getService;

@RMIRemote
public class RecordServiceImpl extends UnicastRemoteObject implements RecordService {
    private RecordDao recordDao;
    private BookDao bookDao;
    private UserDao userDao;

    public RecordServiceImpl() throws RemoteException {
        recordDao = getService(RecordDao.class);
        bookDao = getService(BookDao.class);
        userDao = getService(UserDao.class);
    }

    @Override
    public void borrowBook(String username, String bookId) throws RemoteException {
        // 就不去查书是不是能借了
        try {
            User user = userDao.selectByUsername(username);
            Book book = bookDao.selectById(bookId);

            if (!book.isAvailable()) {
                throw new UnavailableException();
            }
            if (!haveAccess(user, book)) {
                throw new BorrowAccessException();
            }
            if (exceedMaximum(user)) {
                throw new ExceedMaximumException();
            }

            Record record = new Record(username, bookId);
            recordDao.insert(record);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void returnBook(String username, String bookId) throws RemoteException {
        try {
            Record record = recordDao.selectUnreturnedByUsernameAndBookId(username, bookId);
            record.setReturnTime(LocalDateTime.now());
            recordDao.update(record);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ArrayList<Record> getBorrowRecords(String username) throws RemoteException {
        try {
            return recordDao.selectByUsername(username);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Record> searchRecords(String keyword) throws RemoteException {
        try {
            return recordDao.search(keyword);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean haveAccess(User user, Book book) {
        return user.getRole().getCategories().contains(book.getCategory());
    }

    private boolean exceedMaximum(User user) throws SQLException {
        int unreturned = recordDao.selectUnreturnedByUsername(user.getUsername()).size();
        return unreturned >= user.getRole().getMaximum();
    }
}
