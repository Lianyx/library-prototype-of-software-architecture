package serviceImpl;

import annotation.RMIRemote;
import dao.RoleDao;
import dao.UserDao;
import object.exception.AlreadyExistException;
import object.po.Role;
import object.po.User;
import service.UserService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

import static dao.util.DaoFactory.getService;

@RMIRemote
public class UserServiceImpl extends UnicastRemoteObject implements UserService {
    private UserDao userDao;
    private RoleDao roleDao;

    public UserServiceImpl() throws RemoteException {
        userDao = getService(UserDao.class);
        roleDao = getService(RoleDao.class);
    }

    @Override
    public ArrayList<Role> getAllRoles() throws RemoteException {
        try {
            return roleDao.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<User> getAllUsers() throws RemoteException {
        try {
            return userDao.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<User> searchUser(String keyword) throws RemoteException {
        try {
            return userDao.search(keyword);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addUser(User user) throws RemoteException {
        try {
            if (null != userDao.selectByUsername(user.getUsername())) {
                throw new AlreadyExistException();
            }
            userDao.insert(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) throws RemoteException {
        // 不可能invalid。。
        try {
            userDao.update(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
