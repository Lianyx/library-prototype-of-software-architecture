package observer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ObserverMain {
    public static void main(String[] args) {
        RmiService remoteService = null;
        try {
            remoteService = (RmiService) Naming.lookup("rmi://localhost:1099/RmiService");

            RmiClient client = new RmiClient();
            remoteService.addObserver(client);
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            e.printStackTrace();
        }
    }
}
