package presentation.recordui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import presentation.uitools.CenterUIController;
import presentation.uitools.UITool;
import vo.UserVO;

import java.util.ArrayList;

public abstract class BaseRecordUIController extends CenterUIController {
    //private UserBlService userBlService;

    protected ObservableList<UserVO> recordObservableList = FXCollections.observableArrayList();
    @FXML
    protected TableView<UserVO> recordTableView;
    @FXML
    protected TableColumn<UserVO,String> recordIDColumn;
    @FXML
    protected TableColumn<UserVO,String> recordNameColumn;
    @FXML
    protected TableColumn<UserVO,String> recordAuthorColumn;

    @FXML
    protected TextField searchInfo;

    // 加载文件后调用的方法******************************************

    /**
     * 设置显示的客户信息以及显示方法
     * */
    public void initialize(){
        recordIDColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        recordNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        recordAuthorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
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
    protected void showList(ArrayList<UserVO> userList){
        recordObservableList.clear();
        recordObservableList.setAll(userList);
        recordTableView.setItems(recordObservableList);
    }


    // 界面之中会用到的方法******************************************

    protected boolean isRecordSelected(){
        int selectedIndex = recordTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0){
            return true;
        } else {
            UITool.showAlert(Alert.AlertType.ERROR,
                    "No Selection","未选中借阅记录","请在表中选择借阅记录");
            return false;
        }
    }
}
