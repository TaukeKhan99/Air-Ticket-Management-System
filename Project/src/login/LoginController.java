package login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    LoginModel loginModel = new LoginModel();

    //private attributes
    @FXML
    private Label dbstatus;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginButton;
    @FXML
    private Label loginStatus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbstatus.setText(this.loginModel.isDatabaseConnected() ? "Connected to Database" : "Not Connected to Database");
//       loginButton.setDisable(true);
//       loginButton.setVisible(false);
    }

    @FXML
    public void Login(ActionEvent event){
        try {
            if (this.loginModel.isLogin(this.username.getText(),this.password.getText())){
    /*!!!*/            Stage stage = (Stage) this.loginButton.getScene().getWindow();
                       stage.close();
                try {
                    Stage mainStage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("../Admin/Reservation.fxml"));
                    Scene scene = new Scene(root);
                    mainStage.setScene(scene);
                    mainStage.setTitle("Air Ticket Management System");
                    mainStage.setResizable(false);
                    mainStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                this.loginStatus.setText("Incorrect username or password!");
                this.loginStatus.setTextFill(Color.RED);
            }

        }catch (Exception localException){
            localException.printStackTrace();
        }
    }
}
