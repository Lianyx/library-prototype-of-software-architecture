package dao.util;

import dao.CategoryDao;
import dao.RoleDao;
import object.enun.BookType;
import object.enun.Permission;
import object.po.Category;
import object.po.Role;

import java.lang.reflect.Field;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static dao.util.DaoFactory.getService;

public class Util {
    private static final String
            DB_NAME = "library_sa",
            USERNAME = "root",
            PASSWORD = "123456";

    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/" + DB_NAME + "?characterEncoding=UTF8",
                            USERNAME, PASSWORD);
            System.out.println("DB connected");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void executeVoidSql(String sql) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }

    public static PreparedStatement getStatement(String query) throws SQLException {
        return connection.prepareStatement(query);
    }

    public static void voidQuery(PreparedStatement ps, Object... args) throws SQLException {
        mapParams(ps, args);
        ps.execute();
    }

    public static <T> ArrayList<T> retrieveQuery(Class<T> clazz, PreparedStatement ps, Object... args) throws SQLException {
        mapParams(ps, args);
        ResultSet resultSet = ps.executeQuery();

        if (clazz.equals(String.class)) { // TODO 这个也
            ArrayList<T> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add((T) resultSet.getString("x"));
            }
            return list;
        }

        List<Field> fields = new ArrayList<>();
        getAllFields(fields, clazz);
        for (Field field : fields) field.setAccessible(true);

        ArrayList<T> list = new ArrayList<>();
        while (resultSet.next()) {
            try {
                T dto = clazz.getConstructor().newInstance();

                for (Field field : fields) {
                    String name = field.getName();
                    if (name.startsWith("_")) continue; // TODO 这个也不好，只是为了permissions

                    Class<?> type = field.getType();

                    if (type.equals(LocalDateTime.class)) {
                        String str = resultSet.getString(name); // 这个应该应用到所有
                        field.set(dto, str == null ? null : destringify(str));
                    } else if (type.equals(Integer.TYPE)) {
                        field.set(dto, resultSet.getInt(name));
                    } else if (type.equals(Double.TYPE)) {
                        field.set(dto, resultSet.getDouble(name));
                    } else if (type.equals(Category.class)) {
                        // TODO 这里感覺应该不去用Category的Dao
                        CategoryDao categoryDao = getService(CategoryDao.class);
                        Category category = categoryDao.selectById(
                                resultSet.getString(name + "Id"));
                        field.set(dto, category);
                    } else if (type.equals(Role.class)) {
                        RoleDao roleDao = getService(RoleDao.class);
                        Role role = roleDao.selectByType(
                                resultSet.getString(name));
                        field.set(dto, role);
                    } else if (type.equals(Permission.class)) {
                        String str = resultSet.getString(name);
                        for (Permission pm : Permission.values()) {
                            if (pm.toString().equals(str)) {
                                field.set(dto, pm);
                                break;
                            }
                        }
                    } else if (type.equals(BookType.class)) {
                        String str = resultSet.getString(name);
                        for (BookType bt : BookType.values()) {
                            if (bt.toString().equals(str)) {
                                field.set(dto, bt);
                                break;
                            }
                        }
                    } else {
                        field.set(dto, resultSet.getString(name));
                    }

                }
                list.add(dto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    /*
     * all private methods thereafter
     * */

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static String stringify(LocalDateTime dateTime) {
        return dateTime.format(formatter);
    }

    private static LocalDateTime destringify(String str) {
        if (str.contains(".")) str = str.substring(0, str.length() - 2);
        return LocalDateTime.parse(str, formatter);
    }

    // a lot of thanks to https://stackoverflow.com/questions/11777103/set-parameters-dynamically-to-prepared-statement-in-jdbc
    private static void mapParams(PreparedStatement ps, Object... args) throws SQLException {
        // 并不检查問号和arg的数量一致
        int i = 1;
        for (Object arg : args) {
            if (arg instanceof LocalDateTime) {
                ps.setString(i++, stringify((LocalDateTime) arg));
            } else if (arg instanceof Integer) {
                ps.setInt(i++, (Integer) arg);
            } else if (arg instanceof Double) {
                ps.setDouble(i++, (Double) arg);
            } else if (arg instanceof Category) {
                ps.setString(i++, ((Category) arg).getId());
            } else if (arg instanceof Role) {
                ps.setString(i++, ((Role) arg).getType());
            } else if (arg == null) {
                ps.setString(i++, null); // 这里insert下去的应该是null沒错
            } else { // enum也是直接就toString了
                ps.setString(i++, arg.toString());
            }
        }
    }

    // a lot thanks to https://stackoverflow.com/questions/1042798/retrieving-the-inherited-attribute-names-values-using-java-reflection
    private static void getAllFields(List<Field> fields, Class<?> type) {
        fields.addAll(Arrays.asList(type.getDeclaredFields()));

        if (type.getSuperclass() != null) {
            getAllFields(fields, type.getSuperclass());
        }
    }
}
