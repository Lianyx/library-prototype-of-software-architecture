package presentation.bookui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import object.po.Book;
import presentation.mainpageui.AdminMainUIController;
import presentation.mainpageui.RootUIController;
import presentation.uitools.UITool;
import utils.UIType;

public class AdminBookUIController extends BaseBookUIController {

    // 设置controller数据的方法*****************************************

//    public void setUserBlService(UserBlService userBlService) {
//        this.userBlService = userBlService;
//    }

    /**
     * 刷新界面，取得所有用户的列表，并显示在tableview中
     * */
//    private void refresh(UserQueryVO query){
//        try {
//            ArrayList<UserVO> userList = userBlService.getUserList(query);
//            showUserList(userList);
//        }catch(DataException e){
//            UITool.showAlert(Alert.AlertType.ERROR,
//                    "Error","查找用户失败", "数据库错误");
//        }catch(Exception e){
//            UITool.showAlert(Alert.AlertType.ERROR,
//                    "Error","查找用户失败","RMI连接错误");
//        }
//    }


    // 界面之中会用到的方法******************************************

    @FXML
    private void handleSearch(){
//        String text=searchInfo.getText();
//        if(text.equals("")){
//            refresh(null);
//        }
//        else{
//            UserQueryVO query=new UserQueryVO(text,text);
//            refresh(query);
//        }
    }

    @FXML
    private void handleAddBook(){
        BookInfoUIController.init(null, new Book(), UIType.ADD, root.getStage());
    }

    @FXML
    private void handleDeleteUser(){

    }

    @FXML
    private void handleEditUser(){
//        if(isUserSelected()){
//            UserInfoUIController.init(userBlService,userTableView.getSelectionModel().getSelectedItem(),2,root.getStage());
//        }
//        refresh(null);
    }

    private boolean isUserSelected(){
        int selectedIndex = bookTableView.getSelectionModel().getSelectedIndex();
        if(selectedIndex>=0){
            return true;
        }else{
            // Nothing selected
            UITool.showAlert(Alert.AlertType.ERROR,
                    "No Selection","未选中用户","请在表中选择用户");
            return false;
        }
    }

    public void instanceInit(RootUIController root){}

    /**
     * 静态初始化方法，加载相应的FXML文件，并添加一些信息
     * */
    public static void init(RootUIController root){
        try{
            // 加载登陆界面
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AdminBookUIController.class.getResource("AdminBookUI.fxml"));
            root.setCenterPane(loader.load());

            AdminBookUIController controller = loader.getController();
            controller.setRoot(root);
            //controller.setBookService(ServiceFactory.getBookService());
            //controller.refresh(null);
            root.setReturnPaneController(new AdminMainUIController());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
