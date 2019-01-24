package presentation.bookui;

import com.qoppa.pdf.PDFException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import presentation.mainpageui.RootUIController;
import presentation.mainpageui.UserMainUIController;
import presentation.readerui.HTMLReaderUIController;
import utils.FileTool;

import java.io.File;
import java.io.FileNotFoundException;

public class UserBookUIController extends BaseBookUIController {

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
    private void handleReadBook() {
        HTMLReaderUIController.init(root.getStage());
    }

    @FXML
    private void handleBorrowBook(){
        if (isBookSelected()) {

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
            loader.setLocation(UserBookUIController.class.getResource("UserBookUI.fxml"));
            root.setCenterPane(loader.load());

            UserBookUIController controller = loader.getController();
            controller.setRoot(root);
            //controller.setBookService(ServiceFactory.getBookService());
            //controller.refresh(null);
            root.setReturnPaneController(new UserMainUIController());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
