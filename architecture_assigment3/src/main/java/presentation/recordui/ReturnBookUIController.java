package presentation.recordui;

import com.qoppa.pdf.PDFException;
import com.qoppa.pdfViewerFX.PDFViewer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import presentation.bookui.BaseBookUIController;
import presentation.mainpageui.RootUIController;
import presentation.mainpageui.UserMainUIController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ReturnBookUIController extends BaseRecordUIController {

    /**
     * 刷新界面，取得所有用户的列表，并显示在tableview中
     * */
//    private void refresh(UserQueryVO query){
//        try {
//            ArrayList<UserVO> userList = userBlService.getUserList(query);
//            showUserList(userList);
//        }catch(DataException e){
//            UITool.showAlert(Alert.AlertType.ERROR,
//                    "Error","查找用户失败", "数据库错误");
//        }catch(Exception e){
//            UITool.showAlert(Alert.AlertType.ERROR,
//                    "Error","查找用户失败","RMI连接错误");
//        }
//    }



    // 界面之中会用到的方法******************************************

    @FXML
    private void handleSearch(){
//        String text=searchInfo.getText();
//        if(text.equals("")){
//            refresh(null);
//        }
//        else{
//            UserQueryVO query=new UserQueryVO(text,text);
//            refresh(query);
//        }
    }

    @FXML
    private void handleReturnBook() {
        if (isRecordSelected()) {

        }
    }

    public void instanceInit(RootUIController root) {
        init(root);
    }

    /**
     * 静态初始化方法，加载相应的FXML文件，并添加一些信息
     * */
    public static void init(RootUIController root){
        try{
            // 加载登陆界面
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ReturnBookUIController.class.getResource("ReturnBookUI.fxml"));
            root.setCenterPane(loader.load());

            ReturnBookUIController controller = loader.getController();
            controller.setRoot(root);
            //controller.setUserBlService(UserBlFactory.getService());
            //controller.refresh(null);
            root.setReturnPaneController(new UserMainUIController());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
