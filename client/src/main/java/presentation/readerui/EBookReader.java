package presentation.readerui;

import factory.ServiceFactory;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import presentation.uitools.UITool;
import service.ReaderService;

public class EBookReader {
    protected ReaderService readerService;
    protected String bookId;

    public EBookReader(String bookId) {
        this.bookId = bookId;
        this.readerService = ServiceFactory.getService(ReaderService.class);
    }

    public void display(Stage stage) {
        UITool.showAlert(Alert.AlertType.NONE,
                "加载失败", "加载此书的电子书失败", "此书没有对应的电子书");
    }
}
