package factory;

import service.*;
import utils.SystemInfo;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ServiceFactory {

    public static BookService getBookService() throws RemoteException, NotBoundException, MalformedURLException {
        return (BookService) Naming.lookup(SystemInfo.RML_BASE_URL.getValue() + "BookService");
    }

    public static BorrowService getBorrowService() throws RemoteException, NotBoundException, MalformedURLException {
        return (BorrowService) Naming.lookup(SystemInfo.RML_BASE_URL.getValue() + "BorrowService");
    }

    public static LoginService getLoginService() throws RemoteException, NotBoundException, MalformedURLException {
        return (LoginService) Naming.lookup(SystemInfo.RML_BASE_URL.getValue() + "LoginService");
    }

    public static MessageService getMessageService() throws RemoteException, NotBoundException, MalformedURLException {
        return (MessageService) Naming.lookup(SystemInfo.RML_BASE_URL.getValue() + "MessageService");
    }

    public static ReaderService getReaderService() throws RemoteException, NotBoundException, MalformedURLException {
        return (ReaderService) Naming.lookup(SystemInfo.RML_BASE_URL.getValue() + "ReaderService");
    }

    public static UserService getUserService() throws RemoteException, NotBoundException, MalformedURLException {
        return (UserService) Naming.lookup(SystemInfo.RML_BASE_URL.getValue() + "UserService");
    }


}
