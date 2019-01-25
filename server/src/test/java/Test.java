import com.healthmarketscience.rmiio.SimpleRemoteInputStream;
import dao.RecordDao;
import dao.UserDao;
import launcher.Main;
import object.enun.Permission;
import object.exception.AlreadyExistException;
import object.exception.BorrowAccessException;
import object.exception.ExceedMaximumException;
import object.exception.InvalidLoginException;
import object.po.*;
import observer.RmiClient;
import observer.RmiService;
import org.junit.Before;
import org.junit.BeforeClass;
import service.*;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.Assert.*;
import static data.DataSupplier.*;
import static dao.util.DaoFactory.getService;

public class Test {

    @BeforeClass
    public static void before() {
        Main.main(new String[]{});
    }

    @Before
    public void initialData() {
        try {
            initializeData();
        } catch (SQLException e) {
            e.printStackTrace();
            assertEquals(true, false);
        }
    }

    @org.junit.Test
    public void testConnection() {
        try {
            HelloService look_up = (HelloService) Naming.lookup("rmi://localhost:1099/HelloService");
            assertEquals("Hello, CX", look_up.helloTo("CX"));
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            e.printStackTrace();
            assertEquals(true, false);
        }
    }

    @org.junit.Test
    public void testLogin() {
        try {
            LoginService loginService = (LoginService) Naming.lookup("rmi://localhost:1099/LoginService");
            loginService.login("MrWang", "123");
            try {
                loginService.login("admin", "43");
                assertTrue(false);
            } catch (InvalidLoginException e) {
                assertTrue(true);
            }
            try {
                loginService.login("Amin", "43");
                assertTrue(false);
            } catch (InvalidLoginException e) {
                assertTrue(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    @org.junit.Test
    public void testUser() {
        try {
            UserDao userDao = getService(UserDao.class);
            UserService userService = (UserService) Naming.lookup("rmi://localhost:1099/UserService");

            ArrayList<User> allUsers = userService.getAllUsers();
            assertEquals(users, new HashSet<>(allUsers));

            ArrayList<Role> allRoles = userService.getAllRoles();
            assertEquals(roles, new HashSet<>(allRoles));

            ArrayList<User> userWithAng = userService.searchUser("ang");
            assertEquals(new HashSet<>(Arrays.asList(MrWang, GradZhang)), new HashSet<>(userWithAng));

            // update在message里面查的

            userService.addUser(new User("XX", "XX", admin));
            try {
                userService.addUser(new User("MrWang", "fda", admin));
                assertTrue(false);
            } catch (AlreadyExistException e) {
                assertTrue(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    @org.junit.Test
    public void testBook() {
        try {
            BookService bookService = (BookService) Naming.lookup("rmi://localhost:1099/BookService");

            ArrayList<Book> sbk = bookService.searchBook("计算");
            assertEquals(new HashSet<>(Arrays.asList(calculus, network)), new HashSet<>(sbk));
            ArrayList<Book> xiao = bookService.searchBook("肖");
            assertEquals(1, xiao.size());
            assertEquals(calculus, xiao.get(0));

            calculus.setAuthor("fiaebnifdkv");
            bookService.updateBook(calculus);
            xiao = bookService.searchBook("微积分");
            assertEquals(calculus, xiao.get(0));

            ArrayList<Category> allCategories = bookService.getAllCategories();
            assertEquals(categories, new HashSet<>(allCategories));

            // add 沒测
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    @org.junit.Test
    public void testRecord() {
        try {
            RecordService recordService = (RecordService) Naming.lookup("rmi://localhost:1099/RecordService");
            RecordDao recordDao = getService(RecordDao.class);

            recordService.borrowBook(MrWang.getUsername(), calculus.getId());
            Record record = recordService.getBorrowRecords(MrWang.getUsername()).get(0);
            assertEquals(record.getUsername(), MrWang.getUsername());

            assertTrue(record.getBorrowTime().isAfter(LocalDateTime.now().minusSeconds(2)));
            assertTrue(record.getBorrowTime().isBefore(LocalDateTime.now()));
            assertEquals(calculus.getName(), record.getBookName());
            assertEquals(null, record.getReturnTime());

            assertEquals(1, recordDao.selectUnreturnedByUsername(MrWang.getUsername()).size());
            recordService.returnBook(MrWang.getUsername(), calculus.getId());
            assertTrue(recordDao.selectUnreturnedByUsername(MrWang.getUsername()).isEmpty());

            // search沒测

            try {
                recordService.borrowBook(UnGradLi.getUsername(), republic.getId());
                assertTrue(false);
            } catch (BorrowAccessException e) {
                assertTrue(true);
            }

            recordService.borrowBook(UnGradLi.getUsername(), network.getId());
            recordService.borrowBook(UnGradLi.getUsername(), calculus.getId());
            try {
                recordService.borrowBook(UnGradLi.getUsername(), OS.getId());
                assertTrue(false);
            } catch (ExceedMaximumException e) {
                assertTrue(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    @org.junit.Test
    public void testMessage() {
        try {
            UserService userService = (UserService) Naming.lookup("rmi://localhost:1099/UserService");
            MessageService messageService = (MessageService) Naming.lookup("rmi://localhost:1099/MessageService");

            MrWang.addPermission(Permission.PM_CREATE_USER);
            userService.updateUser(MrWang);
//            User user = userDao.selectByUsername("MrWang");
            User user = userService.searchUser(MrWang.getUsername()).get(0);
            assertEquals(MrWang, user);
            assertEquals(
                    new HashSet<>(Collections.singletonList(Permission.PM_CREATE_USER)),
                    user.getPermissions()
            );

            ArrayList<Message> messages = messageService.getByUsername(adminUser.getUsername());
            assertEquals(1, messages.size());
            Message message = messages.get(0);
            assertEquals("管理员", message.getType());
            assertEquals(null, message.getToUsername());
            assertEquals("用户MrWang修改个人信息", message.getContent());

            adminUser.setPassword("fhdabsoiy");
            userService.updateUser(adminUser);

            messages = messageService.getByUsername(adminUser.getUsername());
            assertEquals(2, messages.size());

            messageService.clear(MrWang.getUsername());
            messages = messageService.getByUsername(adminUser.getUsername());
            assertEquals(2, messages.size());

            messageService.clear(adminUser.getUsername());
            messages = messageService.getByUsername(adminUser.getUsername());
            assertEquals(0, messages.size());
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    @org.junit.Test
    public void testSchedule() {
        try {
            RecordDao recordDao = getService(RecordDao.class);

            Record record = new Record();
            record.setUsername(UnGradLi.getUsername());
            record.setBookId(calculus.getId());
            record.setBorrowTime(LocalDateTime.now().minusDays(12));

            recordDao.insert(record);
            record = recordDao.selectUnreturnedByUsernameAndBookId(UnGradLi.getUsername(), calculus.getId());
            assertEquals(0, record.getPenalty(), 0.00001);

            recordDao.updatePenalty();

            record = recordDao.selectUnreturnedByUsernameAndBookId(UnGradLi.getUsername(), calculus.getId());
            assertEquals(1.0, record.getPenalty(), 0.00001);
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    @org.junit.Test
    public void testReader() {
        try {
            ReaderService readerService = (ReaderService) Naming.lookup("rmi://localhost:1099/ReaderService");


            SimpleRemoteInputStream istream = new SimpleRemoteInputStream(new FileInputStream("ebookDir/xx.pdf"));
//            SimpleRemoteInputStream istream = new SimpleRemoteInputStream(new FileInputStream("/Users/tiberius/Documents/College/-Junior2/sa_jn2/hw3/library-prototype-of-software-architecture/server/ebookDir/xx.pdf"));
            readerService.sendFile(istream.export(), "xxx", ".pdf");
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

//    @org.junit.Test
//    public void testObserver() {
//        try {
//            RmiService remoteService = (RmiService) Naming.lookup("rmi://localhost:1099/RmiService");
//
//            RmiClient client = new RmiClient();
//            remoteService.addObserver(client);
//        } catch (Exception e) {
//            e.printStackTrace();
//            assertTrue(false);
//        }
//    }
}
