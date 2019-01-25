package doc;

import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import object.po.Message;
import observer.RemoteObserver;
import presentation.uitools.UITool;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class MessageDoc extends UnicastRemoteObject implements RemoteObserver {

    private TextArea textArea;

    public MessageDoc(TextArea textArea) throws RemoteException {
        this.textArea = textArea;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void update(Object observable, Object updateMsg) throws RemoteException {
        ArrayList<Message> messages = (ArrayList<Message>) updateMsg;
        textArea.setText(String.valueOf(messages));
        UITool.showAlert(Alert.AlertType.INFORMATION, "观察者", "执行了", "23俄");
    }
    // 测试可以先开main，然后开Observermain，去掉test中@beforeClass，然后跑testMessage（去掉最後测clear的）
    // 于是client会收到一次提示
}