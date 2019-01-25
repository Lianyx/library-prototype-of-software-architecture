package presentation.bookui;

import factory.ServiceFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import object.exception.BorrowAccessException;
import object.exception.ExceedMaximumException;
import object.exception.UnavailableException;
import object.po.Book;
import presentation.mainpageui.AdminMainUIController;
import presentation.mainpageui.RootUIController;
import presentation.uitools.UITool;
import service.BookService;
import utils.UIType;

import java.rmi.RemoteException;

public class AdminBookUIController extends BaseBookUIController {

    @FXML
    private void handleAddBook(){
        BookInfoUIController.init(bookService, new Book(), UIType.ADD, root.getStage());
    }

    @FXML
    private void handleEditBook(){
        if (isBookSelected()) {
            Book book = bookTableView.getSelectionModel().getSelectedItem();
            BookInfoUIController.init(bookService, book, UIType.ADMIN_EDIT, root.getStage());
        }
    }

    public void instanceInit(RootUIController root){}

    /**
     * 静态初始化方法，加载相应的FXML文件，并添加一些信息
     * */
    public static void init(RootUIController root){
        try{
            // 加载登陆界面
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AdminBookUIController.class.getResource("AdminBookUI.fxml"));
            root.setCenterPane(loader.load());

            AdminBookUIController controller = loader.getController();
            controller.setRoot(root);
            controller.setBookService(ServiceFactory.getService(BookService.class));
            controller.refresh("");
            root.setReturnPaneController(new AdminMainUIController());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
