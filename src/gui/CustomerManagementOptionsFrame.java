package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerManagementOptionsFrame extends JFrame {
    public CustomerManagementOptionsFrame() {
        setTitle("Customer Management Options");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        JButton registerCustomerButton = new JButton("Register New Customer");

        add(registerCustomerButton);

        registerCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCustomerManagementFrame();
            }
        });

    }

    private void openCustomerManagementFrame() {
        CustomerManagementFrame customerManagementFrame = new CustomerManagementFrame();
        customerManagementFrame.setVisible(true);
    }
}
