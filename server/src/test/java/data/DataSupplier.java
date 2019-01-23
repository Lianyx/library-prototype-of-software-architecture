package data;

import dao.BookDao;
import dao.CategoryDao;
import dao.RoleDao;
import dao.UserDao;
import object.po.Book;
import object.po.Category;
import object.po.Role;
import object.po.User;
import service.UserService;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static dao.util.DaoFactory.getService;
import static dao.util.Util.executeVoidSql;

public class DataSupplier {
    public static Category
            history = new Category("HS", "历史"),
            cs = new Category("CS", "计算机"),
            math = new Category("MATH", "数学"),
            classics = new Category("CL", "古典学");

    public static Role
            teacher = new Role("老师", 15, 40, new HashSet<>(Arrays.asList(history, cs, math, classics))),
            undergraduate = new Role("本科生", 5, 10, new HashSet<>(Arrays.asList(cs, math))),
            graduate = new Role("研究生", 10, 20, new HashSet<>(Arrays.asList(history, cs, math))),
            admin = new Role("管理员", 0, 0, new HashSet<>(Collections.emptyList()));

    public static User
            MrWang = new User("MrWang", "123", teacher),
            GradZhang = new User("Zhang", "123", graduate),
            UnGradLi = new User("Li", "123", undergraduate),
            adminUser = new User("admin", "123", admin);

    public static Book
            network = new Book("NW", "计算机网络", "谢某", null, null, cs),
            calculus = new Book("CCL", "微积分计算", "肖", null, null, math);

    public static Set<Category> categories = new HashSet<>(Arrays.asList(history, cs, math, classics));
    public static Set<Role> roles = new HashSet<>(Arrays.asList(teacher, undergraduate, graduate, admin));
    public static Set<User> users = new HashSet<>(Arrays.asList(MrWang, GradZhang, UnGradLi, adminUser));
    public static Set<Book> books = new HashSet<>(Arrays.asList(network, calculus));

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
        categoryDao.insert(history);
        categoryDao.insert(cs);
        categoryDao.insert(math);
        categoryDao.insert(classics);

        RoleDao roleDao = getService(RoleDao.class);
        roleDao.insert(teacher);
        roleDao.insert(undergraduate);
        roleDao.insert(graduate);
        roleDao.insert(admin);

        UserDao userDao = getService(UserDao.class);
        userDao.insert(MrWang);
        userDao.insert(GradZhang);
        userDao.insert(UnGradLi);
        userDao.insert(adminUser);

        BookDao bookDao = getService(BookDao.class);
        Book network = new Book("NW", "计算机网络", "谢某", null, null, cs);
        Book calculus = new Book("CCL", "微积分计算", "肖", null, null, math);
        bookDao.insert(network);
        bookDao.insert(calculus);
    }
}
