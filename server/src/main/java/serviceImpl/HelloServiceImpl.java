package serviceImpl;

import service.HelloService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloServiceImpl extends UnicastRemoteObject implements HelloService {
    public HelloServiceImpl() throws RemoteException {
    }

    public String helloTo(String name) throws RemoteException {
        return "Hello, " + name;
    }
}
