package presentation.mainpageui;

import doc.MessageDoc;
import factory.ServiceFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import object.po.Message;
import observer.RmiService;
import presentation.uitools.CenterUIController;
import presentation.uitools.UITool;
import service.MessageService;

import java.rmi.RemoteException;
import java.util.ArrayList;


public abstract class BaseMainUIController extends CenterUIController {

    private MessageService messageService;
    RmiService rmiService;

    @FXML
    protected TextArea messageArea;

    public void initialize(){
        messageService = ServiceFactory.getService(MessageService.class);
        rmiService = ServiceFactory.getService(RmiService.class);
    }

    protected void refreshMessage(){
        try{
            ArrayList<Message> messageList = messageService.getByUsername(root.getOperator().getUsername());
            UITool.showMessage(messageArea, messageList);
        } catch(RemoteException e){
            UITool.showAlert(Alert.AlertType.ERROR, "Error", "获取系统信息失败", "服务器连接错误");
        }
    }

    @FXML
    protected void handleMessage(){
        refreshMessage();
    }

    @FXML
    protected void clearMessage(){
        ButtonType buttonType = UITool.showAlert(Alert.AlertType.CONFIRMATION,
                "确认", "是否清空系统信息？", "此操作无法撤回");
        if(buttonType.equals(ButtonType.OK)){
            try{
                messageService.clear(root.getOperator().getUsername());
                UITool.showAlert(Alert.AlertType.INFORMATION,"Success", "清空系统信息成功", "重新刷新系统信息");
                refreshMessage();
            } catch(Exception e){
                UITool.showAlert(Alert.AlertType.ERROR,
                        "Error", "清空系统信息失败", "服务器连接错误");
            }
        }
    }

}
