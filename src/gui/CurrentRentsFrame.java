package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import controller.RentalController;
import model.RentalTransaction;
import java.awt.*;

public class CurrentRentsFrame extends JFrame {
    private RentalController rentalController;
    private JTable currentRentsTable;

    public CurrentRentsFrame(RentalController rentalController) {
        this.rentalController = rentalController;
        setTitle("Current Rents");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        currentRentsTable = new JTable();
        updateCurrentRentsTable();
        JScrollPane scrollPane = new JScrollPane(currentRentsTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void updateCurrentRentsTable() {
        String[] columnNames = {"Transaction ID", "Vehicle ID", "Customer ID", "Customer Name", "Customer Phone", "Duration (days)"};
        Object[][] data = rentalController.getTransactions().values().stream()
                .map(transaction -> new Object[]{
                        transaction.getTransactionID(),
                        transaction.getVehicle().getId(),
                        transaction.getCustomer().getId(),
                        transaction.getCustomer().getName(),
                        transaction.getCustomer().getPhoneNumber(),
                        transaction.getDuration()
                })
                .toArray(Object[][]::new);

        currentRentsTable.setModel(new DefaultTableModel(data, columnNames));
    }
}