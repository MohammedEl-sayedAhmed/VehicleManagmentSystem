package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public class Payment implements Serializable {
    private static final long serialVersionUID = 1L; // For serialization
    private String paymentID;
    private RentalTransaction transaction;
    private String paymentMethod;
    private static int paymentCounter = 0; 

    public Payment(RentalTransaction transaction, String paymentMethod) {
        this.paymentID = generatePaymentID(); 
        this.transaction = transaction;
        this.paymentMethod = paymentMethod;
    }

    private String generatePaymentID() {
        paymentCounter++; 
        return "P" + paymentCounter; 
    }

    public void processPayment() {
        
        System.out.println("Processing payment for transaction ID: " + transaction.getTransactionID());
        System.out.println("Payment Method: " + paymentMethod);
        
        // Generate and save receipt
        String receipt = transaction.generateReceipt();
        saveReceipt(receipt);
    }

    private void saveReceipt(String receipt) {
        // Create the receipts directory if it doesn't exist
        File receiptsDir = new File("receipts");
        if (!receiptsDir.exists()) {
            receiptsDir.mkdir(); // Create the directory
        }
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("receipts/" + paymentID + ".txt"))) {
            writer.write(receipt);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}