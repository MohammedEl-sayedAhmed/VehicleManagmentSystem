package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.RentalController;

public class RentalTransactionFrame extends JFrame {
    private RentalController rentalController;
    private JTextField transactionIDInput;
    private JTextField customerPhoneInput;
    private JComboBox<String> vehicleComboBox;
    private JTextField rentalDurationInput;
    private JButton generateReceiptButton;

    public RentalTransactionFrame(RentalController rentalController) {
        this.rentalController = rentalController;
        setTitle("Rental Transactions");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        JLabel transactionIDLabel = new JLabel("Transaction ID:");
        transactionIDInput = new JTextField();
        JLabel customerPhoneLabel = new JLabel("Customer Phone:");
        customerPhoneInput = new JTextField();
        JLabel vehicleLabel = new JLabel("Select Vehicle:");
        vehicleComboBox = new JComboBox<>(new String[]{"V001 - Toyota", "V002 - Honda", "V003 - Ford"});
        JLabel rentalDurationLabel = new JLabel("Rental Duration (days):");
        rentalDurationInput = new JTextField();
        generateReceiptButton = new JButton("Generate Receipt");

        add(transactionIDLabel);
        add(transactionIDInput);
        add(customerPhoneLabel);
        add(customerPhoneInput);
        add(vehicleLabel);
        add(vehicleComboBox);
        add(rentalDurationLabel);
        add(rentalDurationInput);
        add(generateReceiptButton);

        generateReceiptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateReceipt();
            }
        });
    }

    private void generateReceipt() {
    }
}
