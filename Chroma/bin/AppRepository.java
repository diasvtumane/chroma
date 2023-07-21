import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AppRepository {
    private List<App> apps;

    public AppRepository() {
        this.apps = new ArrayList<>();
    }

    // Add a new app to the repository
    public void addApp(App app) {
        apps.add(app);
    }

    // Search apps by name and return a list of matching apps
    public List<App> searchAppsByName(String searchName) {
        List<App> matchingApps = new ArrayList<>();
        for (App app : apps) {
            if (app.getName().equalsIgnoreCase(searchName)) {
                matchingApps.add(app);
            }
        }
        return matchingApps;
    }

    public void sortApps(Comparator<App> comparator) {
        apps.sort(comparator);
    }


    // Getters and setters
    public List<App> getApps() {
        return apps;
    }

    public void setApps(List<App> apps) {
        this.apps = apps;
    }

    public List<App> getLeaderboard() {
        List<App> leaderboard = apps;
        leaderboard.sort(Comparator.comparingInt(App::getLikes).reversed());
        return leaderboard;
    }
}
