package serviceImpl;

import annotation.RMIRemote;
import object.po.Role;
import object.po.User;
import service.UserService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

@RMIRemote
public class UserServiceImpl extends UnicastRemoteObject implements UserService {
    public UserServiceImpl() throws RemoteException {
    }

    @Override
    public ArrayList<Role> getAllRoles() throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<User> getAllUsers() throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<User> searchUser(String keyword) throws RemoteException {
        return null;
    }

    @Override
    public void addUser(User user) throws RemoteException {

    }

    @Override
    public void updateUser(User user) throws RemoteException {

    }
}
