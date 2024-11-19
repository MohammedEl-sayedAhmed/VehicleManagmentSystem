package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Vehicle;
import controller.RentalController;

public class RentVehicleFrame extends JFrame {
    private RentalController rentalController;
    private JComboBox<String> vehicleComboBox;
    private JComboBox<String> customerComboBox;
    private JComboBox<String> paymentMethodComboBox;
    private JTextField durationInput;
    private JTextField extraFeesInput;
    private JCheckBox renterInsuranceCheckBox;
    private JButton rentButton;
    private JTable rentedVehiclesTable;
    private JButton openPaymentProcessingButton;

    public RentVehicleFrame(RentalController rentalController) {
        this.rentalController = rentalController;
        setTitle("Rent Vehicle");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 2));

        JLabel vehicleLabel = new JLabel("Select Vehicle:");
        vehicleComboBox = new JComboBox<>(getAvailableVehicles());
        JLabel customerLabel = new JLabel("Select Customer:");
        customerComboBox = new JComboBox<>(getCustomerList());
        JLabel paymentMethodLabel = new JLabel("Payment Method:");
        paymentMethodComboBox = new JComboBox<>(new String[]{"Credit Card", "Debit Card", "PayPal", "Cash"});
        JLabel durationLabel = new JLabel("Duration (days):");
        durationInput = new JTextField();
        JLabel extraFeesLabel = new JLabel("Extra Fees (damages,etc..):");
        extraFeesInput = new JTextField();
        JLabel renterInsuranceLabel = new JLabel("Renter's Insurance:");
        renterInsuranceCheckBox = new JCheckBox();
        rentButton = new JButton("Rent Vehicle");

        add(vehicleLabel);
        add(vehicleComboBox);
        add(customerLabel);
        add(customerComboBox);
        add(paymentMethodLabel);
        add(paymentMethodComboBox);
        add(durationLabel);
        add(durationInput);
        add(extraFeesLabel);
        add(extraFeesInput);
        add(renterInsuranceLabel);
        add(renterInsuranceCheckBox);
        add(rentButton);

        rentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rentVehicle();
            }
        });

        rentedVehiclesTable = new JTable();
        updateRentedVehiclesTable();
        JScrollPane scrollPane = new JScrollPane(rentedVehiclesTable);
        add(scrollPane);
    }

    private String[] getAvailableVehicles() {
        return rentalController.getVehicles().values().stream()
                .filter(Vehicle::isAvailable)
                .map(Vehicle::getId)
                .toArray(String[]::new);
    }

    private String[] getCustomerList() {
        return rentalController.getCustomers().values().stream()
                .map(customer -> customer.getPhoneNumber() + " - " + customer.getName())
                .toArray(String[]::new);
    }

    private void rentVehicle() {
        String vehicleID = (String) vehicleComboBox.getSelectedItem();
        String customerPhone = customerComboBox.getSelectedItem().toString().split(" - ")[0];
        String paymentMethod = (String) paymentMethodComboBox.getSelectedItem();
        int duration;
        double extraFees;

        try {
            duration = Integer.parseInt(durationInput.getText());
            if (duration <= 0) {
                throw new NumberFormatException();
            }
            extraFees = Double.parseDouble(extraFeesInput.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid duration and extra fees.");
            return;
        }

        boolean renterInsurance = renterInsuranceCheckBox.isSelected();
        rentalController.rentVehicle(customerPhone, vehicleID, duration, extraFees, renterInsurance, paymentMethod);
        clearInputFields();
        updateRentedVehiclesTable();
        updateAvailableVehicles();
    }

    private void clearInputFields() {
        vehicleComboBox.setSelectedIndex(0);
        customerComboBox.setSelectedIndex(0);
        paymentMethodComboBox.setSelectedIndex(0);
        durationInput.setText("");
        extraFeesInput.setText("");
        renterInsuranceCheckBox.setSelected(false);
    }

    private void updateRentedVehiclesTable() {
        String[] columnNames = {"Vehicle ID", "Customer Phone", "Duration (days)"};
        Object[][] data = rentalController.getTransactions().values().stream()
                .map(transaction -> new Object[]{
                        transaction.getVehicle().getId(),
                        transaction.getCustomer().getPhoneNumber(),
                        transaction.getDuration()
                })
                .toArray(Object[][]::new);

        rentedVehiclesTable.setModel(new DefaultTableModel(data, columnNames));
    }

    private void updateAvailableVehicles() {
        vehicleComboBox.removeAllItems();
        for (String vehicleID : rentalController.getVehicles().keySet()) {
            Vehicle vehicle = rentalController.getVehicles().get(vehicleID);
            if (vehicle.isAvailable()) {
                vehicleComboBox.addItem(vehicleID);
            }
        }
    }
}