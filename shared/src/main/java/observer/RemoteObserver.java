package observer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Observable;

public interface RemoteObserver extends Remote {
    void update(Object observable, Object updateMsg) throws RemoteException;
}
