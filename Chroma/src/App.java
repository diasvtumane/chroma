import java.util.List;

/**
 * App is a class that represents an application.
 */
public class App {
    // Name of the application.
    private String name;
    
    // Description of the application.
    private String description;
    
    // List of versions of the application.
    private List<String> versions;
    
    // Platform of the application (like Android, iOS, etc).
    private String platform;

    /**
     * Constructor
     * @param name: name of the application.
     * @param description: description of the application.
     * @param versions: list of versions of the application.
     * @param platform: platform of the application.
     */
    public App(String name, String description, List<String> versions, String platform) {
        this.name = name;
        this.description = description;
        this.versions = versions;
        this.platform = platform;
    }

    /**
     * To get the name of the application.
     * @return The name of the application.
     */
    public String getName() {
        return name;
    }

    /**
     * To set the name of the application.
     * @param newName The new name of the application.
     */
    public void setName(String newName) {
        this.name = newName;
    }
    
    /**
     * To Get the description of the application.
     * @return The description of the application.
     */
    public String getDescription() {
        return description;
    }

    /**
     * To Set the description of the application.
     * @param newDescription The new description of the application.
     */
    public void setDescription(String newDescription) {
        this.description = newDescription;
    }
    
    /**
     * To get list of versions of the application.
     * @return The list of versions of the application.
     */
    public List<String> getVersions() {
        return versions;
    }

    /**
     * To get platform of the application.
     * @return The platform of the application.
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * To get string representation of the application.
     * @return A string representation of the application.
     */
    @Override
    public String toString() {
        return "App Name: " + name + 
               "\nDescription: " + description + 
               "\nVersions: " + versions +
               "\nPlatform: " + platform;
    }
}