package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.RentalController;
import model.RentalTransaction;
import model.Payment;

public class PaymentProcessingFrame extends JFrame {
    private RentalController rentalController;
    private JTextField transactionIDInput;
    private JComboBox<String> paymentMethodInput;
    private JButton processPaymentButton;

    public PaymentProcessingFrame(RentalController rentalController) {
        this.rentalController = rentalController;
        setTitle("Payment Processing");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        JLabel transactionIDLabel = new JLabel("Transaction ID:");
        transactionIDInput = new JTextField();
        JLabel paymentMethodLabel = new JLabel("Payment Method:");
        paymentMethodInput = new JComboBox<>(new String[]{"Credit Card", "Debit Card", "PayPal", "Cash"});
        processPaymentButton = new JButton("Process Payment");

        add(transactionIDLabel);
        add(transactionIDInput);
        add(paymentMethodLabel);
        add(paymentMethodInput);
        add(processPaymentButton);

    }


}
