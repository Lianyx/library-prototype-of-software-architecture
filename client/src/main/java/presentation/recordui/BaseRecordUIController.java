package presentation.recordui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lombok.Setter;
import object.po.Record;
import presentation.uitools.CenterUIController;
import presentation.uitools.UITool;
import service.RecordService;
import utils.DateTool;

import java.rmi.RemoteException;
import java.util.ArrayList;

@Setter
public abstract class BaseRecordUIController extends CenterUIController {
    protected RecordService recordService;

    private ObservableList<Record> recordObservableList = FXCollections.observableArrayList();
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


    public void initialize(){
        recordUsernameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsername()));
        recordBookNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBookId()));
        recordBorrowTimeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(DateTool.DateFormat(cellData.getValue().getBorrowTime())));
        recordReturnTimeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(DateTool.DateFormat(cellData.getValue().getReturnTime())));
    }

    abstract void refresh();

    void showRecordList(ArrayList<Record> recordList){
        recordObservableList.clear();
        recordObservableList.setAll(recordList);
        recordTableView.setItems(recordObservableList);
    }


    boolean isRecordSelected(){
        int selectedIndex = recordTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0){
            return true;
        } else {
            UITool.showAlert(Alert.AlertType.ERROR,
                    "No Selection","未选中借阅记录","请在表中选择借阅记录");
            return false;
        }
    }

    @FXML
    private void handleSearch(){
        refresh();
    }
}
