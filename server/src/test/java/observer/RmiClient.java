package observer;

import object.po.Message;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class RmiClient extends UnicastRemoteObject implements RemoteObserver {

    public RmiClient() throws RemoteException {
    }

    @Override
    @SuppressWarnings("unchecked")
    public void update(Object observable, Object updateMsg) throws RemoteException {
        ArrayList<Message> messages = (ArrayList<Message>) updateMsg;

        System.out.println("got message: " + messages);
    }
    // 测试可以先开main，然后开Observermain，去掉test中@beforeClass，然后跑testMessage
    // 于是client会收到一次提示
}