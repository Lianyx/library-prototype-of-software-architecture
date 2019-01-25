package presentation.userui;

import factory.ServiceFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lombok.Setter;
import object.po.User;
import presentation.mainpageui.AdminMainUIController;
import presentation.mainpageui.RootUIController;
import presentation.uitools.CenterUIController;
import presentation.uitools.UITool;
import service.UserService;
import utils.UIType;

import java.rmi.RemoteException;
import java.util.ArrayList;

@Setter
public class UserManagementUIController extends CenterUIController {
    private UserService userService;

    private ObservableList<User> userObservableList= FXCollections.observableArrayList();
    @FXML
    private TableView<User> userTableView;
    @FXML
    private TableColumn<User,String> usernameColumn;
    @FXML
    private TableColumn<User,String> userRoleColumn;
    @FXML
    private TableColumn<User, String> userDebtColumn;

    @FXML
    private TextField searchInfo;


    public void initialize(){
        usernameColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getUsername()));
        userRoleColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getRole().getType()));
        userDebtColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().getDebt())));
    }

    private void refresh(String keyword){
        try {
            ArrayList<User> userList;
            if (keyword == null || keyword.equals("")) {
                userList = userService.getAllUsers();
            } else {
                userList = userService.searchUser(keyword);
            }
            showUserList(userList);
        } catch (RemoteException e){
            UITool.showAlert(Alert.AlertType.ERROR,
                    "Error", "查找用户失败", "服务器连接错误");
        }
    }

    /**
     * 取得用户列表并修改ObservableList的信息
     * */
    private void showUserList(ArrayList<User> userList){
        userObservableList.clear();
        userObservableList.setAll(userList);
        userTableView.setItems(userObservableList);
    }

    // 界面之中会用到的方法******************************************

    @FXML
    private void handleSearch(){
        refresh(searchInfo.getText());
    }

    @FXML
    private void handleAddUser(){
        UserInfoUIController.init(userService, new User(), UIType.ADD, root.getStage());
        refresh("");
    }

    @FXML
    private void handleEditUser(){
        if(isUserSelected()){
            User user = userTableView.getSelectionModel().getSelectedItem();
            UserInfoUIController.init(userService, user, UIType.ADMIN_EDIT, root.getStage());
        }
        refresh("");
    }

    private boolean isUserSelected(){
        int selectedIndex = userTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            return true;
        } else {
            UITool.showAlert(Alert.AlertType.ERROR,
                    "No Selection", "未选中用户", "请在表中选择用户");
            return false;
        }
    }

    public void instanceInit(RootUIController root){
        init(root);
    }

    /**
     * 静态初始化方法，加载相应的FXML文件，并添加一些信息
     * */
    public static void init(RootUIController root){
        try{
            // 加载登陆界面
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(UserManagementUIController.class.getResource("UserManagementUI.fxml"));
            root.setCenterPane(loader.load());

            UserManagementUIController controller=loader.getController();
            controller.setRoot(root);
            controller.setUserService(ServiceFactory.getService(UserService.class));
            controller.refresh("");
            root.setReturnPaneController(new AdminMainUIController());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
