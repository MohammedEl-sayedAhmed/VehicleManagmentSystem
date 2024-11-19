package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Vehicle;
import controller.RentalController;

public class ReservationFrame extends JFrame {
    private RentalController rentalController;
    private JComboBox<String> vehicleComboBox;
    private JTextField customerPhoneInput;
    private JButton reserveButton;

    public ReservationFrame(RentalController rentalController) {
        this.rentalController = rentalController;
        setTitle("Reservation System");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        JLabel vehicleLabel = new JLabel("Select Vehicle:");
        vehicleComboBox = new JComboBox<>(new String[]{"V001 - Toyota", "V002 - Honda", "V003 - Ford"});
        JLabel customerPhoneLabel = new JLabel("Customer Phone:");
        customerPhoneInput = new JTextField();
        reserveButton = new JButton("Reserve Vehicle");

        add(vehicleLabel);
        add(vehicleComboBox);
        add(customerPhoneLabel);
        add(customerPhoneInput);
        add(reserveButton);

        reserveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reserveVehicle();
            }
        });
    }

    private void reserveVehicle() {
        String selectedVehicle = (String) vehicleComboBox.getSelectedItem();
        String vehicleID = selectedVehicle.split(" - ")[0];
        String customerPhone = customerPhoneInput.getText();
    }
}
