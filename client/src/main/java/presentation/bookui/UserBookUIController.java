package presentation.bookui;

import doc.BookDoc;
import factory.ServiceFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import object.po.Book;
import presentation.mainpageui.RootUIController;
import presentation.mainpageui.UserMainUIController;
import presentation.readerui.EBookReader;
import service.BookService;

import java.io.*;

public class UserBookUIController extends BaseBookUIController {

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
    private void handleBorrowBook(){

    }

    public void instanceInit(RootUIController root) {
        init(root);
    }

    /**
     * 静态初始化方法，加载相应的FXML文件，并添加一些信息
     * */
    public static void init(RootUIController root){
        try{
            // 加载登陆界面
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(UserBookUIController.class.getResource("UserBookUI.fxml"));
            root.setCenterPane(loader.load());

            UserBookUIController controller = loader.getController();
            controller.setRoot(root);
            controller.setBookService(ServiceFactory.getService(BookService.class));
            controller.refresh("");
            root.setReturnPaneController(new UserMainUIController());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
