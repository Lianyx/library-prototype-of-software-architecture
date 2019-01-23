package serviceImpl;

import annotation.RMIRemote;
import dao.UserDao;
import object.exception.InvalidLoginException;
import object.po.User;
import service.LoginService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

import static dao.util.DaoFactory.getService;

@RMIRemote
public class LoginServiceImpl extends UnicastRemoteObject implements LoginService {
    private UserDao userDao;

    public LoginServiceImpl() throws RemoteException {
        userDao = getService(UserDao.class);
    }

    @Override
    public void login(String username, String password) throws RemoteException {
        try {
            User user = userDao.selectByUsername(username);
            if (user == null || ! user.getPassword().equals(password)) {
                throw new InvalidLoginException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
