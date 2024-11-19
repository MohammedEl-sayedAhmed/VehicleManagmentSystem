package model;

import java.io.Serializable;
import java.util.Date;

public class Reservation implements Serializable {
    private static final long serialVersionUID = 1L; // For serialization
    private String reservationID;
    private Customer customer;
    private Vehicle vehicle;
    private Date reservationDate;
    private boolean status;

    public Reservation(String reservationID, Customer customer, Vehicle vehicle, Date reservationDate) {
        this.reservationID = reservationID;
        this.customer = customer;
        this.vehicle = vehicle;
        this.reservationDate = reservationDate;
        this.status = true; // Default to active
    }

    public String getReservationID() {
        return reservationID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public boolean isStatus() {
        return status;
    }

    public void cancelReservation() {
        this.status = false; 
    }
}
