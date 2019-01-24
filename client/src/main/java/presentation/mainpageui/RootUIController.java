package presentation.mainpageui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.Data;
import object.po.User;
import presentation.loginui.LoginUIController;
import presentation.uitools.CenterUIController;
import utils.SystemInfo;

@Data
public class RootUIController {
    private Stage stage;
    private User operator;
    private BorderPane rootPane;
    private CenterUIController returnPaneController;

    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label type;
    @FXML
    private Button logout;
    @FXML
    private Button exit;

    private void setStage(Stage stage) {
        this.stage = stage;
    }

    private void setOperator(User operator) {
        this.operator = operator;
        name.setText(operator.getUsername());
        type.setText(operator.getRole().getType());
    }

    public void setCenterPane(AnchorPane centerPane){
        rootPane.setCenter(centerPane);
    }

    public void showLogoutButton(boolean state){
        logout.setVisible(state);
        exit.setVisible(!state);
    }

    private void setClose(){
        stage.setOnCloseRequest(event-> logout());
    }

    private void logout(){

    }

    @FXML
    private void handleLogout(){
        logout();
        stage.close();
        Stage newStage=new Stage();
        newStage.setTitle(SystemInfo.SYSTEM_NAME.getValue());
        LoginUIController.init(newStage);
    }

    @FXML
    private void handleExit(){
        returnPaneController.instanceInit(this);
    }

    public static RootUIController initRoot(Stage stage, User operator){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(RootUIController.class.getResource("RootUI.fxml"));
            BorderPane rootPane = loader.load();
            Scene scene = new Scene(rootPane);
            stage.setScene(scene);
            stage.show();

            RootUIController controller = loader.getController();
            controller.setStage(stage);
            controller.setRootPane(rootPane);
            controller.setOperator(operator);
            controller.setClose();

            return controller;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
