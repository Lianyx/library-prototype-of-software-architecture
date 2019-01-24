package observer;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Observable;
import java.util.Observer;

public class WrappedObserver implements Observer, Serializable {
    private RemoteObserver ro;

    public WrappedObserver(RemoteObserver ro) {
        this.ro = ro;
    }

    @Override
    public void update(Observable o, Object arg) {
        try {
            ro.update(o, arg);
        } catch (RemoteException e) {
            System.out.println("Remote exception removing observer:" + this);
            o.deleteObserver(this);
            // 不是很懂
        }
    }
}
