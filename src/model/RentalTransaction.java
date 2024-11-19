package model;

import java.io.Serializable;

public class RentalTransaction implements Serializable {
    private static final long serialVersionUID = 1L; // For serialization
    private String transactionID;
    private Customer customer;
    private Vehicle vehicle;
    private int rentalDuration;
    private double extraFees;
    private boolean renterInsurance; 

    public RentalTransaction(String transactionID, Customer customer, Vehicle vehicle, int rentalDuration, double extraFees, boolean renterInsurance) {
        this.transactionID = transactionID;
        this.customer = customer;
        this.vehicle = vehicle;
        this.rentalDuration = rentalDuration;
        this.extraFees = extraFees;
        this.renterInsurance = renterInsurance; 
    }

    public String getTransactionID() {
        return transactionID;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getDuration() {
        return rentalDuration;
    }

    public double getExtraFees() {
        return extraFees;
    }

    public boolean hasRenterInsurance() {
        return renterInsurance; 
    }

    public double calculateTotal() {
        double total = vehicle.getRentalPrice() * rentalDuration + extraFees;
        if (renterInsurance) {
            total += 50; 
        }
        return total;
    }

    public String generateReceipt() {
        StringBuilder receipt = new StringBuilder();
        receipt.append("Receipt for Transaction ID: ").append(transactionID).append("\n");
        receipt.append("Customer: ").append(customer.getName()).append("\n");
        receipt.append("Vehicle: ").append(vehicle.getName()).append("\n");
        receipt.append("Rental Duration: ").append(rentalDuration).append(" days\n");
        receipt.append("Extra Fees: ").append(extraFees).append(" AED\n");
        if (renterInsurance) {
            receipt.append("Renter's Insurance: Included\n");
        } else {
            receipt.append("Renter's Insurance: Not Included\n");
        }
        receipt.append("Total Amount: ").append(calculateTotal()).append(" AED\n");
        return receipt.toString();
    }
}
