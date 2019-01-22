package service;

import object.enun.Permission;
import object.enun.Role;
import object.po.Book;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AdminService extends Remote {
    void createUser(String username, String password, Role role) throws RemoteException;

    void updateBook(Book book) throws RemoteException;

    void getBorrowRecords() throws RemoteException; // TODO

    void grantUser(String username, Permission permission) throws RemoteException;
}
