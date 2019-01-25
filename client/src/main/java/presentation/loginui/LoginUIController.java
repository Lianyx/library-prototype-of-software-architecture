package presentation.loginui;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import factory.ServiceFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lombok.Setter;
import object.exception.InvalidLoginException;
import object.po.User;
import presentation.mainpageui.AdminMainUIController;
import presentation.mainpageui.RootUIController;
import presentation.mainpageui.UserMainUIController;
import presentation.uitools.UITool;
import service.LoginService;
import java.io.IOException;
import java.rmi.RemoteException;

@Setter
public class LoginUIController {

    @FXML
    private JFXTextField userIdField;
    @FXML
    private JFXPasswordField passwordField;

    private LoginService loginService;

    private Stage stage;

    @FXML
    private void handleLogin() throws RemoteException {
        String username = userIdField.getText();
        String password = passwordField.getText();
        try {
            if (loginService == null) {
                loginService = ServiceFactory.getService(LoginService.class);
            }
            User user = loginService.login(username, password);

            stage.close();
            Stage newStage=new Stage();
            newStage.setTitle("图书馆信息管理系统");
            RootUIController root = RootUIController.initRoot(newStage, user);
            root.showLogoutButton(true);

            if (user.getRole().getType().equals("管理员")) {
                AdminMainUIController.init(root);
            } else {
                UserMainUIController.init(root);
            }
        } catch (InvalidLoginException e) {
            UITool.showAlert(Alert.AlertType.ERROR, "Error", "登陆失败", "用户名或密码错误");
        } catch(RemoteException e){
            UITool.showAlert(Alert.AlertType.ERROR, "Error", "登陆失败", "服务器连接错误");
        }
    }

    @FXML
    private void handleExit() throws RemoteException {
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

            LoginUIController controller = loader.getController();
            controller.setLoginService(ServiceFactory.getService(LoginService.class));
            controller.setStage(stage);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}