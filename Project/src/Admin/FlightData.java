package Admin;
/*
 * Name : Taukekhan Mustakhov
 * Email : taukiw@gmail.com
 * Date and venue : 04.04.2018 / SDU (Suleyman Demirel University)
 * Description : Flight Data.
 */

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FlightData {

    //Attributes
    private final StringProperty flightID;
    private final StringProperty flightName;
    private final StringProperty source;
    private final StringProperty departure;
    private final StringProperty flightCharge;
    private final StringProperty destination;
    private final StringProperty seats;
    private final StringProperty arrivalTime;

    //Constructor
    public FlightData(String flightID, String flightName, String source, String departure, String flightCharge, String destination, String seats, String arrivalTime) {
        //creating new simpleStringProperty
        this.flightID = new SimpleStringProperty(flightID);
        this.flightName = new SimpleStringProperty(flightName);
        this.source = new SimpleStringProperty(source);
        this.departure = new SimpleStringProperty(departure);
        this.flightCharge = new SimpleStringProperty(flightCharge);
        this.destination = new SimpleStringProperty(destination);
        this.seats = new SimpleStringProperty(seats);
        this.arrivalTime = new SimpleStringProperty(arrivalTime);
    }

    public String getFlightID() {
        return flightID.get();
    }

    public StringProperty flightIDProperty() {
        return flightID;
    }

    public void setFlightID(String flightID) {
        this.flightID.set(flightID);
    }

    public String getFlightName() {
        return flightName.get();
    }

    public StringProperty flightNameProperty() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName.set(flightName);
    }

    public String getSource() {
        return source.get();
    }

    public StringProperty sourceProperty() {
        return source;
    }

    public void setSource(String source) {
        this.source.set(source);
    }

    public String getDeparture() {
        return departure.get();
    }

    public StringProperty departureProperty() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure.set(departure);
    }

    public String getFlightCharge() {
        return flightCharge.get();
    }

    public StringProperty flightChargeProperty() {
        return flightCharge;
    }

    public void setFlightCharge(String flightCharge) {
        this.flightCharge.set(flightCharge);
    }

    public String getDestination() {
        return destination.get();
    }

    public StringProperty destinationProperty() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination.set(destination);
    }

    public String getSeats() {
        return seats.get();
    }

    public StringProperty seatsProperty() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats.set(seats);
    }

    public String getArrivalTime() {
        return arrivalTime.get();
    }

    public StringProperty arrivalTimeProperty() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime.set(arrivalTime);
    }
}
