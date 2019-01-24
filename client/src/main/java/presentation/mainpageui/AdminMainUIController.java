package presentation.mainpageui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import presentation.bookui.AdminBookUIController;
import presentation.recordui.RecordManagementUIController;
import presentation.userui.UserManagementUIController;


public class AdminMainUIController extends BaseMainUIController {

    @FXML
    private void gotoBookManagement(){
        root.showLogoutButton(false);
        AdminBookUIController.init(root);
    }

    @FXML
    private void gotoUserManagement(){
        root.showLogoutButton(false);
        UserManagementUIController.init(root);
    }

    @FXML
    private void gotoRecordManagement(){
        root.showLogoutButton(false);
        RecordManagementUIController.init(root);
    }


    // 加载文件和界面的方法******************************************

    public void instanceInit(RootUIController root){
        init(root);
    }

    public static void init(RootUIController root){
        try{
            // 加载登陆界面
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(AdminMainUIController.class.getResource("AdminMainUI.fxml"));
            root.setCenterPane(loader.load());

            AdminMainUIController controller=loader.getController();
            root.showLogoutButton(true);
            controller.setRoot(root);
            //controller.setService(MessageBlFactory.getService());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
