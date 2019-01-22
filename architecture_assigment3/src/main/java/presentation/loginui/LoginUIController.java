package presentation.loginui;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import presentation.mainpageui.AdminMainUIController;
import presentation.mainpageui.RootUIController;
import presentation.mainpageui.UserMainUIController;
import utils.SystemInfo;
import utils.UserType;
import vo.UserVO;

import java.io.IOException;

public class LoginUIController {

    @FXML
    private JFXTextField userIdField;
    @FXML
    private JFXPasswordField passwordField;

    private Stage stage;

    private void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void handleLogin(){
        UserVO user = new UserVO();
        user.setId("233");
        user.setName("陈骁");
        user.setType(UserType.ADMINISTRATOR.getName());

        stage.close();
        Stage newStage = new Stage();
        newStage.setResizable(false);
        newStage.setTitle(SystemInfo.SYSTEM_NAME.getValue());
        RootUIController root = RootUIController.initRoot(newStage, user);
        root.showLogoutButton(true);

        if (userIdField.getText().equals("admin")) {
            AdminMainUIController.init(root);
        } else {
            UserMainUIController.init(root);
        }
    }

    @FXML
    private void handleExit(){
        // 这里还有一个登出操作没有写
        System.exit(0);
    }

    public static void init(Stage stage){
        try{
            // 加载登陆界面
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(LoginUIController.class.getResource("LoginUI.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

            LoginUIController controller=loader.getController();
            controller.setStage(stage);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}