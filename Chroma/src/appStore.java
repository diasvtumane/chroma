import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The appStore class maintains a list of App.
 */
public class appStore {
    // A list of apps maintained by the appStore
    private List<App> appList;

    /**
     * Constructor
     */
    public appStore() {
        this.appList = new ArrayList<>();
    }

    /**
     * Returns the list of apps.
     * @return A list of App objects.
     */
    public List<App> getAppList() {
        return this.appList;
    }

    /**
     * Sets a new list for apps.
     * @param appList A list of new App objects.
     */
    public void setAppList(List<App> appList) {
        this.appList = appList;
    }

    /**
     * Adds a new app to the list.
     * @param app An App object to be added to the list.
     */
    public void addApp(App app) {
        this.appList.add(app);
    }

    /**
     * Removes an app from the list.
     * @param app An App object to be removed from the list.
     */
    public void removeApp(App app) {
        this.appList.remove(app);
    }
}