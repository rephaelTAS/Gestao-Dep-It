package app.styles;

import javafx.scene.Parent;
import java.util.*;

/**
 * Sistema centralizado de gerenciamento de app.estilos
 */
public class StyleManager {
    private static StyleManager instance;
    private final Map<String, String> styleRegistry = new HashMap<>();
    private final List<String> activeThemes = new ArrayList<>();
    private String currentTheme = "light";

    private StyleManager() {
        // Privado para singleton
    }

    public static synchronized StyleManager getInstance() {
        if (instance == null) {
            instance = new StyleManager();
        }
        return instance;
    }

    public void registerStyle(String componentName, String cssPath) {
        styleRegistry.put(componentName, cssPath);
    }

    public void applyComponentStyles(Parent root, String... componentNames) {
        root.getStylesheets().clear();
        applyTheme(root); // Aplica o tema atual primeiro

        for (String name : componentNames) {
            String path = styleRegistry.get(name);
            if (path != null) {
                CSSLoader.loadCSS(root, path);
            }
        }
    }

    public void switchTheme(Parent root, String themeName) {
        this.currentTheme = themeName;
        applyTheme(root);
    }

    private void applyTheme(Parent root) {
        CSSLoader.loadCSS(root, "/css/themes/" + currentTheme + "-theme.css");
    }

    public void addTheme(String themeName) {
        if (!activeThemes.contains(themeName)) {
            activeThemes.add(themeName);
        }
    }

    public List<String> getAvailableThemes() {
        return Collections.unmodifiableList(activeThemes);
    }
}