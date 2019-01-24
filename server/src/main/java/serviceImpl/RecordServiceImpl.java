package serviceImpl;

import annotation.RMIRemote;
import dao.BookDao;
import dao.RecordDao;
import dao.UserDao;
import object.exception.BorrowAccessException;
import object.exception.ExceedMaximumException;
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
        // TODO 要不要來查已借走的
        try {
            User user = userDao.selectByUsername(username);
            Book book = bookDao.selectById(bookId);

            if (!haveAccess(user, book)) {
                throw new BorrowAccessException();
            }

            if (exceedMaximum(user)) {
                throw new ExceedMaximumException();
            }

            Record record = new Record(username, bookId, LocalDateTime.now());
            recordDao.insert(record);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    private boolean haveAccess(User user, Book book) {
        return user.getRole().getCategories().contains(book.getCategory());
    }

    private boolean exceedMaximum(User user) throws SQLException {
        int unreturned = recordDao.selectUnreturnedByUsername(user.getUsername()).size();
        return unreturned >= user.getRole().getMaximum();
    }
}
