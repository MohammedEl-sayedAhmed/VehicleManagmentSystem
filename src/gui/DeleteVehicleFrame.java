package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.RentalController;

public class DeleteVehicleFrame extends JFrame {
    private RentalController rentalController;
    private JComboBox<String> vehicleIDInput;

    public DeleteVehicleFrame(RentalController rentalController) {
        this.rentalController = rentalController;
        setTitle("Delete Vehicle");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel vehicleIDLabel = new JLabel("Vehicle ID:");
        vehicleIDInput = new JComboBox<>(getCurrentVehicleIDs());
        JButton deleteButton = new JButton("Delete Vehicle");

        add(vehicleIDLabel);
        add(vehicleIDInput);
        add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteVehicle();
            }
        });
    }

    private String[] getCurrentVehicleIDs() {
        return rentalController.getVehicles().keySet().toArray(new String[0]);
    }

    private void deleteVehicle() {
        String id = (String) vehicleIDInput.getSelectedItem();
        boolean success = rentalController.deleteVehicle(id);
        if (success) {
            JOptionPane.showMessageDialog(this, "Vehicle deleted successfully.");
            vehicleIDInput.removeItem(id);
        } else {
            JOptionPane.showMessageDialog(this, "Vehicle not found.");
        }
    }
}