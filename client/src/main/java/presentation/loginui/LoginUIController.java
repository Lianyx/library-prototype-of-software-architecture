package presentation.loginui;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.qoppa.pdf.PDFException;
import com.qoppa.pdfViewerFX.PDFViewer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import object.po.User;
import presentation.mainpageui.AdminMainUIController;
import presentation.mainpageui.RootUIController;
import presentation.mainpageui.UserMainUIController;
import utils.SystemInfo;
import utils.UserType;
import vo.UserVO;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
        User user = new User();

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
    private void handleExit() {
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