package com.example.chroma;

import java.util.List;

public class AppRequest {
    private String name;
    private String version;
    private List<String> platforms;

    // Constructor
    public AppRequest(String name, String version, List<String> platforms) {
        this.name = name;
        this.version = version;
        this.platforms = platforms;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<String> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<String> platforms) {
        this.platforms = platforms;
    }

    // Override toString method for debugging or display purposes
    @Override
    public String toString() {
        return name + " " + version + " " + platforms;
    }
}
