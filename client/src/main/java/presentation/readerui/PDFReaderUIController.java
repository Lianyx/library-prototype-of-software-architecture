package presentation.readerui;

import com.qoppa.pdfViewerFX.PDFViewer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import presentation.mainpageui.AdminMainUIController;
import presentation.mainpageui.RootUIController;
import presentation.recordui.RecordManagementUIController;

import java.io.File;
import java.io.FileInputStream;

public class PDFReaderUIController extends BaseReaderUIController {

    public static void init(Stage stage){
        try{
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showOpenDialog(stage);

            if (file != null) {
                Stage newStage = new Stage();
                newStage.initOwner(stage);
                PDFViewer pdfViewer = new PDFViewer();
                pdfViewer.loadPDF(new FileInputStream(file));
                Scene scene = new Scene(new BorderPane(pdfViewer));
                newStage.setScene(scene);
                newStage.show();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
