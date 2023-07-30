package com.example.chroma;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AppStoreGUI extends JFrame {

    private List<App> appList = new ArrayList<>();
    private DefaultListModel<App> listModel;
    private JList<App> appJList;
    private JTextField searchField;
    private JButton sortButton;
    private int sortClickCount;
    private List<App> appRequests = new ArrayList<>();

    private class AppListCellRenderer extends DefaultListCellRenderer {
        private final Font largerFont;

        public AppListCellRenderer() {
            largerFont = new Font(getFont().getName(), Font.PLAIN, getFont().getSize() + 4);
        }

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            setFont(largerFont);
            return this;
        }
    }

    public AppStoreGUI() {
        listModel = new DefaultListModel<>();
        appJList = new JList<>(listModel);
        searchField = new JTextField(20);
        sortButton = new JButton("Sort");
        sortClickCount = 0;

        loadAppRequestsFromCSV(); // Load app requests from the app_requests.csv file

        setTitle("Chroma Store");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the frame

        // Create a panel for the search bar and Sort button
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Search bar
        topPanel.add(searchField, BorderLayout.CENTER);

        // Sort button
        topPanel.add(sortButton, BorderLayout.WEST);

        // Admin button
        JButton adminButton = new JButton("Admin");
        topPanel.add(adminButton, BorderLayout.EAST);

        // Add headerPanel to the top of the frame
        add(topPanel, BorderLayout.NORTH);

        // App list panel
        JScrollPane appListScrollPane = new JScrollPane(appJList);
        appListScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create a panel for the bottom buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Adjust the gap as needed

        // Add Request button
        JButton addRequestButton = new JButton("Add Request");
        bottomPanel.add(addRequestButton);

        // Register a listener for the Add Request button
        addRequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddRequestWindow();
            }
        });

        // Add bottom panel to the frame
        add(bottomPanel, BorderLayout.SOUTH);

        // Add components to the frame
        add(appListScrollPane, BorderLayout.CENTER);

        // Register a listener for the search field
        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterApps(searchField.getText());
            }
        });

        // Register a listener for the Sort button
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortClickCount++;
                sortApps();
            }
        });

        // Register a listener for the Admin button
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAdminLoginDialog();
            }
        });

        appJList.setCellRenderer(new AppListCellRenderer()); // Set a custom cell renderer for the app list

        // Show the frame
        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    // Method to add an app request to the list and save it to CSV
    public void addAppRequest(App appRequest) {
        appRequests.add(appRequest); // Add to the list of app requests
        saveAppRequestToCSV(appRequest); // Save the app request to the CSV file
    }

    public List<App> getAppRequests() {
        return appRequests;
    }

    // Method to filter the app list based on search input
    private void filterApps(String searchString) {
        listModel.clear();
        for (App app : appList) {
            if (app.getName().toLowerCase().contains(searchString.toLowerCase())
                    || app.getVersion().toLowerCase().contains(searchString.toLowerCase())
                    || app.getPlatforms().stream().anyMatch(platform -> platform.toLowerCase().contains(searchString.toLowerCase()))) {
                listModel.addElement(app);
            }
        }
    }

    public void refreshAdminAppRequests() {
        listModel.clear();
        for (App appRequest : appRequests) {
            listModel.addElement(appRequest);
        }
    }

    // Method to sort the app list based on the sortClickCount
    private void sortApps() {
        switch (sortClickCount % 4) {
            case 0: // Unsorted, restore the original order
                refreshAppListModel();
                break;
            case 1: // Sort by name (alphabetical order)
                Collections.sort(appList, Comparator.comparing(App::getName));
                refreshAppListModel();
                break;
            case 2: // Sort by version (in descending order)
                Collections.sort(appList, (app1, app2) -> app2.getVersion().compareTo(app1.getVersion()));
                refreshAppListModel();
                break;
            case 3: // Sort by platforms (Windows first, then Mac)
                Collections.sort(appList, new Comparator<App>() {
                    @Override
                    public int compare(App app1, App app2) {
                        boolean app1HasWindows = app1.getPlatforms().contains("Windows");
                        boolean app2HasWindows = app2.getPlatforms().contains("Windows");
                        if (app1HasWindows && !app2HasWindows) {
                            return -1;
                        } else if (!app1HasWindows && app2HasWindows) {
                            return 1;
                        } else {
                            return app1.getName().compareToIgnoreCase(app2.getName());
                        }
                    }
                });
                refreshAppListModel();
                break;
        }
    }

    // Method to refresh the JList based on the sorted appList
    private void refreshAppListModel() {
        listModel.clear();
        for (App app : appList) {
            listModel.addElement(app);
        }
    }

    private void showAdminAppRequestsWindow(JDialog adminDialog) {
        AdminAppRequestsWindow adminAppRequestsWindow = new AdminAppRequestsWindow(this, appRequests); // Pass the appRequests list
        adminAppRequestsWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                refreshAdminAppRequests(); // Refresh the admin app requests list after the AdminAppRequestsWindow is closed
                adminDialog.dispose(); // Close the admin login dialog
            }
        });
        adminAppRequestsWindow.setVisible(true); // Show the admin window
    }

    public void removeAppFromExistingAppsList(App app) {
        appList.remove(app);
        refreshAppListModel();
    }

    // Method to remove an app request from the CSV file
    public void removeAppRequest(App appRequest) {
        try {
            List<String> appRequestLines = Files.readAllLines(Paths.get("app_requests.csv"));
            List<String> newAppRequestLines = new ArrayList<>();
            for (String line : appRequestLines) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String name = parts[0].trim();
                    String version = parts[1].trim();
                    List<String> platforms = Arrays.asList(parts[2].trim().split(",\\s*"));
                    App app = new App(name, version, platforms);
                    if (!app.equals(appRequest)) {
                        newAppRequestLines.add(line); // Add the line if it's not the one to remove
                    }
                }
            }
            Files.write(Paths.get("app_requests.csv"), newAppRequestLines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to save the existing apps to CSV
    public void saveExistingAppsToCSV() {
        try {
            FileWriter writer = new FileWriter("existing_apps.csv");
            for (App app : appList) {
                writer.append(app.getName());
                writer.append(",");
                writer.append(app.getVersion());
                writer.append(",");
                String platforms = String.join(", ", app.getPlatforms());
                writer.append(platforms);
                writer.append("\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to add an app to the existing apps and save it to CSV
    public void addAppToExistingApps(App app) {
        appList.add(app);
        refreshAppListModel();
    }

    // Method to save an app request to the CSV file
    public void saveAppRequestToCSV(App appRequest) {
        // Check if the app request already exists in the appList
        if (!appList.contains(appRequest)) {
            try {
                FileWriter writer = new FileWriter("app_requests.csv", true);
                writer.append(appRequest.getName());
                writer.append(",");
                writer.append(appRequest.getVersion());
                writer.append(",");
                String platforms = String.join(", ", appRequest.getPlatforms());
                writer.append(platforms);
                writer.append("\n");
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to load app requests from the app_requests.csv file
    private void loadAppRequestsFromCSV() {
        try {
            List<String> appRequestLines = Files.readAllLines(Paths.get("app_requests.csv"));
            for (String line : appRequestLines) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String name = parts[0].trim();
                    String version = parts[1].trim();
                    List<String> platforms = Arrays.asList(parts[2].trim().split(",\\s*"));
                    App appRequest = new App(name, version, platforms);
                    appRequests.add(appRequest);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to show the admin login dialog
    private void showAdminLoginDialog() {
        JDialog adminDialog = new JDialog();
        adminDialog.setTitle("Admin Login");
        adminDialog.setSize(400, 200); // Adjust the size as needed
        adminDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        adminDialog.setLocationRelativeTo(null); // Center the dialog on the screen

        // Create a panel for the components in the admin dialog
        JPanel adminPanel = new JPanel(new GridLayout(4, 2, 10, 10)); // Adjust the row, column, and gap as needed

        // Add components for admin login
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(15); // Adjust the width as needed

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(15); // Adjust the width as needed

        JButton loginButton = new JButton("Login"); // Declare the loginButton variable
        JLabel messageLabel = new JLabel();

        // Add components to the admin panel
        adminPanel.add(usernameLabel);
        adminPanel.add(usernameField);
        adminPanel.add(passwordLabel);
        adminPanel.add(passwordField);
        adminPanel.add(new JLabel()); // Empty label for spacing
        adminPanel.add(loginButton);
        adminPanel.add(messageLabel); // Label to display login status message

        // Add action listener to the "Login" button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Check if the entered username and password match the correct credentials
                if (username.equals("admin") && password.equals("password")) {
                    messageLabel.setText("Logged in successfully!");
                    // Create and show the AdminAppRequestsWindow
                    new AdminAppRequestsWindow(AppStoreGUI.this, appRequests); // Instantiate and show the admin window
                    adminDialog.dispose(); // Close the admin login dialog
                } else {
                    messageLabel.setText("Incorrect login or password");
                }
            }
        });

        // Add the admin panel to the admin dialog
        adminDialog.add(adminPanel);

        // Show the dialog
        adminDialog.setVisible(true);
    }

    // Method to show the Add Request window
    private void showAddRequestWindow() {
        new AddRequestWindow(this);
    }

    private void loadExistingAppsFromCSV() {
        try {
            List<String> existingAppsLines = Files.readAllLines(Paths.get("existing_apps.csv"));
            for (String line : existingAppsLines) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String name = parts[0].trim();
                    String version = parts[1].trim();
                    List<String> platforms = Arrays.asList(parts[2].trim().split(",\\s*"));
                    App existingApp = new App(name, version, platforms);
                    appList.add(existingApp);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppStoreGUI appStoreGUI = new AppStoreGUI(); // Instantiate the AppStoreGUI and make it visible
            appStoreGUI.loadExistingAppsFromCSV(); // Load existing apps from the existing_apps.csv file
            appStoreGUI.refreshAppListModel(); // Refresh the app list model to show existing apps
        });
    }
}
