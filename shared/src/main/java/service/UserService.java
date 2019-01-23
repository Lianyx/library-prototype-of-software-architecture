package service;

import object.po.Role;
import object.po.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface UserService extends Remote {
    ArrayList<Role> getAllRoles() throws RemoteException;
    ArrayList<User> getAllUsers() throws RemoteException;
    ArrayList<User> searchUser(String keyword) throws RemoteException;
    void addUser(User user) throws RemoteException;
    void updateUser(User user) throws RemoteException;
}
