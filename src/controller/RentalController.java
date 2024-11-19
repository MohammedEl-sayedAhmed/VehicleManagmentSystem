package controller;

import model.Customer;
import model.Vehicle;
import model.RentalTransaction;
import model.Payment;
import java.util.HashMap;
import java.util.Map;
import java.io.*;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;

public class RentalController {

    // In-memory storage for vehicles 
    private Map<String, Vehicle> vehicles = new HashMap<>();
    // In-memory storage for transactions 
    private Map<String, RentalTransaction> transactions = new HashMap<>();
    // In-memory storage for customers 
    private Map<String, Customer> customers = new HashMap<>();
    private static final String CUSTOMER_FILE = "customers.dat";
    private static final String VEHICLE_FILE = "vehicles.dat";
    private static final String TRANSACTION_FILE = "transactions.dat";
    private Set<String> deletedVehicleIDs = new HashSet<>();

    // Initialize with some sample vehicles
    public RentalController() {
        loadCustomers();
        loadVehicles();
        loadTransactions();
    }

    public void rentVehicle(String customerPhone, String vehicleID, int duration, double extraFees, boolean renterInsurance, String paymentMethod) {
        Vehicle vehicle = vehicles.get(vehicleID);
        Customer customer = customers.get(customerPhone);

        if (vehicle != null && customer != null) {
            if (vehicle.isAvailable()) {
                RentalTransaction transaction = new RentalTransaction(generateTransactionID(), customer, vehicle, duration, extraFees, renterInsurance);
                transactions.put(transaction.getTransactionID(), transaction);

                vehicle.setAvailability(false);
                vehicle.setRentalDuration(duration);

                Payment payment = new Payment(transaction, paymentMethod);
                payment.processPayment();

                saveTransactions();
                saveVehicles();

                System.out.println("Vehicle rented to: " + customer.getName());
                JOptionPane.showMessageDialog(null, "Vehicle rented successfully to " + customer.getName());
            } else {
                JOptionPane.showMessageDialog(null, "Vehicle is not available for rent.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vehicle or customer not found.");
        }
    }

    public void addCustomer(Customer customer) {
        customers.put(customer.getPhoneNumber(), customer); // Use phone number as a unique key to display
        saveCustomers();
        System.out.println("Customer added: " + customer.getName());
    }

    private void saveCustomers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CUSTOMER_FILE))) {
            oos.writeObject(customers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void loadCustomers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CUSTOMER_FILE))) {
            customers = (Map<String, Customer>) ois.readObject();
        } catch (FileNotFoundException e) {
            // File not found, no customers to load
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Helper methods for generating unique IDs
    private String generateTransactionID() {
        return "T" + (transactions.size() + 1);
    }

    public int getCustomersCount() {
        return customers.size();
    }

    public Map<String, Customer> getCustomers() {
        return customers;
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.put(vehicle.getId(), vehicle);
        saveVehicles();
        System.out.println("Vehicle added: " + vehicle.getName());
    }


    private void saveVehicles() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(VEHICLE_FILE))) {
            oos.writeObject(vehicles);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void loadVehicles() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(VEHICLE_FILE))) {
            vehicles = (Map<String, Vehicle>) ois.readObject();
        } catch (FileNotFoundException e) {
            // File not found, no vehicles to load
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Vehicle> getVehicles() {
        return vehicles;
    }

    public String generateVehicleID() {
        int id = 1;
        while (vehicles.containsKey("V" + id) || deletedVehicleIDs.contains("V" + id)) {
            id++;
        }
        return "V" + id;
    }

    public boolean deleteVehicle(String id) {
        if (vehicles.remove(id) != null) {
            deletedVehicleIDs.add(id); // Add the deleted ID to the set
            return true; 
        } else {
            return false; 
        }
    }

    
    public Map<String, RentalTransaction> getTransactions() {
        return transactions;
    }

    private void saveTransactions() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(TRANSACTION_FILE))) {
            oos.writeObject(transactions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void loadTransactions() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(TRANSACTION_FILE))) {
            transactions = (Map<String, RentalTransaction>) ois.readObject();
        } catch (FileNotFoundException e) {
            // File not found, no transactions to load
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
