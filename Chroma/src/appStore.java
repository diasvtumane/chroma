import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class appStore {
    private List<App> appList;


    public appStore() {
        this.appList = new ArrayList<>();
    }

    public void addApp(App app) {
        this.appList.add(app);
    }

    public void removeApp(App app) {
        this.appList.remove(app);
    }

    
    
}