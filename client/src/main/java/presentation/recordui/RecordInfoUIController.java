package presentation.recordui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Setter;
import object.po.Record;
import presentation.uitools.UITool;
import service.RecordService;
import utils.UIType;

@Setter
public class RecordInfoUIController {
    private Record record;
    private RecordService recordService;

    @FXML
    private TextField ID;
    @FXML
    private TextField recordName;
    @FXML
    private TextField author;
    @FXML
    private TextField category;
    @FXML
    private TextField eRecord;

    @FXML
    private ChoiceBox<String> categoryChoiceBox;
    @FXML
    private ChoiceBox<String> eRecordChoiceBox;
    
    @FXML
    private Button confirm;
    @FXML
    private Button cancel;

    private Stage dialogStage;

    public void initialize(){
//        String[] typeList = RecordType.getRecordTypeList();
//        typeChoiceBox.setItems(FXCollections.observableArrayList(typeList));
//        typeChoiceBox.getSelectionModel().selectedIndexProperty().addListener((ov,oldValue,newValue)->{
//            type.setText(typeList[newValue.intValue()]);
//            //record.getRole().setType(typeList[newValue.intValue()]);
//        });

    }

    // 设置controller数据的方法*****************************************

    private void setRecord(Record record) {
        this.record = record;
        ID.setText(record.getId());
        recordName.setText(record.getName());
        author.setText(record.getAuthor());
        eRecord.setText(String.valueOf(record.getErecordType()));
        category.setText(String.valueOf(record.getCategory()));
        //setRole(record.getRole());
    }

    private void setPaneType(UIType type) {
        if (type.equals(UIType.ADD)) {
            confirm.setText("添加");
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
//                    String recordID=recordBlService.addRecord(record);
//                    String recordName=record.getName();
//
//                    UITool.showAlert(Alert.AlertType.INFORMATION,
//                            "Success","添加用户成功",
//                            "用户ID："+recordID+System.lineSeparator()+"名字："+recordName);
//                }
//                else if(text.equals("编辑")){
//                    recordBlService.editRecord(record);
//                    String recordID=record.getID();
//                    String recordName=record.getName();
//
//                    UITool.showAlert(Alert.AlertType.INFORMATION,
//                            "Success","编辑用户成功",
//                            "用户ID："+recordID+System.lineSeparator()+"名字："+recordName);
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

        if (recordName.getText().length() == 0) {
            errorMessage += ("未输入书名。" + System.lineSeparator());
        }
        if (author.getText().length() == 0) {
            errorMessage += ("未输入作者。" + System.lineSeparator());
        }
        if (category.getText().length() == 0) {
            errorMessage += ("未选择书的种类。" + System.lineSeparator());
        }

        if (errorMessage.length() == 0){
            record.setName(recordName.getText());
            record.setAuthor(author.getText());
            return true;
        } else {
            UITool.showAlert(Alert.AlertType.ERROR, "书籍信息错误","请检查书籍信息的输入", errorMessage);
            return false;
        }
    }

    // 加载文件和界面的方法******************************************

    /**
     * 静态初始化方法，加载相应的FXML文件，并添加一些信息
     * */
    public static void init(RecordService service, Record record, UIType type, Stage stage){
        try{
            // 加载登陆界面
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(RecordInfoUIController.class.getResource("RecordInfoUI.fxml"));

            // Create the dialog stage
            Stage dialogStage = new Stage();
            dialogStage.setResizable(false);
            dialogStage.setTitle("书籍信息界面");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);
            dialogStage.setScene(new Scene(loader.load()));

            RecordInfoUIController controller=loader.getController();
            controller.setRecordService(service);
            controller.setRecord(record);
            controller.setDialogStage(dialogStage);
            controller.setPaneType(type);

            // Show the dialog and wait until the record closes it.
            dialogStage.showAndWait();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}