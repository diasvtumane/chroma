package com.example.chroma;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AppStore {

    private static AppStore instance;
    private List<App> apps;
    private List<AppRequest> appRequests;

    private AppStore() {
        apps = new ArrayList<>();
        appRequests = new ArrayList<>();
    }

    public static AppStore getInstance() {
        if (instance == null) {
            instance = new AppStore();
        }
        return instance;
    }

    public List<App> getApps() {
        return apps;
    }

    public void addApp(App app) {
        apps.add(app);
    }

    public void addAppRequest(AppRequest appRequest) {
        appRequests.add(appRequest);
    }

    public void sortApps(Comparator<App> comparator) {
        Collections.sort(apps, comparator);
    }

    public void loadAppsFromCSV(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            String name = parts[0].trim();
            String version = parts[1].trim();
            List<String> platforms = new ArrayList<>(Arrays.asList(parts[2].trim().split(",\\s*")));
            App app = new App(name, version, platforms);
            apps.add(app);
        }
        reader.close();
    }

    public void saveAppsToCSV(String filename) throws IOException {
        FileWriter writer = new FileWriter(filename);
        for (App app : apps) {
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
        System.out.println("Data written to CSV successfully.");
    }
}
