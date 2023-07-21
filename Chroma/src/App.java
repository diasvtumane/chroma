public class App {
    private String name;
    private String description;
    private String developer;
    private String version;
    private String platform;
    private String appSize;
    private double rating;
    private int likes;
   

    // Constructor
    public App(String name, String description, String developer, String version, String platform, String appSize) {
        this.name = name;
        this.description = description;
        this.developer = developer;
        this.version = version;
        this.platform = platform;
        this.appSize = appSize;
        this.rating = 0.0;
        this.likes = 0;
    }

    public void updateFeedback(double rating, int likes) {
        this.rating = rating;
        this.likes = likes;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDeveloper() {
        return developer;
    }

    public String getVersion() {
        return version;
    }

    public String getPlatform() {
        return platform;
    }

    public String getAppSize() {
        return appSize;
    }

    public double getRating() {
        return rating;
    }

    public int getLikes() {
        return likes;
    }

    
}