package com.example.chroma;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppStoreCSVUtil {

    // Method to load app requests from the CSV file and return a list of App objects
    public static List<App> loadAppRequestsFromCSV(String filePath) {
        List<App> appRequests = new ArrayList<>();
        try {
            List<String> appRequestLines = Files.readAllLines(Paths.get(filePath));
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
        return appRequests;
    }
}
