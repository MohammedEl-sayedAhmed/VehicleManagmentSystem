package model;

import java.io.Serializable;

public class Vehicle implements Serializable {
    private static final long serialVersionUID = 1L; // For serialization
    private String id;
    private String name;
    private String type;
    private double rentalPrice;
    private boolean available;
    private int rentalDuration; 

    public Vehicle(String id, String name, String type, double rentalPrice) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.rentalPrice = rentalPrice;
        this.available = true;
        this.rentalDuration = 0; 
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public double getRentalPrice() { return rentalPrice; }
    
    public boolean isAvailable() { return available; }
    public void setAvailability(boolean available) { this.available = available; }

    // Setter methods
    public void setName(String name) { this.name = name; }
    public void setType(String type) { this.type = type; }
    public void setRentalPrice(double rentalPrice) { this.rentalPrice = rentalPrice; }

    public int getRentalDuration() { return rentalDuration; }
    public void setRentalDuration(int rentalDuration) { this.rentalDuration = rentalDuration; }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Type: " + type + ", Price: " + String.format("%.2f AED", rentalPrice);
    }
}