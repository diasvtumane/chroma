package com.example.chroma;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class AddRequestWindow extends JFrame {

    private AppStoreGUI appStoreGUI;
    private JTextField nameField;
    private JTextField versionField;
    private JTextField platformsField;

    public AddRequestWindow(AppStoreGUI appStoreGUI) {
        this.appStoreGUI = appStoreGUI;
        setTitle("Add App Request");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window on the screen

        // Create a panel for the components
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10)); // Adjust the row, column, and gap as needed

        // Add components for app request
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(15); // Adjust the width as needed

        JLabel versionLabel = new JLabel("Version:");
        versionField = new JTextField(15); // Adjust the width as needed

        JLabel platformsLabel = new JLabel("Platforms (comma-separated):");
        platformsField = new JTextField(20); // Adjust the width as needed

        JButton addButton = new JButton("Add");
        JButton cancelButton = new JButton("Cancel");

        // Add components to the panel
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(versionLabel);
        panel.add(versionField);
        panel.add(platformsLabel);
        panel.add(platformsField);
        panel.add(addButton);
        panel.add(cancelButton);

        // Add action listener to the "Add" button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAppRequest();
            }
        });

        // Add action listener to the "Cancel" button
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Add the panel to the window
        add(panel, BorderLayout.CENTER);

        // Show the window
        pack();
        setVisible(true);
    }

    private void addAppRequest() {
        String name = nameField.getText();
        String version = versionField.getText();
        String platformsText = platformsField.getText();
        List<String> platforms = Arrays.asList(platformsText.split(",\\s*"));

        // Create the App object and add it to the AppStoreGUI
        App newAppRequest = new App(name, version, platforms);
        appStoreGUI.addAppRequest(newAppRequest);

        // Save the request to CSV (Note: This line is not needed in the AddRequestWindow)
        // appStoreGUI.saveAppRequestToCSV(newAppRequest);

        // Close the AddRequestWindow
        dispose();
    }
}
