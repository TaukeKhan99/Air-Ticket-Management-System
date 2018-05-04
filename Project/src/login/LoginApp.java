package login;
/*
* Name : Taukekhan Mustakhov
* Email : taukiw@gmail.com
* Date and venue : 20.02.2018 / SDU (Suleyman Demirel University)
* Description : Login Application.
*/

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../login/LoginWindow.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("../Admin/Reservation.fxml"));

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Air Ticket Management System");
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
