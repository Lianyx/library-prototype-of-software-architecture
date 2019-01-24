package presentation.userui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Data;
import lombok.Setter;
import object.po.Role;
import object.po.User;
import presentation.uitools.UITool;
import service.UserService;
import utils.UIType;
import utils.UserType;

@Setter
public class UserInfoUIController {
    private User user;
    private UserService userService;

    @FXML
    private TextField username;
    @FXML
    private TextField type;
    @FXML
    private TextField password; // 密码
    @FXML
    private TextField debt;
    @FXML
    private TextField maximum;
    @FXML
    private TextField dayLimit;
    @FXML
    private TextField categories;
    @FXML
    private TextField permission;

    @FXML
    private ChoiceBox<String> typeChoiceBox;
    @FXML
    private Button confirm;
    @FXML
    private Button cancel;

    private Stage dialogStage;

    public void initialize(){
        String[] typeList = UserType.getUserTypeList();
        typeChoiceBox.setItems(FXCollections.observableArrayList(typeList));
        typeChoiceBox.getSelectionModel().selectedIndexProperty().addListener((ov,oldValue,newValue)->{
            type.setText(typeList[newValue.intValue()]);
            //user.getRole().setType(typeList[newValue.intValue()]);
        });

    }

    // 设置controller数据的方法*****************************************

    private void setUser(User user) {
        this.user = user;
        username.setText(user.getUsername());
        password.setText(user.getPassword());
        debt.setText(String.valueOf(user.getDebt()));
        permission.setText(String.valueOf(user.getPermissions()));
        //setRole(user.getRole());
    }

    private void setRole(Role role) {
        type.setText(role.getType());
        maximum.setText(String.valueOf(role.getMaximum()));
        dayLimit.setText(String.valueOf(role.getDayLimit()));
        categories.setText(String.valueOf(role.getCategories()));
    }

    private void setPaneType(UIType type) {
        if (type.equals(UIType.ADD)) {
            confirm.setText("添加");
        }
        else if (type.equals(UIType.USER_EDIT)) {
            confirm.setText("编辑");
            typeChoiceBox.setDisable(true);
        }
        else if (type.equals(UIType.ADMIN_EDIT)) {
            confirm.setText("编辑");
        }
    }

    // 界面之中会用到的方法******************************************

    @FXML
    private void handleConfirm(){
//        if(isInputValid()){
//            String text=confirm.getText();
//
//            try{
//                if(text.equals("添加")){
//                    String userID=userBlService.addUser(user);
//                    String userName=user.getName();
//
//                    UITool.showAlert(Alert.AlertType.INFORMATION,
//                            "Success","添加用户成功",
//                            "用户ID："+userID+System.lineSeparator()+"名字："+userName);
//                }
//                else if(text.equals("编辑")){
//                    userBlService.editUser(user);
//                    String userID=user.getID();
//                    String userName=user.getName();
//
//                    UITool.showAlert(Alert.AlertType.INFORMATION,
//                            "Success","编辑用户成功",
//                            "用户ID："+userID+System.lineSeparator()+"名字："+userName);
//                }
//
//                dialogStage.close();
//            }catch(DataException e){
//                UITool.showAlert(Alert.AlertType.ERROR,
//                        "Error",text+"用户失败", "数据库错误");
//            }catch(NotExistException e){
//                UITool.showAlert(Alert.AlertType.ERROR,
//                        "Error",text+"用户失败","用户不存在");
//            }catch(ExistException e){
//                UITool.showAlert(Alert.AlertType.ERROR,
//                        "Error",text+"用户失败","账号和已有用户重复");
//            }catch(Exception e){
//                UITool.showAlert(Alert.AlertType.ERROR,
//                        "Error",text+"用户失败","RMI连接错误");
//            }
//        }
    }

    @FXML
    private void handleCancel(){
        dialogStage.close();
    }

    /**
     * 检查用户信息的输入是否完整且合法
     * 完整且合法返回true
     * */
    private boolean isInputValid(){
        String errorMessage = "";

        if (username.getText().length() == 0) {
            errorMessage += ("未输入用户名。" + System.lineSeparator());
        }
        if (type.getText().length() == 0) {
            errorMessage += ("未选择用户类型。" + System.lineSeparator());
        }
        if (password.getText().length() == 0) {
            errorMessage += ("未输入用户密码。" + System.lineSeparator());
        }
        if (debt.getText().length() == 0) {
            errorMessage += ("未输入用户欠款。" + System.lineSeparator());
        }
        else {
            try {
                double d = Double.parseDouble(debt.getText());
                if (d <= 0)
                    throw new NumberFormatException();
            } catch (NumberFormatException e) {
                errorMessage += ("用户欠款必须是非负数。" + System.lineSeparator());
            }
        }

        if (errorMessage.length() == 0){
            user.setUsername(username.getText());
            user.setPassword(password.getText());
            user.setDebt(Double.parseDouble(debt.getText()));
            return true;
        } else {
            UITool.showAlert(Alert.AlertType.ERROR, "用户信息错误","请检查用户信息的输入", errorMessage);
            return false;
        }
    }

    // 加载文件和界面的方法******************************************

    /**
     * 静态初始化方法，加载相应的FXML文件，并添加一些信息
     * */
    public static void init(UserService service, User user, UIType type, Stage stage){
        try{
            // 加载登陆界面
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(UserInfoUIController.class.getResource("UserInfoUI.fxml"));

            // Create the dialog stage
            Stage dialogStage = new Stage();
            dialogStage.setResizable(false);
            dialogStage.setTitle("用户信息界面");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);
            dialogStage.setScene(new Scene(loader.load()));

            UserInfoUIController controller=loader.getController();
            controller.setUserService(service);
            controller.setUser(user);
            controller.setDialogStage(dialogStage);
            controller.setPaneType(type);

            // Show the dialog and wait until the user closes it.
            dialogStage.showAndWait();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}