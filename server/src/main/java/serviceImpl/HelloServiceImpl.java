package serviceImpl;

import annotation.RMIRemote;
import service.HelloService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

@RMIRemote
public class HelloServiceImpl extends UnicastRemoteObject implements HelloService {

    public HelloServiceImpl() throws RemoteException {
    }

    public String helloTo(String name) throws RemoteException {
        return "Hello, " + name;
    }
}
