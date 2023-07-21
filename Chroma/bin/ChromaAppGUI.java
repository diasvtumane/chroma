import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ChromaAppGUI extends JFrame {
    private JTextField searchField;
    private JButton searchButton;
    private JTable appTable;
    private JTextArea appDetailsTextArea;
    private AppRepository appRepository;

    public ChromaAppGUI() {
        // Set up the main frame
        setTitle("Chroma - App Library");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize the app repository
        appRepository = new AppRepository();

        // Initialize and add GUI components
        initComponents();
        addComponentsToFrame();


        // Load sample data (for testing purposes)
        loadSampleData();
    }

    private void initComponents() {
        // Initialize GUI components here
        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        appTable = new JTable(new DefaultTableModel(new Object[]{"Name", "Description", "Developer", "Version", "Platform", "App Size"}, 0));
        appDetailsTextArea = new JTextArea(10, 30);
        appDetailsTextArea.setEditable(false);

        // Add event listener for the search button
        searchButton.addActionListener(e -> {
            String searchName = searchField.getText().trim();
            List<App> matchingApps = appRepository.searchAppsByName(searchName);
            updateTableData(matchingApps);
        });

        // Add event listener for table selection
        appTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = appTable.getSelectedRow();
                if (selectedRow >= 0) {
                    App selectedApp = appRepository.getApps().get(selectedRow);
                    updateAppDetailsPanel(selectedApp);
                }
            }
        });
    }

    private void addComponentsToFrame() {
        // Add GUI components to the main frame here
        JPanel topPanel = new JPanel();
        topPanel.add(searchField);
        topPanel.add(searchButton);

        JScrollPane tableScrollPane = new JScrollPane(appTable);

        JPanel detailsPanel = new JPanel();
        detailsPanel.add(new JLabel("App Details:"));
        detailsPanel.add(new JScrollPane(appDetailsTextArea));

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tableScrollPane, detailsPanel);

        add(topPanel, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
    }

    // Method to update the table data with a list of apps
    private void updateTableData(List<App> apps) {
        DefaultTableModel model = (DefaultTableModel) appTable.getModel();
        model.setRowCount(0); // Clear the existing rows

        for (App app : apps) {
            Object[] rowData = {app.getName(), app.getDescription(), app.getDeveloper(),
                                app.getVersion(), app.getPlatform(), app.getAppSize()};
            model.addRow(rowData);
        }
    }

    // Method to update the app details panel based on the selected app
    private void updateAppDetailsPanel(App app) {
        appDetailsTextArea.setText(""); // Clear the existing details

        // Create a formatted string to display app details
        StringBuilder details = new StringBuilder();
        details.append("Name: ").append(app.getName()).append("\n");
        details.append("Description: ").append(app.getDescription()).append("\n");
        details.append("Developer: ").append(app.getDeveloper()).append("\n");
        details.append("Version: ").append(app.getVersion()).append("\n");
        details.append("Platform: ").append(app.getPlatform()).append("\n");
        details.append("App Size: ").append(app.getAppSize()).append("\n");
        details.append("Rating: ").append(app.getRating()).append("\n");
        details.append("Likes: ").append(app.getLikes()).append("\n");

        appDetailsTextArea.setText(details.toString());
    }

    // Load sample data (for testing purposes)
    private void loadSampleData() {
        App app1 = new App("App1", "Description 1", "Developer A", "1.0", "Android", "10 MB");
        App app2 = new App("App2", "Description 2", "Developer B", "2.0", "iOS", "15 MB");

        appRepository.addApp(app1);
        appRepository.addApp(app2);

        updateTableData(appRepository.getApps());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChromaAppGUI chromaAppGUI = new ChromaAppGUI();
            chromaAppGUI.setVisible(true);
        });
    }
}
