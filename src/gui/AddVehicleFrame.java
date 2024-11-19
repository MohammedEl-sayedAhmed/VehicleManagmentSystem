package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Vehicle;
import controller.RentalController;

public class AddVehicleFrame extends JFrame {
    private RentalController rentalController;
    private JTextField vehicleIDInput;
    private JTextField vehicleNameInput;
    private JComboBox<String> vehicleTypeInput;
    private JTextField vehiclePriceInput;

    public AddVehicleFrame(RentalController rentalController) {
        this.rentalController = rentalController;
        setTitle("Add New Vehicle");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        JLabel vehicleIDLabel = new JLabel("Vehicle ID:");
        vehicleIDInput = new JTextField();
        vehicleIDInput.setEditable(false);
        vehicleIDInput.setText(generateVehicleID());
        JLabel vehicleNameLabel = new JLabel("Vehicle Name:");
        vehicleNameInput = new JTextField();
        JLabel vehicleTypeLabel = new JLabel("Vehicle Type:");
        String[] vehicleTypes = {"Sedan", "SUV", "Truck", "Coupe", "Convertible", "Hatchback", "Minivan", "Sports Car", "Crossover", "Motorcycle"};
        vehicleTypeInput = new JComboBox<>(vehicleTypes);
        JLabel vehiclePriceLabel = new JLabel("Rental Price:");
        vehiclePriceInput = new JTextField();
        JButton addButton = new JButton("Add Vehicle");

        add(vehicleIDLabel);
        add(vehicleIDInput);
        add(vehicleNameLabel);
        add(vehicleNameInput);
        add(vehicleTypeLabel);
        add(vehicleTypeInput);
        add(vehiclePriceLabel);
        add(vehiclePriceInput);
        add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addVehicle();
            }
        });
    }

    private String generateVehicleID() {
        return rentalController.generateVehicleID();
    }

    private void addVehicle() {
        if (rentalController.getVehicles().size() >= 10) {
            JOptionPane.showMessageDialog(this, "You can only have a maximum of 10 vehicles. Please delete existing vehicles first.", "Limit Reached", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String id = vehicleIDInput.getText();
            String name = vehicleNameInput.getText();
            String type = (String) vehicleTypeInput.getSelectedItem();
            double price = Double.parseDouble(vehiclePriceInput.getText());

            if (name.isEmpty() || type.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                return;
            }

            Vehicle vehicle = new Vehicle(id, name, type, price);
            rentalController.addVehicle(vehicle);
            JOptionPane.showMessageDialog(this, "Vehicle added successfully.");
            clearInputFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid price. Please enter a valid number.");
        }
    }

    private void clearInputFields() {
        vehicleNameInput.setText("");
        vehicleTypeInput.setSelectedIndex(0);
        vehiclePriceInput.setText("");
        vehicleIDInput.setText(generateVehicleID());
    }
}
