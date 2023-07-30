package com.example.chroma;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminAppRequestsWindow extends JFrame {

    private AppStoreGUI appStoreGUI;
    private DefaultListModel<App> listModel;
    private JList<App> appJList;
    private List<App> appRequests;

    public AdminAppRequestsWindow(AppStoreGUI appStoreGUI, List<App> appRequests) {
        this.appStoreGUI = appStoreGUI;
        this.appRequests = appRequests; // Initialize appRequests from the parameter
        listModel = new DefaultListModel<>();
        appJList = new JList<>(listModel);

        setTitle("Admin App Requests");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window on the screen

        // Load app requests from CSV file
        loadAppRequestsFromCSV();

        // App list panel
        JScrollPane appListScrollPane = new JScrollPane(appJList);
        appListScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        // Accept button
        JButton acceptButton = new JButton("Accept");
        buttonPanel.add(acceptButton);

        // Register a listener for the Accept button
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                App selectedAppRequest = appJList.getSelectedValue();
                if (selectedAppRequest != null) {
                    appStoreGUI.addAppToExistingApps(selectedAppRequest); // Add to existing_apps.csv
                    appStoreGUI.removeAppRequest(selectedAppRequest); // Remove from app requests list
                    appStoreGUI.saveExistingAppsToCSV(); // Save the updated existing apps list to existing_apps.csv
                    refreshAppListModel();
                }
            }
        });

        // Decline button
        JButton declineButton = new JButton("Decline");
        buttonPanel.add(declineButton);

        // Register a listener for the Decline button
        declineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                App selectedAppRequest = appJList.getSelectedValue();
                if (selectedAppRequest != null) {
                    appStoreGUI.removeAppRequest(selectedAppRequest); // Remove from app requests list
                    refreshAppListModel();
                }
            }
        });

        // Add components to the frame
        add(appListScrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Show the window
        pack();
        setVisible(true);
    }

    // Method to load app requests from the CSV file
    private void loadAppRequestsFromCSV() {
        List<App> appRequests = AppStoreCSVUtil.loadAppRequestsFromCSV("app_requests.csv");
        for (App app : appRequests) {
            listModel.addElement(app);
        }
    }

    // Method to refresh the JList based on the sorted appList
    private void refreshAppListModel() {
        listModel.clear();
        for (App app : appRequests) {
            listModel.addElement(app);
        }
    }
}
