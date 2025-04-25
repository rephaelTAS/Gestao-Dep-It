package packt.app.views;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class FXMLRegistry {
    private static final Logger LOGGER = Logger.getLogger(FXMLRegistry.class.getName());
    private static final FXMLRegistry INSTANCE = new FXMLRegistry();
    private final Map<String, String> registry = new HashMap<>();

    private FXMLRegistry() {
        // Private constructor for singleton
        LOGGER.config("FXMLRegistry instance created");
    }

    public static FXMLRegistry getInstance() {
        return INSTANCE;
    }

    /**
     * Registers a new view with its FXML path
     * @param viewId The unique identifier for the view
     * @param fxmlPath The path to the FXML file
     * @throws IllegalStateException if the viewId is already registered
     * @throws IllegalArgumentException if any parameter is null or empty
     */
    public void registerView(String viewId, String fxmlPath) {
        if (viewId == null || viewId.trim().isEmpty()) {
            throw new IllegalArgumentException("View ID cannot be null or empty");
        }
        if (fxmlPath == null || fxmlPath.trim().isEmpty()) {
            throw new IllegalArgumentException("FXML path cannot be null or empty");
        }

        if (registry.containsKey(viewId)) {
            String errorMsg = "Duplicate View ID: " + viewId;
            LOGGER.severe(errorMsg);
            throw new IllegalStateException(errorMsg);
        }

        LOGGER.log(Level.CONFIG, "Registering view: {0} -> {1}", new Object[]{viewId, fxmlPath});
        registry.put(viewId, fxmlPath);
    }

    /**
     * Gets the FXML path for a registered view
     * @param viewId The view identifier
     * @return The FXML file path
     * @throws IllegalArgumentException if the view is not registered
     */
    public String getFxmlPath(String viewId) {
        if (!registry.containsKey(viewId)) {
            String errorMsg = "View not registered: " + viewId +
                    "\nAvailable views: " + getRegisteredViews();
            LOGGER.warning(errorMsg);
            throw new IllegalArgumentException(errorMsg);
        }
        return registry.get(viewId);
    }

    /**
     * Checks if a view is registered
     * @param viewId The view identifier to check
     * @return true if the view is registered, false otherwise
     */
    public boolean isRegistered(String viewId) {
        boolean registered = registry.containsKey(viewId);
        if (!registered) {
            LOGGER.fine("View not found: " + viewId);
        }
        return registered;
    }

    /**
     * Gets an unmodifiable set of all registered view IDs
     * @return Set of registered view IDs
     */
    public Set<String> getRegisteredViews() {
        return Collections.unmodifiableSet(registry.keySet());
    }

    /**
     * Clears all registered views (primarily for testing)
     */
    public void clearRegistry() {
        LOGGER.info("Clearing all registered views");
        registry.clear();
    }
}