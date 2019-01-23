package presentation.mainpageui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import presentation.bookui.BookReaderUIController;
import presentation.recordui.ReturnBookUIController;

public class UserMainUIController extends BaseMainUIController {

    @FXML
    private void gotoOnlineReading(){
        root.showLogoutButton(false);
        BookReaderUIController.init(root);
    }

    @FXML
    private void gotoReturnBook(){
        root.showLogoutButton(false);
        ReturnBookUIController.init(root);
    }

    @FXML
    private void gotoUserInfo(){
        //root.showLogoutButton(false);
        //ReturnBookUIController.init(root);
    }


    // 加载文件和界面的方法******************************************

    public void instanceInit(RootUIController root){
        init(root);
    }

    public static void init(RootUIController root){
        try{
            // 加载登陆界面
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(UserMainUIController.class.getResource("UserMainUI.fxml"));
            root.setCenterPane(loader.load());

            UserMainUIController controller=loader.getController();
            root.showLogoutButton(true);
            controller.setRoot(root);
            //controller.setService(MessageBlFactory.getService());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
