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
import lombok.EqualsAndHashCode;
import object.po.Book;
import presentation.uitools.CenterUIController;
import presentation.uitools.UITool;
import service.BookService;

import java.rmi.RemoteException;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class BaseBookUIController extends CenterUIController {
    protected BookService bookService;

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
    protected TableColumn<Book,String> bookAvailableColumn;

    @FXML
    protected TextField searchInfo;

    public void initialize(){
        bookIDColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        bookNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        bookAuthorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAuthor()));
        bookCategoryColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategory().getName()));
        bookFileTypeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().getEbookType() == null ?
                        "无" : String.valueOf(cellData.getValue().getEbookType())));
        bookAvailableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isAvailable() ? "空闲" : "借出"));
    }

    protected void refresh(String keyword){
        try {
            List<Book> bookList = bookService.searchBook(keyword);
            showBookList(bookList);
        } catch (RemoteException e){
            UITool.showAlert(Alert.AlertType.ERROR,
                    "Error", "查找书籍失败", "服务器连接错误");
        } catch(Exception e){
            UITool.showAlert(Alert.AlertType.ERROR,
                    "Error","查找书籍失败","数据库错误");
        }
    }

    @FXML
    private void handleSearch(){
        refresh(searchInfo.getText());
    }

    private void showBookList(List<Book> bookList){
        bookObservableList.clear();
        bookObservableList.setAll(bookList);
        bookTableView.setItems(bookObservableList);
    }

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
