package presentation.uitools;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import object.po.Message;
import utils.DateTool;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class UITool {
    public static ButtonType showAlert(AlertType type, String title, String headerText, String contentText){
        Alert alert=new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
        return alert.getResult();
    }

    public static void showMessage(TextArea messageArea, ArrayList<Message> messageList){
        String separator = System.lineSeparator()+"- - - - - -"+System.lineSeparator()+System.lineSeparator();
        StringBuilder text= new StringBuilder();
        Collections.reverse(messageList);

        for(Message message: messageList){
            text.append(DateTool.DateFormat(message.getTime())).append(System.lineSeparator());
            text.append(message.getContent()).append(separator);
        }
        messageArea.setText(text.toString());
    }
}
