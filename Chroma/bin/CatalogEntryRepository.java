package data;

import java.util.ArrayList;
import java.util.List;

public class CatalogEntryRepository {
    private List<CatalogEntry> catalogEntries;

    public CatalogEntryRepository() {
        this.catalogEntries = new ArrayList<>();
    }

    public void addEntry(CatalogEntry entry) {
        catalogEntries.add(entry);
    }

    public List<CatalogEntry> getAllEntries() {
        return catalogEntries;
    }

    // Other methods for data retrieval and manipulation
    // ...
}