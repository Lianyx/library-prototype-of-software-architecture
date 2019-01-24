package presentation.userui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import object.po.User;
import presentation.mainpageui.AdminMainUIController;
import presentation.mainpageui.RootUIController;
import presentation.uitools.CenterUIController;
import presentation.uitools.UITool;
import utils.UIType;
import vo.UserVO;

import java.util.ArrayList;

public class UserManagementUIController extends CenterUIController {
    //private UserBlService userBlService;

    private ObservableList<UserVO> userObservableList= FXCollections.observableArrayList();
    @FXML
    private TableView<UserVO> userTableView;
    @FXML
    private TableColumn<UserVO,String> userIDColumn;
    @FXML
    private TableColumn<UserVO,String> userNameColumn;
    @FXML
    private TableColumn<UserVO,String> userTypeColumn;

    @FXML
    private TextField searchInfo;

    // 加载文件后调用的方法******************************************

    /**
     * 设置显示的客户信息以及显示方法
     * */
    public void initialize(){
        userIDColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getId()));
        userNameColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getName()));
        userTypeColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getType()));
    }

    // 设置controller数据的方法*****************************************

//    public void setUserBlService(UserBlService userBlService) {
//        this.userBlService = userBlService;
//    }

    /**
     * 刷新界面，取得所有用户的列表，并显示在tableview中
     * */
//    private void refresh(UserQueryVO query){
//        try {
//            ArrayList<UserVO> userList = userBlService.getUserList(query);
//            showUserList(userList);
//        }catch(DataException e){
//            UITool.showAlert(Alert.AlertType.ERROR,
//                    "Error","查找用户失败", "数据库错误");
//        }catch(Exception e){
//            UITool.showAlert(Alert.AlertType.ERROR,
//                    "Error","查找用户失败","RMI连接错误");
//        }
//    }

    /**
     * 取得用户列表并修改ObservableList的信息
     * */
    private void showUserList(ArrayList<UserVO> userList){
        userObservableList.clear();
        userObservableList.setAll(userList);
        userTableView.setItems(userObservableList);
    }

    // 界面之中会用到的方法******************************************

    @FXML
    private void handleSearch(){
//        String text=searchInfo.getText();
//        if(text.equals("")){
//            refresh(null);
//        }
//        else{
//            UserQueryVO query=new UserQueryVO(text,text);
//            refresh(query);
//        }
    }

    @FXML
    private void handleAddUser(){
        UserInfoUIController.init(null, new User(), UIType.ADD, root.getStage());
//        refresh(null);
    }

    @FXML
    private void handleDeleteUser(){
//        if(isUserSelected()){
//            try {
//                String ID = userTableView.getSelectionModel().getSelectedItem().getID();
//                String name = userTableView.getSelectionModel().getSelectedItem().getName();
//                userBlService.deleteUser(ID);
//
//                UITool.showAlert(Alert.AlertType.INFORMATION,
//                        "Success", "删除用户成功",
//                        "用户ID："+ID+System.lineSeparator()+"名字："+name);
//            }catch(DataException e){
//                UITool.showAlert(Alert.AlertType.ERROR,
//                        "Error","删除用户失败","数据库错误");
//            }catch(NotExistException e){
//                UITool.showAlert(Alert.AlertType.ERROR,
//                        "Error","删除用户失败","用户不存在");
//            }catch(Exception e){
//                UITool.showAlert(Alert.AlertType.ERROR,
//                        "Error","删除用户失败","RMI连接错误");
//            }
//            refresh(null);
//        }
    }

    @FXML
    private void handleEditUser(){
//        if(isUserSelected()){
//            UserInfoUIController.init(userBlService,userTableView.getSelectionModel().getSelectedItem(),2,root.getStage());
//        }
//        refresh(null);
    }

    @FXML
    private void handleCheckUser() {
//        if(isUserSelected()){
//            UserInfoUIController.init(userBlService,userTableView.getSelectionModel().getSelectedItem(),3,root.getStage());
//        }
    }

    private boolean isUserSelected(){
        int selectedIndex = userTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex>=0) {
            return true;
        } else {
            UITool.showAlert(Alert.AlertType.ERROR,
                    "No Selection","未选中用户","请在表中选择用户");
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
            //controller.setUserBlService(UserBlFactory.getService());
            //controller.refresh(null);
            root.setReturnPaneController(new AdminMainUIController());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
