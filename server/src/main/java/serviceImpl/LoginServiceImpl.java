package serviceImpl;

import annotation.RMIRemote;
import service.LoginService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

@RMIRemote
public class LoginServiceImpl extends UnicastRemoteObject implements LoginService {
    public LoginServiceImpl() throws RemoteException {
    }

    @Override
    public void login(String username, String password) throws RemoteException {

    }
}
