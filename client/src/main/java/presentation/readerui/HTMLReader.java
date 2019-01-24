package presentation.readerui;

import com.healthmarketscience.rmiio.RemoteInputStreamClient;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import utils.FileTool;

import java.io.InputStream;

public class HTMLReader extends EBookReader {

    public HTMLReader(String bookId) {
        super(bookId);
    }

    public void display(Stage stage){
        try{
            InputStream inputStream = RemoteInputStreamClient.wrap(readerService.getFile(bookId));
            WebView webView = new WebView();
            webView.getEngine().loadContent(FileTool.readContent(inputStream));

            Stage newStage = new Stage();
            newStage.initOwner(stage);
            Scene scene = new Scene(new BorderPane(webView));
            newStage.setScene(scene);
            newStage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
