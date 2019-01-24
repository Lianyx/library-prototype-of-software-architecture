package serviceImpl;

import annotation.RMIRemote;
import com.healthmarketscience.rmiio.RemoteInputStream;
import com.healthmarketscience.rmiio.RemoteInputStreamClient;
import com.healthmarketscience.rmiio.SimpleRemoteInputStream;
import dao.BookDao;
import object.po.Book;
import service.ReaderService;

import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

import static dao.util.DaoFactory.getService;

@RMIRemote
public class ReaderServiceImpl extends UnicastRemoteObject implements ReaderService {
    private BookDao bookDao;

    public ReaderServiceImpl() throws RemoteException {
        bookDao = getService(BookDao.class);
    }

    @Override
    public void sendFile(RemoteInputStream data, String bookId, String suffix) throws RemoteException {
        try (InputStream input = RemoteInputStreamClient.wrap(data)) {
            writeToFile(input, "ebookDir/" + bookId + suffix);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public RemoteInputStream getFile(String bookId) throws RemoteException, FileNotFoundException {
        try {
            Book book = bookDao.selectById(bookId);
            String ebookPath = "EBook.html";
            SimpleRemoteInputStream istream = new SimpleRemoteInputStream(new FileInputStream(
                    ReaderServiceImpl.class.getResource("").getPath() + ebookPath));
            return istream.export();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // TODO 可以用preview，但沒法用pdf expert打开
    private void writeToFile(InputStream stream, String path) throws IOException {
        byte[] buffer = new byte[stream.available()];

        File targetFile = new File(path);
        OutputStream outStream = new FileOutputStream(targetFile);
        outStream.write(buffer);

        int readBytes = 0;
        do {
            readBytes = stream.read(buffer);
            if (readBytes >= 0) {
                outStream.write(buffer, 0, readBytes);
            }
        } while (readBytes != -1);
    }
}
