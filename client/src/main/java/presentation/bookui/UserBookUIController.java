package presentation.bookui;

import doc.BookDoc;
import factory.ServiceFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import lombok.Setter;
import object.exception.BorrowAccessException;
import object.exception.ExceedMaximumException;
import object.exception.UnavailableException;
import object.po.Book;
import presentation.mainpageui.RootUIController;
import presentation.mainpageui.UserMainUIController;
import presentation.readerui.EBookReader;
import presentation.uitools.UITool;
import service.BookService;
import service.RecordService;

import java.io.*;
import java.rmi.RemoteException;

@Setter
public class UserBookUIController extends BaseBookUIController {

    private RecordService recordService;

    @FXML
    private void handleReadBook() throws IOException {
        if (isBookSelected()) {
            Book book = bookTableView.getSelectionModel().getSelectedItem();
            BookDoc doc = new BookDoc(book);
            EBookReader reader = doc.getReader();
            reader.display(root.getStage());
        }
    }

    @FXML
    private void handleBorrowBook() {
        if (isBookSelected()) {
            try {
                String username = root.getOperator().getUsername();
                Book book = bookTableView.getSelectionModel().getSelectedItem();
                recordService.borrowBook(username, book.getId());
                UITool.showAlert(Alert.AlertType.INFORMATION, "Success", "借书成功", "书名: " + book.getName());
                refresh(searchInfo.getText());
            } catch (UnavailableException e) {
                UITool.showAlert(Alert.AlertType.ERROR, "Error", "借书失败", "书籍已借出");
            } catch (BorrowAccessException e){
                UITool.showAlert(Alert.AlertType.ERROR, "Error", "借书失败", "借书权限不够");
            } catch (ExceedMaximumException e){
                UITool.showAlert(Alert.AlertType.ERROR, "Error", "借书失败", "超过借书上限");
            } catch (RemoteException e){
                UITool.showAlert(Alert.AlertType.ERROR, "Error", "借书失败", "连接服务器错误");
            }
        }
    }

    public void instanceInit(RootUIController root) {
        init(root);
    }

    public static void init(RootUIController root){
        try{
            // 加载登陆界面
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(UserBookUIController.class.getResource("UserBookUI.fxml"));
            root.setCenterPane(loader.load());

            UserBookUIController controller = loader.getController();
            controller.setRoot(root);
            controller.setBookService(ServiceFactory.getService(BookService.class));
            controller.setRecordService(ServiceFactory.getService(RecordService.class));
            controller.refresh("");
            root.setReturnPaneController(new UserMainUIController());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
