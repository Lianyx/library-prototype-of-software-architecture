import dao.UserDao;
import launcher.Main;
import object.enun.Permission;
import object.exception.AlreadyExistException;
import object.exception.InvalidLoginException;
import object.po.Book;
import object.po.Role;
import object.po.User;
import org.junit.Before;
import org.junit.BeforeClass;
import service.BookService;
import service.HelloService;
import service.LoginService;
import service.UserService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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

            MrWang.addPermission(Permission.PM_CREATE_USER);
            userService.updateUser(MrWang);
            User user = userDao.selectByUsername("MrWang");
            assertEquals(MrWang, user);

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
            assertEquals(books, new HashSet<>(sbk));
            ArrayList<Book> xiao = bookService.searchBook("肖");
            assertEquals(1, xiao.size());
            assertEquals(calculus, xiao.get(0));

            calculus.setAuthor("fiaebnifdkv");
            bookService.updateBook(calculus);
            xiao = bookService.searchBook("微积分");
            assertEquals(calculus, xiao.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }
}
