package presentation.bookui;

import factory.ServiceFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import presentation.mainpageui.RootUIController;
import presentation.mainpageui.UserMainUIController;
import presentation.readerui.HTMLReaderUIController;
import service.BookService;

public class UserBookUIController extends BaseBookUIController {

    @FXML
    private void handleReadBook() {
        HTMLReaderUIController.init(root.getStage());
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
