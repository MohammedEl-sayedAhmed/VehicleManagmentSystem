package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    public MainWindow() {
        setTitle("Welcome to Vehicle Rental Management System");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1));

        JButton storeManagementButton = new JButton("Manage Store");
        JButton customerManagementButton = new JButton("Handle Customers");

        add(storeManagementButton);
        add(customerManagementButton);

        storeManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openStoreManagementWindow();
            }
        });

        customerManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCustomerManagementWindow();
            }
        });
    }

    private void openStoreManagementWindow() {
        StoreManagementFrame storeManagementFrame = new StoreManagementFrame();
        storeManagementFrame.setVisible(true);
    }

    private void openCustomerManagementWindow() {
        CustomerManagementOptionsFrame customerManagementOptionsFrame = new CustomerManagementOptionsFrame();
        customerManagementOptionsFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow();
            mainWindow.setVisible(true);
        });
    }
}
