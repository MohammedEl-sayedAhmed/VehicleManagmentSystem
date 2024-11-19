package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SalesReportFrame extends JFrame {
    private JTable salesTable;

    public SalesReportFrame() {
        setTitle("Sales Report");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Title label
        JLabel titleLabel = new JLabel("Sales Report", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        salesTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(salesTable);
        add(scrollPane, BorderLayout.CENTER);

        loadSalesData();
    }

    private void loadSalesData() {
        File receiptsDir = new File("receipts");
        File[] receiptFiles = receiptsDir.listFiles((dir, name) -> name.endsWith(".txt"));

        String[] columnNames = {"Payment ID", "Customer", "Vehicle", "Rental Duration", "Extra Fees", "Renter's Insurance", "Total Amount"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        if (receiptFiles != null) {
            for (File file : receiptFiles) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    StringBuilder receiptContent = new StringBuilder();
                    String line;
                    String paymentID = file.getName().replace(".txt", "");
                    String customer = "";
                    String vehicle = "";
                    String rentalDuration = "";
                    String extraFees = "";
                    String renterInsurance = "";
                    String totalAmount = "";

                    while ((line = reader.readLine()) != null) {
                        receiptContent.append(line).append("<br>"); // <br> for line breaks
                        if (line.startsWith("Customer: ")) {
                            customer = line.replace("Customer: ", "");
                        } else if (line.startsWith("Vehicle: ")) {
                            vehicle = line.replace("Vehicle: ", "");
                        } else if (line.startsWith("Rental Duration: ")) {
                            rentalDuration = line.replace("Rental Duration: ", "");
                        } else if (line.startsWith("Extra Fees: ")) {
                            extraFees = line.replace("Extra Fees: ", "");
                        } else if (line.startsWith("Renter's Insurance: ")) {
                            renterInsurance = line.replace("Renter's Insurance: ", "");
                        } else if (line.startsWith("Total Amount: ")) {
                            totalAmount = line.replace("Total Amount: ", "");
                        }
                    }

                    model.addRow(new Object[]{paymentID, customer, vehicle, rentalDuration, extraFees, renterInsurance, totalAmount});
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        salesTable.setModel(model);
        salesTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (value instanceof String) {
                    setText((String) value);
                    setHorizontalAlignment(SwingConstants.LEFT);
                }
                return c;
            }
        });
    }
}