package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.RentalController;

public class StoreManagementFrame extends JFrame {
    private RentalController rentalController;
    private JButton salesReportButton;

    public StoreManagementFrame() {
        this.rentalController = new RentalController();
        setTitle("Store Management");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        JButton addVehicleButton = new JButton("Add New Vehicle");
        JButton viewVehiclesButton = new JButton("View Vehicles");
        JButton updateVehicleButton = new JButton("Update Vehicle");
        JButton deleteVehicleButton = new JButton("Delete Vehicle");
        JButton showCurrentRentsButton = new JButton("Show Current Rents");
        JButton rentVehicleButton = new JButton("Rent Vehicle");
        salesReportButton = new JButton("View Sales Report");

        add(addVehicleButton);
        add(viewVehiclesButton);
        add(updateVehicleButton);
        add(deleteVehicleButton);
        add(showCurrentRentsButton);
        add(rentVehicleButton);
        add(salesReportButton);

        // Add action listeners for each button
        addVehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAddVehicleFrame();
            }
        });

        viewVehiclesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewVehicles();
            }
        });

        updateVehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openUpdateVehicleFrame();
            }
        });

        deleteVehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openDeleteVehicleFrame();
            }
        });

        showCurrentRentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCurrentRents();
            }
        });

        rentVehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openRentVehicleFrame();
            }
        });

        salesReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SalesReportFrame salesReportFrame = new SalesReportFrame();
                salesReportFrame.setVisible(true);
            }
        });
    }

    private void openAddVehicleFrame() {
        AddVehicleFrame addVehicleFrame = new AddVehicleFrame(rentalController);
        addVehicleFrame.setVisible(true);
    }

    private void viewVehicles() {
        String[] columnNames = {"Vehicle ID", "Vehicle Name", "Vehicle Type", "Rental Price (AED)", "Availability"};
        Object[][] data = rentalController.getVehicles().values().stream()
                .map(vehicle -> new Object[]{
                        vehicle.getId(),
                        vehicle.getName(),
                        vehicle.getType(),
                        String.format("%.2f AED", vehicle.getRentalPrice()),
                        vehicle.isAvailable() ? "Available" : "Not Available"
                })
                .toArray(Object[][]::new);

        if (data.length == 0) {
            JOptionPane.showMessageDialog(this, "No vehicles available.", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JTable vehicleTable = new JTable(data, columnNames);
        vehicleTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(vehicleTable);
        JFrame tableFrame = new JFrame("Vehicle List");
        tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tableFrame.setSize(600, 300);
        tableFrame.add(scrollPane);
        tableFrame.setVisible(true);
    }

    private void openUpdateVehicleFrame() {
        UpdateVehicleFrame updateVehicleFrame = new UpdateVehicleFrame(rentalController);
        updateVehicleFrame.setVisible(true);
    }

    private void openDeleteVehicleFrame() {
        DeleteVehicleFrame deleteVehicleFrame = new DeleteVehicleFrame(rentalController);
        deleteVehicleFrame.setVisible(true);
    }

    private void showCurrentRents() {
        CurrentRentsFrame currentRentsFrame = new CurrentRentsFrame(rentalController);
        currentRentsFrame.setVisible(true);
    }

    private void openRentVehicleFrame() {
        RentVehicleFrame rentVehicleFrame = new RentVehicleFrame(rentalController);
        rentVehicleFrame.setVisible(true);
    }
}
