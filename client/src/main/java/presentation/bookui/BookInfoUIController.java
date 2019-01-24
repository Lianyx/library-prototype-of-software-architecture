package presentation.bookui;

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
import object.po.Book;
import presentation.uitools.UITool;
import service.BookService;
import utils.UIType;

@Setter
public class BookInfoUIController {
    private Book book;
    private BookService bookService;

    @FXML
    private TextField ID;
    @FXML
    private TextField bookName;
    @FXML
    private TextField author;
    @FXML
    private TextField category;
    @FXML
    private TextField eBook;

    @FXML
    private ChoiceBox<String> categoryChoiceBox;
    @FXML
    private ChoiceBox<String> eBookChoiceBox;
    
    @FXML
    private Button confirm;
    @FXML
    private Button cancel;

    private Stage dialogStage;

    public void initialize(){
//        String[] typeList = BookType.getBookTypeList();
//        typeChoiceBox.setItems(FXCollections.observableArrayList(typeList));
//        typeChoiceBox.getSelectionModel().selectedIndexProperty().addListener((ov,oldValue,newValue)->{
//            type.setText(typeList[newValue.intValue()]);
//            //book.getRole().setType(typeList[newValue.intValue()]);
//        });

    }

    // 设置controller数据的方法*****************************************

    private void setBook(Book book) {
        this.book = book;
        ID.setText(book.getId());
        bookName.setText(book.getName());
        author.setText(book.getAuthor());
        eBook.setText(String.valueOf(book.getEbookType()));
        category.setText(String.valueOf(book.getCategory()));
        //setRole(book.getRole());
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
//                    String bookID=bookBlService.addBook(book);
//                    String bookName=book.getName();
//
//                    UITool.showAlert(Alert.AlertType.INFORMATION,
//                            "Success","添加用户成功",
//                            "用户ID："+bookID+System.lineSeparator()+"名字："+bookName);
//                }
//                else if(text.equals("编辑")){
//                    bookBlService.editBook(book);
//                    String bookID=book.getID();
//                    String bookName=book.getName();
//
//                    UITool.showAlert(Alert.AlertType.INFORMATION,
//                            "Success","编辑用户成功",
//                            "用户ID："+bookID+System.lineSeparator()+"名字："+bookName);
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

        if (bookName.getText().length() == 0) {
            errorMessage += ("未输入书名。" + System.lineSeparator());
        }
        if (author.getText().length() == 0) {
            errorMessage += ("未输入作者。" + System.lineSeparator());
        }
        if (category.getText().length() == 0) {
            errorMessage += ("未选择书的种类。" + System.lineSeparator());
        }

        if (errorMessage.length() == 0){
            book.setName(bookName.getText());
            book.setAuthor(author.getText());
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
    public static void init(BookService service, Book book, UIType type, Stage stage){
        try{
            // 加载登陆界面
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(BookInfoUIController.class.getResource("BookInfoUI.fxml"));

            // Create the dialog stage
            Stage dialogStage = new Stage();
            dialogStage.setResizable(false);
            dialogStage.setTitle("书籍信息界面");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);
            dialogStage.setScene(new Scene(loader.load()));

            BookInfoUIController controller=loader.getController();
            controller.setBookService(service);
            controller.setBook(book);
            controller.setDialogStage(dialogStage);
            controller.setPaneType(type);

            // Show the dialog and wait until the book closes it.
            dialogStage.showAndWait();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}