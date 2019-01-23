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
import blservice.UserBlService;
import presentation.uitools.UITool;
import utils.UIType;
import utils.UserType;
import vo.UserVO;

@Data
public class UserInfoUIController {
    private UserVO user;
    private UserBlService userBlService;

    @FXML
    private TextField ID; // 用户编号
    @FXML
    private TextField type; // 用户类别
    @FXML
    private TextField name; // 姓名
    @FXML
    private TextField password; // 密码
    @FXML
    private TextField borrowingNumber; // 可借阅书本数
    @FXML
    private TextField borrowingDays; // 可借阅时长

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
            user.setType(typeList[newValue.intValue()]);
        });

    }

    // 设置controller数据的方法*****************************************

    private void setUser(UserVO user) {
        this.user = user;
        ID.setText(user.getId());
        type.setText(user.getType());
        name.setText(user.getName());
        password.setText(user.getPassword());
        borrowingNumber.setText(String.valueOf(user.getBorrowingNumber()));
        borrowingDays.setText(String.valueOf(user.getBorrowingDays()));
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

        if (type.getText().length() == 0) {
            errorMessage += ("未选择用户类型。" + System.lineSeparator());
        }
        if (name.getText().length() == 0) {
            errorMessage += ("未输入用户姓名。" + System.lineSeparator());
        }
        if (password.getText().length() == 0) {
            errorMessage += ("未输入用户权限。" + System.lineSeparator());
        }
        if (borrowingNumber.getText().length() == 0) {
            errorMessage += ("未输入用户可借阅书本数。" + System.lineSeparator());
        }
        if (borrowingDays.getText().length() == 0) {
            errorMessage += ("未输入用户可借阅天数。" + System.lineSeparator());
        }
        else {
            try {
                int i = Integer.parseInt(borrowingNumber.getText());
                if (i <= 0)
                    throw new NumberFormatException();
            } catch (NumberFormatException e) {
                errorMessage += ("用户可借阅书本数必须是正整数。" + System.lineSeparator());
            }

            try {
                int i = Integer.parseInt(borrowingDays.getText());
                if (i <= 0)
                    throw new NumberFormatException();
            } catch (NumberFormatException e) {
                errorMessage += ("用户可借阅天数必须是正整数。" + System.lineSeparator());
            }
        }

        if (errorMessage.length() == 0){
            user.setType(type.getText());
            user.setName(name.getText());
            user.setPassword(password.getText());
            user.setBorrowingNumber(Integer.parseInt(borrowingNumber.getText()));
            user.setBorrowingDays(Integer.parseInt(borrowingDays.getText()));
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
    public static void init(UserBlService service, UserVO user, UIType type, Stage stage){
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
            controller.setUserBlService(service);
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