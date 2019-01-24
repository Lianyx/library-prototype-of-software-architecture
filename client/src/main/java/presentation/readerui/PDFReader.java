package presentation.readerui;

import com.healthmarketscience.rmiio.RemoteInputStreamClient;
import com.qoppa.pdfViewerFX.PDFViewer;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.InputStream;

public class PDFReader extends EBookReader {

    public PDFReader(String bookId) {
        super(bookId);
    }

    public void display(Stage stage){
        try{
            InputStream inputStream = RemoteInputStreamClient.wrap(readerService.getFile(bookId));
            PDFViewer pdfViewer = new PDFViewer();
            pdfViewer.loadPDF(inputStream);

            Stage newStage = new Stage();
            newStage.initOwner(stage);
            Scene scene = new Scene(new BorderPane(pdfViewer));
            newStage.setScene(scene);
            newStage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
