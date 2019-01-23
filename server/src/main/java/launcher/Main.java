package launcher;

import annotation.RMIRemote;
import dao.BookDao;
import dao.CategoryDao;
import dao.RoleDao;
import dao.UserDao;
import object.po.Book;
import object.po.Category;
import object.po.Role;
import object.po.User;
import org.reflections.Reflections;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static dao.util.Util.*;
import static dao.util.DaoFactory.getService;

public class Main {
    public static void initializeData() throws SQLException {
        executeVoidSql("delete from Book");
        executeVoidSql("delete from Category");
        executeVoidSql("delete from Message");
        executeVoidSql("delete from Record");
        executeVoidSql("delete from Role");
        executeVoidSql("delete from User");
        executeVoidSql("delete from UserPermission");
        executeVoidSql("delete from RoleCategory");

        CategoryDao categoryDao = getService(CategoryDao.class);
        Category history = new Category("HS", "历史");
        Category cs = new Category("CS", "计算机");
        Category math = new Category("MATH", "数学");
        Category classics = new Category("CL", "古典学");
        categoryDao.insert(history);
        categoryDao.insert(cs);
        categoryDao.insert(math);
        categoryDao.insert(classics);

        RoleDao roleDao = getService(RoleDao.class);
        Role teacher = new Role("老师", 15, 40, new HashSet<>(Arrays.asList(history, cs, math, classics)));
        Role undergraduate = new Role("本科生", 5, 10, new HashSet<>(Arrays.asList(cs, math)));
        Role graduate = new Role("研究生", 10, 20, new HashSet<>(Arrays.asList(history, cs, math)));
        Role admin = new Role("管理员", 0, 0, new HashSet<>(Collections.emptyList()));
        roleDao.insert(teacher);
        roleDao.insert(undergraduate);
        roleDao.insert(graduate);
        roleDao.insert(admin);

        UserDao userDao = getService(UserDao.class);
        User MrWang = new User("MrWang", "123", teacher);
        User GradZhang = new User("Zhang", "123", graduate);
        User UnGradLi = new User("Li", "123", undergraduate);
        User adminUser = new User("admin", "123", admin);
        userDao.insert(MrWang);
        userDao.insert(GradZhang);
        userDao.insert(UnGradLi);
        userDao.insert(adminUser);

        BookDao bookDao = getService(BookDao.class);
        Book network = new Book("NW", "计算机网络", "谢某", null, null, cs);
        Book calculus = new Book("CCL", "微积分", "肖", null, null, math);
        bookDao.insert(network);
        bookDao.insert(calculus);


    }

    public static void main(String[] args) {
        try {
            initializeData();

            LocateRegistry.createRegistry(1099);

            Reflections reflections = new Reflections("serviceImpl");
            Set<Class<?>> classesList = reflections.getTypesAnnotatedWith(RMIRemote.class);
            for (Class<?> clazz : classesList) {
                Remote toBeRegistered = (Remote) clazz.newInstance();
                String classFullName = clazz.getName();
                String className = classFullName.substring(
                        classFullName.lastIndexOf(".") + 1,
                        classFullName.length() - 4 // 因为全叫的xxxImpl
                );
                Naming.rebind("rmi://localhost:1099/" + className, toBeRegistered);
            }
            System.out.println("Server ready");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
