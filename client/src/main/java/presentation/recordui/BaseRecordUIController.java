package presentation.recordui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import object.po.Record;
import presentation.uitools.CenterUIController;
import presentation.uitools.UITool;

import java.util.ArrayList;

public abstract class BaseRecordUIController extends CenterUIController {
    //protected RecordService recordService;

    protected ObservableList<Record> recordObservableList = FXCollections.observableArrayList();
    @FXML
    protected TableView<Record> recordTableView;
    @FXML
    protected TableColumn<Record,String> recordUsernameColumn;
    @FXML
    protected TableColumn<Record,String> recordBookNameColumn;
    @FXML
    protected TableColumn<Record,String> recordBorrowTimeColumn;
    @FXML
    protected TableColumn<Record,String> recordReturnTimeColumn;

    @FXML
    protected TextField searchInfo;

    // 加载文件后调用的方法******************************************

    /**
     * 设置显示的客户信息以及显示方法
     * */
    public void initialize(){
        recordUsernameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsername()));
        recordBookNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBookId()));
        recordBorrowTimeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getBorrowTime())));
        recordReturnTimeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getReturnTime())));
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
//            ArrayList<Record> userList = userBlService.getUserList(query);
//            showUserList(userList);
//        }catch(DataException e){
//            UITool.showAlert(Alert.AlertType.ERROR,
//                    "Error","查找用户失败", "数据库错误");
//        }catch(Exception e){
//            UITool.showAlert(Alert.AlertType.ERROR,
//                    "Error","查找用户失败","RMI连接错误");
//        }
//    }

    protected void showList(ArrayList<Record> recordList){
        recordObservableList.clear();
        recordObservableList.setAll(recordList);
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
