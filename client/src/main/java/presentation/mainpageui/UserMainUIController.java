package presentation.mainpageui;

import factory.ServiceFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import object.po.User;
import presentation.bookui.UserBookUIController;
import presentation.recordui.ReturnBookUIController;
import presentation.userui.UserInfoUIController;
import service.UserService;
import utils.UIType;

public class UserMainUIController extends BaseMainUIController {

    @FXML
    private void gotoOnlineReading(){
        root.showLogoutButton(false);
        UserBookUIController.init(root);
    }

    @FXML
    private void gotoReturnBook(){
        root.showLogoutButton(false);
        ReturnBookUIController.init(root);
    }

    @FXML
    private void gotoUserInfo(){
        UserInfoUIController.init(ServiceFactory.getService(UserService.class),
                root.getOperator(), UIType.USER_EDIT, root.getStage());
    }


    public void instanceInit(RootUIController root){
        init(root);
    }

    public static void init(RootUIController root){
        try{
            // 加载登陆界面
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(UserMainUIController.class.getResource("UserMainUI.fxml"));
            root.setCenterPane(loader.load());

            UserMainUIController controller=loader.getController();
            root.showLogoutButton(true);
            controller.setRoot(root);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
