package presentation.bookui;

import blservice.impl.UserBlServiceImpl;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import presentation.mainpageui.AdminMainUIController;
import presentation.mainpageui.RootUIController;
import presentation.mainpageui.UserMainUIController;
import presentation.uitools.CenterUIController;
import presentation.uitools.UITool;
import presentation.userui.UserInfoUIController;
import utils.UIType;
import vo.UserVO;

import java.util.ArrayList;

public abstract class BaseBookUIController extends CenterUIController {
    //private UserBlService userBlService;

    protected ObservableList<UserVO> bookObservableList = FXCollections.observableArrayList();
    @FXML
    protected TableView<UserVO> bookTableView;
    @FXML
    protected TableColumn<UserVO,String> bookIDColumn;
    @FXML
    protected TableColumn<UserVO,String> bookNameColumn;
    @FXML
    protected TableColumn<UserVO,String> bookAuthorColumn;

    @FXML
    protected TextField searchInfo;

    // 加载文件后调用的方法******************************************

    /**
     * 设置显示的客户信息以及显示方法
     * */
    public void initialize(){
        bookIDColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        bookNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        bookAuthorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
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
    protected void showBookList(ArrayList<UserVO> userList){
        bookObservableList.clear();
        bookObservableList.setAll(userList);
        bookTableView.setItems(bookObservableList);
    }


    // 界面之中会用到的方法******************************************

    protected boolean isBookSelected(){
        int selectedIndex = bookTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0){
            return true;
        } else {
            UITool.showAlert(Alert.AlertType.ERROR,
                    "No Selection","未选中书籍","请在表中选择书籍");
            return false;
        }
    }
}
