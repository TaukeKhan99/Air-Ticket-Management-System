package Admin;
/*
 * Name : Taukekhan Mustakhov
 * Email : taukiw@gmail.com
 * Date and venue : 01.04.2018 / SDU (Suleyman Demirel University)
 * Description : Air ticket MS.
 */

import dbUtil.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Admin implements Initializable {

    ObservableList<String> sourceStation = FXCollections.observableArrayList("Astana", "Almaty", "Aktobe", "Atyray");
    ObservableList<String> destinationStation = FXCollections.observableArrayList("Astana", "Almaty", "Aktobe", "Atyray");

    //Attributes of Reservation tab
    @FXML
    private ComboBox sourceStationBox;
    @FXML
    private ComboBox destinationBox;
    @FXML
    private Button getDetails;
    @FXML
    private Button reset;
    @FXML
    private DatePicker date;
    @FXML
    private Label instruction;

    //Attributes of Airline Management tab
    @FXML
    private TextField flightID;
    @FXML
    private TextField flightName;
    @FXML
    private TextField source;
    @FXML
    private TextField departure;
    @FXML
    private TextField flightCharge;
    @FXML
    private TextField seats;
    @FXML
    private TextField destination;
    @FXML
    private TextField arrivalTime;
    @FXML
    private TableView<FlightData> flightData;
    @FXML
    TableColumn<FlightData, String> idColumn;
    @FXML
    TableColumn<FlightData, String> nameColumn;
    @FXML
    TableColumn<FlightData, String> sourceColumn;
    @FXML
    TableColumn<FlightData, String> departureColumn;
    @FXML
    TableColumn<FlightData, String> chargeColumn;
    @FXML
    TableColumn<FlightData, String> seatsColumn;
    @FXML
    TableColumn<FlightData, String> destinationColumn;
    @FXML
    TableColumn<FlightData, String> timeColumn;

    //Attributes in Reservation tab
    @FXML
    private TableView<FlightData> flightData1;
    @FXML
    TableColumn<FlightData, String> idColumn1;
    @FXML
    TableColumn<FlightData, String> nameColumn1;
    @FXML
    TableColumn<FlightData, String> sourceColumn1;
    @FXML
    TableColumn<FlightData, String> departureColumn1;
    @FXML
    TableColumn<FlightData, String> chargeColumn1;
    @FXML
    TableColumn<FlightData, String> seatsColumn1;
    @FXML
    TableColumn<FlightData, String> destinationColumn1;
    @FXML
    TableColumn<FlightData, String> timeColumn1;
    private ObservableList<FlightData> data1;


    private dbConnection dbConnection;
    private ObservableList<FlightData> data;
    private String sql = "SELECT * FROM flightData";

    public static FlightData rowData;
    public static Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //connecting to database
        this.dbConnection = new dbConnection();
        //set items to the combo boxes
        sourceStationBox.setItems(sourceStation);
        sourceStationBox.setValue("");
        destinationBox.setItems(destinationStation);
        destinationBox.setValue("");
        //to make invisible
        instruction.setVisible(false);
        flightData1.setVisible(false);
        flightData1.setRowFactory(tv -> {
            TableRow<FlightData> rowFlight = new TableRow<>();
            rowFlight.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !rowFlight.isEmpty()) {
                    rowData = rowFlight.getItem();
                    try {
                        stage = new Stage();
                        Parent root = FXMLLoader.load(getClass().getResource("Cash.fxml"));
                        stage.setTitle("Reservation");
                        stage.setScene(new Scene(root));
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.setResizable(false);
                        stage.show();

                    } catch (IOException e) {
                        System.err.println("File not found" + e);
                        e.printStackTrace();
                    }
                }
            });
            return rowFlight;
        });
    }


    @FXML
    public void resetDetails(ActionEvent event) {
        destinationBox.setValue("");
        sourceStationBox.setValue("");
        date.getEditor().setText("");
        //to make the invisible
        instruction.setVisible(false);
        flightData1.setVisible(false);
        //set disable to combo boxes and buttons
        sourceStationBox.setDisable(false);
        destinationBox.setDisable(false);
        date.setDisable(false);
        getDetails.setDisable(false);
    }


    @FXML
    public void addFlight(ActionEvent event) {
        String sqlInsert = "INSERT INTO flightData(flight_ID,flight_name,source,departure,flight_charge,seats,destination,arrival_time) VALUES (?,?,?,?,?,?,?,?)";

        try {
            //connecting to database
            Connection connection = dbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlInsert);

            statement.setString(1, this.flightID.getText());
            statement.setString(2, this.flightName.getText());
            statement.setString(3, this.source.getText());
            statement.setString(4, this.departure.getText());
            statement.setString(5, this.flightCharge.getText());
            statement.setString(6, this.seats.getText());
            statement.setString(7, this.destination.getText());
            statement.setString(8, this.arrivalTime.getText());
            statement.execute();
            connection.close();

            flightID.clear();
            flightName.clear();
            source.clear();
            destination.clear();
            departure.clear();
            seats.clear();
            arrivalTime.clear();
            flightCharge.clear();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void removeFlight(ActionEvent event) {
        String sqlDelete = "DELETE FROM flightData WHERE flight_ID = '"+flightID.getText() + "' OR  flight_name = '" + flightName.getText() + "'";
        try {
            //connecting to database
            Connection connection = dbConnection.getConnection();
            this.data = FXCollections.observableArrayList();
            ResultSet rs = connection.createStatement().executeQuery(sqlDelete);
            //add data from database to tableView
            while (rs.next()) {
                this.data.add(new FlightData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        this.idColumn.setCellValueFactory(new PropertyValueFactory<FlightData, String>("flightID"));
        this.nameColumn.setCellValueFactory(new PropertyValueFactory<FlightData, String>("flightName"));
        this.sourceColumn.setCellValueFactory(new PropertyValueFactory<FlightData, String>("source"));
        this.departureColumn.setCellValueFactory(new PropertyValueFactory<FlightData, String>("departure"));
        this.chargeColumn.setCellValueFactory(new PropertyValueFactory<FlightData, String>("flightCharge"));
        this.seatsColumn.setCellValueFactory(new PropertyValueFactory<FlightData, String>("seats"));
        this.destinationColumn.setCellValueFactory(new PropertyValueFactory<FlightData, String>("destination"));
        this.timeColumn.setCellValueFactory(new PropertyValueFactory<FlightData, String>("arrivalTime"));

        this.flightData.setItems(null);
        this.flightData.setItems(this.data);

        flightID.clear();
        flightName.clear();
        source.clear();
        destination.clear();
        departure.clear();
        seats.clear();
        arrivalTime.clear();
        flightCharge.clear();
    }

    @FXML
    public void loadFlightData(ActionEvent event) {
        try {
            //connecting to database
            Connection connection = dbConnection.getConnection();
            this.data = FXCollections.observableArrayList();
            ResultSet rs = connection.createStatement().executeQuery(sql);
            //add data from database to tableView
            while (rs.next()) {
                this.data.add(new FlightData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        this.idColumn.setCellValueFactory(new PropertyValueFactory<FlightData, String>("flightID"));
        this.nameColumn.setCellValueFactory(new PropertyValueFactory<FlightData, String>("flightName"));
        this.sourceColumn.setCellValueFactory(new PropertyValueFactory<FlightData, String>("source"));
        this.departureColumn.setCellValueFactory(new PropertyValueFactory<FlightData, String>("departure"));
        this.chargeColumn.setCellValueFactory(new PropertyValueFactory<FlightData, String>("flightCharge"));
        this.seatsColumn.setCellValueFactory(new PropertyValueFactory<FlightData, String>("seats"));
        this.destinationColumn.setCellValueFactory(new PropertyValueFactory<FlightData, String>("destination"));
        this.timeColumn.setCellValueFactory(new PropertyValueFactory<FlightData, String>("arrivalTime"));

        this.flightData.setItems(null);
        this.flightData.setItems(this.data);
    }


    @FXML
    public void getDetailsAction(ActionEvent event) {
        if (!sourceStationBox.getValue().equals("") && !destinationBox.getValue().equals("") && !date.getEditor().getText().equals("")) {
            //to make the buttons visible
            instruction.setVisible(true);
            flightData1.setVisible(true);
            //set disable to combo boxes and buttons
            sourceStationBox.setDisable(true);
            destinationBox.setDisable(true);
            date.setDisable(true);
            getDetails.setDisable(true);
            String sqlSelect = "SELECT * FROM flightData WHERE source ='" + sourceStationBox.getValue() + "' AND destination = '" + destinationBox.getValue() + "'";

            try {
                //connecting to database
                Connection connection = dbConnection.getConnection();
                this.data1 = FXCollections.observableArrayList();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect);
                ResultSet rs = connection.createStatement().executeQuery(sqlSelect);
                //add data from database to tableView
                while (rs.next()) {
                    this.data1.add(new FlightData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            this.idColumn1.setCellValueFactory(new PropertyValueFactory<FlightData, String>("flightID"));
            this.nameColumn1.setCellValueFactory(new PropertyValueFactory<FlightData, String>("flightName"));
            this.sourceColumn1.setCellValueFactory(new PropertyValueFactory<FlightData, String>("source"));
            this.departureColumn1.setCellValueFactory(new PropertyValueFactory<FlightData, String>("departure"));
            this.chargeColumn1.setCellValueFactory(new PropertyValueFactory<FlightData, String>("flightCharge"));
            this.seatsColumn1.setCellValueFactory(new PropertyValueFactory<FlightData, String>("seats"));
            this.destinationColumn1.setCellValueFactory(new PropertyValueFactory<FlightData, String>("destination"));
            this.timeColumn1.setCellValueFactory(new PropertyValueFactory<FlightData, String>("arrivalTime"));

            this.flightData1.setItems(null);
            this.flightData1.setItems(this.data1);

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeight(100);
            alert.setWidth(50);
            alert.setHeaderText("Please fill the all fields!");
            alert.showAndWait();

        }
    }


}
