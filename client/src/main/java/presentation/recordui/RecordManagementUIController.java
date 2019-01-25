package presentation.recordui;

import factory.ServiceFactory;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import object.po.Record;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import presentation.mainpageui.AdminMainUIController;
import presentation.mainpageui.RootUIController;
import presentation.uitools.UITool;
import service.RecordService;

import java.io.File;
import java.io.FileOutputStream;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RecordManagementUIController extends BaseRecordUIController {

    void refresh() {
        try {
            String keyword = searchInfo.getText() == null ? "" : searchInfo.getText();
            ArrayList<Record> recordList = recordService.searchRecords(keyword);
            showRecordList(recordList);
        } catch (RemoteException e) {
            UITool.showAlert(Alert.AlertType.ERROR,
                    "Error", "查找借阅记录失败", "RMI连接错误");
        }
    }

    @FXML
    private void handleExportRecord() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String day = sdf.format(new Date());
            String fileName = "借阅记录（"+day+"）";

            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("First Sheet");

            HSSFRow rowHead = sheet.createRow((short)0);
            rowHead.createCell(0).setCellValue("用户名");
            rowHead.createCell(1).setCellValue("书名");
            rowHead.createCell(2).setCellValue("借阅时间");
            rowHead.createCell(3).setCellValue("归还时间");

            ObservableList<Record> list = recordTableView.getItems();
            for (int  i = 0; i < list.size(); i++) {
                HSSFRow row = sheet.createRow((short)(i+1));
                row.createCell(0).setCellValue(recordUsernameColumn.getCellData(i));
                row.createCell(1).setCellValue(recordBookNameColumn.getCellData(i));
                row.createCell(2).setCellValue(recordBorrowTimeColumn.getCellData(i));
                row.createCell(3).setCellValue(recordReturnTimeColumn.getCellData(i));
            }

            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XLS files (*.xls)", "*.xls");
            fileChooser.getExtensionFilters().add(extFilter);
            fileChooser.setInitialFileName(fileName);
            File file = fileChooser.showSaveDialog(root.getStage());

            if(file!=null){
                FileOutputStream fileOut = new FileOutputStream(file);
                workbook.write(fileOut);
                fileOut.close();

                UITool.showAlert(Alert.AlertType.INFORMATION,
                        "Success","导出报表成功",
                        "文件路径："+file.getAbsolutePath());
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void instanceInit(RootUIController root) {
        init(root);
    }

    public static void init(RootUIController root){
        try{
            // 加载登陆界面
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(RecordManagementUIController.class.getResource("RecordManagementUI.fxml"));
            root.setCenterPane(loader.load());

            RecordManagementUIController controller = loader.getController();
            controller.setRoot(root);
            controller.setRecordService(ServiceFactory.getService(RecordService.class));
            controller.refresh();
            root.setReturnPaneController(new AdminMainUIController());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
