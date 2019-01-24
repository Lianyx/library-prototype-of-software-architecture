package presentation.bookui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lombok.Data;
import object.po.Book;
import presentation.uitools.CenterUIController;
import presentation.uitools.UITool;
import service.BookService;

import java.util.List;

@Data
public abstract class BaseBookUIController extends CenterUIController {
    private BookService bookService;

    protected ObservableList<Book> bookObservableList = FXCollections.observableArrayList();
    @FXML
    protected TableView<Book> bookTableView;
    @FXML
    protected TableColumn<Book,String> bookIDColumn;
    @FXML
    protected TableColumn<Book,String> bookNameColumn;
    @FXML
    protected TableColumn<Book,String> bookAuthorColumn;
    @FXML
    protected TableColumn<Book,String> bookCategoryColumn;
    @FXML
    protected TableColumn<Book,String> bookFileTypeColumn;

    @FXML
    protected TextField searchInfo;

    // 加载文件后调用的方法******************************************

    /**
     * 设置显示的客户信息以及显示方法
     * */
    public void initialize(){
        bookIDColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        bookNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        bookAuthorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAuthor()));
        bookCategoryColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategory().getName()));
        bookFileTypeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEbookType().toString()));
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
//            ArrayList<Book> userList = userBlService.getUserList(query);
//            showUserList(userList);
//        }catch(DataException e){
//            UITool.showAlert(Alert.AlertType.ERROR,
//                    "Error","查找用户失败", "数据库错误");
//        }catch(Exception e){
//            UITool.showAlert(Alert.AlertType.ERROR,
//                    "Error","查找用户失败","RMI连接错误");
//        }
//    }


    protected void showBookList(List<Book> bookList){
        bookObservableList.clear();
        bookObservableList.setAll(bookList);
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
