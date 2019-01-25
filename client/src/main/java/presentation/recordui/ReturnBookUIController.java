package presentation.recordui;

import com.qoppa.pdf.PDFException;
import com.qoppa.pdfViewerFX.PDFViewer;
import factory.ServiceFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import object.po.Record;
import presentation.bookui.BaseBookUIController;
import presentation.mainpageui.RootUIController;
import presentation.mainpageui.UserMainUIController;
import presentation.uitools.UITool;
import service.RecordService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class ReturnBookUIController extends BaseRecordUIController {

    @FXML
    private void handleReturnBook() {
        if (isRecordSelected()) {
            try {
                Record record = recordTableView.getSelectionModel().getSelectedItem();
                recordService.returnBook(record.getUsername(), record.getBookId());
                UITool.showAlert(Alert.AlertType.INFORMATION,
                        "Success", "还书成功", "书名: " + record.getBookName());
                refresh();
            } catch (RemoteException e) {
                UITool.showAlert(Alert.AlertType.ERROR, "Error", "还书失败", "连接服务器错误");
            }
        }
    }

    void refresh(){
        try {
            ArrayList<Record> recordList =
                    recordService.getBorrowRecords(root.getOperator().getUsername());
            showRecordList(recordList);
        } catch (RemoteException e) {
            UITool.showAlert(Alert.AlertType.ERROR,
                    "Error", "查找借阅记录失败", "RMI连接错误");
        }
    }

    public void instanceInit(RootUIController root) {
        init(root);
    }

    public static void init(RootUIController root){
        try{
            // 加载登陆界面
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ReturnBookUIController.class.getResource("ReturnBookUI.fxml"));
            root.setCenterPane(loader.load());

            ReturnBookUIController controller = loader.getController();
            controller.setRoot(root);
            controller.setRecordService(ServiceFactory.getService(RecordService.class));
            controller.refresh();
            root.setReturnPaneController(new UserMainUIController());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
