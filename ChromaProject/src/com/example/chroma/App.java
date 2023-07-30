package com.example.chroma;

import java.util.List;

public class App {
    private String name;
    private String version;
    private List<String> platforms;

    public App(String name, String version, List<String> platforms) {
        this.name = name;
        this.version = version;
        this.platforms = platforms;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public List<String> getPlatforms() {
        return platforms;
    }

    // Add toString method to convert App to CSV format
    @Override
    public String toString() {
        String platformsStr = String.join(", ", platforms);
        return name + "," + version + "," + platformsStr + "\n";
    }
}
