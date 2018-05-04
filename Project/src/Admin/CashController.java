package Admin;
/*
 * Name : Taukekhan Mustakhov
 * Email : taukiw@gmail.com
 * Date and venue : 01.04.2018 / SDU (Suleyman Demirel University)
 * Description : Cash Controller.
 */

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CashController implements Initializable {


    //Attributes of Making Reservation
    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField creditcardno;
    @FXML
    private TextField email;
    @FXML
    private TextField dateOfCard;
    @FXML
    private TextField cvv;
    @FXML
    private TextField phone;
    @FXML
    private Button ButtonReservation;
    @FXML
    private Label LabelSuccess;

    private dbUtil.dbConnection dbConnection;
    public TableView<FlightData> flightData1;
    public static FlightData rowData;
    public static Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ButtonReservation.setOnAction(event1 -> {
            if (!name.getText().isEmpty() && !surname.getText().isEmpty() && !email.getText().isEmpty() && !phone.getText().isEmpty() && !creditcardno.getText().isEmpty() && !cvv.getText().isEmpty() && !dateOfCard.getText().isEmpty()) {
                LabelSuccess.setTextFill(Color.GREEN);
                LabelSuccess.setText("Done successfully");
                name.clear();
                surname.clear();
                email.clear();
                phone.clear();
                creditcardno.clear();
                cvv.clear();
                dateOfCard.clear();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeight(100);
                alert.setWidth(50);
                alert.setHeaderText("Please fill the all fields!");
                alert.showAndWait();
            }
        });
    }
}
