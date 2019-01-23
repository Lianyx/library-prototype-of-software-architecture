import javafx.application.Application;
import javafx.stage.Stage;
import presentation.loginui.LoginUIController;
import utils.SystemInfo;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle(SystemInfo.SYSTEM_NAME.getValue());
        LoginUIController.init(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
