package presentation.bookui;

import factory.ServiceFactory;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Setter;
import object.enun.BookType;
import object.exception.AlreadyExistException;
import object.po.Book;
import object.po.Category;
import presentation.uitools.UITool;
import service.BookService;
import utils.UIType;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.sun.tools.doclint.Entity.lambda;

@Setter
public class BookInfoUIController {
    private Book book;
    private BookService bookService;

    @FXML
    private TextField ID;
    @FXML
    private TextField bookName;
    @FXML
    private TextField author;
    @FXML
    private TextField category;
    @FXML
    private TextField eBook;

    @FXML
    private ChoiceBox<String> categoryChoiceBox;
    @FXML
    private ChoiceBox<String> eBookChoiceBox;
    
    @FXML
    private Button confirm;
    @FXML
    private Button cancel;

    private Stage dialogStage;

    public void initialize() throws RemoteException {
        bookService = ServiceFactory.getService(BookService.class);
        ArrayList<Category> categoryList = bookService.getAllCategories();
        List<String> nameList = categoryList.stream().map(Category::toString).collect(Collectors.toList());
        categoryChoiceBox.setItems(FXCollections.observableArrayList(nameList));
        categoryChoiceBox.getSelectionModel().selectedIndexProperty().addListener((ov,oldValue,newValue)->{
            Category selectedCategory = categoryList.get(newValue.intValue());
            book.setCategory(selectedCategory);
            category.setText(selectedCategory.toString());
        });

        List<BookType> bookTypeList = bookService.getAllEBookTypes();
        List<String> nameList2 = bookTypeList.stream().map(String::valueOf).collect(Collectors.toList());
        eBookChoiceBox.setItems(FXCollections.observableArrayList(nameList2));
        eBookChoiceBox.getSelectionModel().selectedIndexProperty().addListener((ov,oldValue,newValue)->{
            BookType bookType = bookTypeList.get(newValue.intValue());
            book.setEbookType(bookType);
            eBook.setText(String.valueOf(bookType));
            if (bookType.equals(BookType.NULL)) {
                book.setEbookPath("");
            } else {
                book.setEbookPath("EBook." + String.valueOf(bookType));
            }
        });
    }

    private void setBook(Book book) {
        this.book = book;
        ID.setText(book.getId());
        bookName.setText(book.getName());
        author.setText(book.getAuthor());
        eBook.setText(String.valueOf(book.getEbookType()));
        category.setText(String.valueOf(book.getCategory()));
    }

    private void setPaneType(UIType category) {
        if (category.equals(UIType.ADD)) {
            confirm.setText("添加");
            ID.setEditable(true);
        }
        else if (category.equals(UIType.ADMIN_EDIT)) {
            confirm.setText("编辑");
        }
    }


    @FXML
    private void handleConfirm(){
        if (isInputValid()){
            String text=confirm.getText();
            try{
                if(text.equals("添加")){
                    bookService.addBook(book);
                }
                else if(text.equals("编辑")){
                    bookService.updateBook(book);
                }
                UITool.showAlert(Alert.AlertType.INFORMATION, "Success", text + "书籍成功", "书名: " + bookName.getText());
                dialogStage.close();
            } catch (AlreadyExistException e){
                UITool.showAlert(Alert.AlertType.ERROR,
                        "Error", text + "书籍失败", "书籍ID重复");
            } catch (Exception e){
                UITool.showAlert(Alert.AlertType.ERROR,
                        "Error", text + "书籍失败", "RMI连接错误");
            }
        }
    }

    @FXML
    private void handleCancel(){
        dialogStage.close();
    }

    /**
     * 检查用户信息的输入是否完整且合法
     * 完整且合法返回true
     * */
    private boolean isInputValid(){
        String errorMessage = "";
        
        if (ID.getText() == null || ID.getText().length() == 0) {
            errorMessage += ("未输入ID。" + System.lineSeparator());
        }
        if (bookName.getText() == null || bookName.getText().length() == 0) {
            errorMessage += ("未输入书名。" + System.lineSeparator());
        }
        if (author.getText() == null || author.getText().length() == 0) {
            errorMessage += ("未输入作者。" + System.lineSeparator());
        }
        if (category.getText() == null || category.getText().length() == 0) {
            errorMessage += ("未选择书的种类。" + System.lineSeparator());
        }

        if (errorMessage.length() == 0){
            book.setId(ID.getText());
            book.setName(bookName.getText());
            book.setAuthor(author.getText());
            return true;
        } else {
            UITool.showAlert(Alert.AlertType.ERROR, "书籍信息错误","请检查书籍信息的输入", errorMessage);
            return false;
        }
    }


    public static void init(BookService service, Book book, UIType category, Stage stage){
        try{
            // 加载登陆界面
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BookInfoUIController.class.getResource("BookInfoUI.fxml"));

            Stage dialogStage = new Stage();
            dialogStage.setResizable(false);
            dialogStage.setTitle("书籍信息界面");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);
            dialogStage.setScene(new Scene(loader.load()));

            BookInfoUIController controller = loader.getController();
            controller.setBook(book);
            controller.setDialogStage(dialogStage);
            controller.setPaneType(category);

            dialogStage.showAndWait();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}