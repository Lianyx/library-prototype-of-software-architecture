package launcher;

import  annotation.RMIRemote;
import dao.RoleDao;
import object.po.Role;
import org.reflections.Reflections;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;
import java.util.Set;

import static dao.util.Util.*;
import static dao.util.DaoFactory.getService;

public class Main {
    public static void initializeData() throws SQLException {
        executeVoidSql("delete from Book");
        executeVoidSql("delete from Category");
        executeVoidSql("delete from Record");
        executeVoidSql("delete from Role");
        executeVoidSql("delete from User");
        executeVoidSql("delete from UserPermission");
        executeVoidSql("delete from RoleCategory");

        ////    TEACHER(15, 40, Category.values()),
////    GRAD(10, 20, Category.CT_CS, Category.CT_HISTORY),
////    UNDERGRAD(5, 10, Category.values()),
////    ADMIN(0, 0),
////    GUEST(0, 0);

        RoleDao roleDao = getService(RoleDao.class);

//        roleDao.insert(new Role("TEACHER", 15, 40));
//        roleDao.insert(new Role("GRAD", 10, 20));
//        roleDao.insert();




    }

    public static void main(String[] args) {
        try {
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
