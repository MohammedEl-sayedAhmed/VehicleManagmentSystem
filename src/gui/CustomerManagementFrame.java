package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Customer;
import controller.RentalController;

public class CustomerManagementFrame extends JFrame {
    private RentalController rentalController;
    private JTextField customerNameInput;
    private JTextField customerAgeInput;
    private JTextField customerPhoneInput;
    private JTextField customerIDInput;
    private JTable customerTable;
    private DefaultTableModel tableModel;

    public CustomerManagementFrame() {
        this.rentalController = new RentalController();
        setTitle("Customer Management");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel customerIDLabel = new JLabel("Customer ID:");
        customerIDInput = new JTextField(15);
        customerIDInput.setEditable(false); // ID will be generated automatically

        JLabel customerNameLabel = new JLabel("Customer Name:");
        customerNameInput = new JTextField(15);
        JLabel customerAgeLabel = new JLabel("Customer Age:");
        customerAgeInput = new JTextField(15);
        JLabel customerPhoneLabel = new JLabel("Customer Phone:");
        customerPhoneInput = new JTextField(15);
        
        JButton registerButton = new JButton("Register Customer");

        // Table setup
        String[] columnNames = {"ID", "Name", "Age", "Phone"};
        tableModel = new DefaultTableModel(columnNames, 0);
        customerTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(customerTable);
        scrollPane.setPreferredSize(new Dimension(500, 150));

        // Layout configuration
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(customerIDLabel, gbc);
        gbc.gridx = 1;
        add(customerIDInput, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(customerNameLabel, gbc);
        gbc.gridx = 1;
        add(customerNameInput, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(customerAgeLabel, gbc);
        gbc.gridx = 1;
        add(customerAgeInput, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(customerPhoneLabel, gbc);
        gbc.gridx = 1;
        add(customerPhoneInput, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(registerButton, gbc);
        
        gbc.gridy = 5;
        add(new JLabel("Registered Customers:"), gbc);
        
        gbc.gridy = 6;
        add(scrollPane, gbc);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerCustomer();
            }
        });

        generateCustomerID(); // Generate a unique ID when the frame is opened
        loadCustomers(); // Load existing customers into the table
    }

    private void generateCustomerID() {
        String uniqueID = "C" + (rentalController.getCustomersCount() + 1);
        customerIDInput.setText(uniqueID);
    }

    private void registerCustomer() {
        try {
            String id = customerIDInput.getText();
            String name = customerNameInput.getText();
            int age = Integer.parseInt(customerAgeInput.getText());
            String phone = customerPhoneInput.getText();

            if (name.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all the required fields.");
                return;
            }

            Customer customer = new Customer(name, age, phone);
            customer.setId(id); // Set the unique ID
            rentalController.addCustomer(customer);
            addCustomerToTable(customer);
            clearInputFields();
            generateCustomerID(); // Generate a new ID for the next customer
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers.");
        }
    }

    private void addCustomerToTable(Customer customer) {
        tableModel.addRow(new Object[]{customer.getId(), customer.getName(), customer.getAge(), customer.getPhoneNumber()});
    }

    private void loadCustomers() {
        for (Customer customer : rentalController.getCustomers().values()) {
            addCustomerToTable(customer);
        }
    }

    private void clearInputFields() {
        customerNameInput.setText("");
        customerAgeInput.setText("");
        customerPhoneInput.setText("");
    }
}
