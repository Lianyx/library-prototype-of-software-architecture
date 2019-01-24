package presentation.readerui;

import com.qoppa.pdfViewerFX.PDFViewer;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import utils.FileTool;

import java.io.File;
import java.io.FileInputStream;

public class HTMLReaderUIController extends BaseReaderUIController {

    public static void init(Stage stage){
        try{
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("HTML files (*.html)", "*.html");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showOpenDialog(stage);

            if (file != null) {
                Stage newStage = new Stage();
                newStage.initOwner(stage);
                WebView webView = new WebView();
                webView.getEngine().loadContent(FileTool.readAllLines(file));
                Scene scene = new Scene(new BorderPane(webView));
                newStage.setScene(scene);
                newStage.show();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
