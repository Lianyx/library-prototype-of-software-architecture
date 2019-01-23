package presentation.uitools;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class UITool {
    public static ButtonType showAlert(AlertType type, String title, String headerText, String contentText){
        Alert alert=new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
        return alert.getResult();
    }

//    public static void showMessage(TextArea messageArea,ArrayList<MessageVO> messageList){
//        String separator=System.lineSeparator()+"------------------------------"+System.lineSeparator()+System.lineSeparator();
//        String text="";
//
//        for(MessageVO message:messageList){
//            text+=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(message.getTime())+System.lineSeparator();
//            text+=message.getMessage()+separator;
//        }
//        messageArea.setText(text);
//    }
}
