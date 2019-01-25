package presentation.mainpageui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import presentation.uitools.CenterUIController;
import service.MessageService;


public abstract class BaseMainUIController extends CenterUIController {
    protected int messageNumber = 0;


    // 设置controller数据的方法*****************************************

//    public void setService(MessageBlService service){
//        this.service=service;
//        refreshMessage();
//    }

    private void refreshMessage(){
//        try{
//            ArrayList<MessageVO> messageList=service.getMessageList(root.getOperator());
//            messageNumber=messageList.size();
//            UITool.showMessage(messageArea,messageList);
//        }catch(DataException e){
//            UITool.showAlert(Alert.AlertType.ERROR,
//                    "Error","获取系统信息失败","数据库错误");
//        }catch(Exception e){
//            UITool.showAlert(Alert.AlertType.ERROR,
//                    "Error","获取系统信息失败","RMI连接错误");
//        }
    }

    // 界面之中会用到的方法******************************************

//    @FXML
//    private void handleMessage(){
//        refreshMessage();
//    }
//
//    @FXML
//    private void clearMessage(){
//        ButtonType buttonType=UITool.showAlert(Alert.AlertType.CONFIRMATION,
//                "确认", "是否清空系统信息？","此操作无法撤回");
//        if(buttonType.equals(ButtonType.OK)){
//            try{
//                service.deleteMessage(root.getOperator().getID(),messageNumber);
//                UITool.showAlert(Alert.AlertType.INFORMATION,"Success","清空系统信息成功","重新刷新系统信息");
//                refreshMessage();
//            }catch(DataException e){
//                UITool.showAlert(Alert.AlertType.ERROR,
//                        "Error","清空系统信息失败","数据库错误");
//            }catch(Exception e){
//                UITool.showAlert(Alert.AlertType.ERROR,
//                        "Error","清空系统信息失败","RMI连接错误");
//            }
//        }
//    }
//
}
