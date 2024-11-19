package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Vehicle;
import controller.RentalController;

public class UpdateVehicleFrame extends JFrame {
    private RentalController rentalController;
    private JComboBox<String> vehicleIDInput;
    private JTextField vehicleNameInput;
    private JComboBox<String> vehicleTypeInput;
    private JTextField vehiclePriceInput;

    public UpdateVehicleFrame(RentalController rentalController) {
        this.rentalController = rentalController;
        setTitle("Update Vehicle");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        JLabel vehicleIDLabel = new JLabel("Select Vehicle ID:");
        vehicleIDInput = new JComboBox<>(getCurrentVehicleIDs());
        vehicleIDInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                populateVehicleDetails((String) vehicleIDInput.getSelectedItem());
            }
        });

        JLabel vehicleNameLabel = new JLabel("Vehicle Name:");
        vehicleNameInput = new JTextField();
        JLabel vehicleTypeLabel = new JLabel("Vehicle Type:");
        String[] vehicleTypes = {"Sedan", "SUV", "Truck", "Coupe", "Convertible", "Hatchback", "Minivan", "Sports Car", "Crossover", "Motorcycle"};
        vehicleTypeInput = new JComboBox<>(vehicleTypes);
        JLabel vehiclePriceLabel = new JLabel("Rental Price:");
        vehiclePriceInput = new JTextField();
        JButton updateButton = new JButton("Update Vehicle");

        add(vehicleIDLabel);
        add(vehicleIDInput);
        add(vehicleNameLabel);
        add(vehicleNameInput);
        add(vehicleTypeLabel);
        add(vehicleTypeInput);
        add(vehiclePriceLabel);
        add(vehiclePriceInput);
        add(updateButton);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateVehicle();
            }
        });
    }

    private String[] getCurrentVehicleIDs() {
        return rentalController.getVehicles().keySet().toArray(new String[0]);
    }

    private void populateVehicleDetails(String vehicleID) {
        Vehicle vehicle = rentalController.getVehicles().get(vehicleID);
        if (vehicle != null) {
            vehicleNameInput.setText(vehicle.getName());
            vehicleTypeInput.setSelectedItem(vehicle.getType());
            vehiclePriceInput.setText(String.valueOf(vehicle.getRentalPrice()));
        }
    }

    private void updateVehicle() {
        String id = (String) vehicleIDInput.getSelectedItem();
        String name = vehicleNameInput.getText();
        String type = (String) vehicleTypeInput.getSelectedItem();
        double price;

        try {
            price = Double.parseDouble(vehiclePriceInput.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid price. Please enter a valid number.");
            return;
        }

        Vehicle vehicle = rentalController.getVehicles().get(id);
        if (vehicle != null) {
            vehicle.setName(name);
            vehicle.setType(type);
            vehicle.setRentalPrice(price);
            JOptionPane.showMessageDialog(this, "Vehicle updated successfully.");
            clearInputFields();
        } else {
            JOptionPane.showMessageDialog(this, "Vehicle not found.");
        }
    }

    private void clearInputFields() {
        vehicleIDInput.setSelectedIndex(0);
        vehicleNameInput.setText("");
        vehicleTypeInput.setSelectedIndex(0);
        vehiclePriceInput.setText("");
    }
}
