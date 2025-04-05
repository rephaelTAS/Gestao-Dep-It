package util;

import app.styles.StyleManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import notificacao.Notificacao;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FXMLCacheLoader {

    private static final Notificacao notificacao = new Notificacao();
    private static final Map<String, Parent> cache = new HashMap<>();

    public static void loadView(String fxmlPath, StackPane multiploPanel, String... componentNames) {
        try {
            Parent fxml = load(fxmlPath, componentNames);
            multiploPanel.getChildren().setAll(fxml);
        } catch (RuntimeException e) {
            Logger.getLogger(FXMLLoader.class.getName()).log(Level.SEVERE, null, e);
            notificacao.showError("Erro ao carregar a visualização.");
        }
    }

    public static Parent load(String fxmlPath, String... componentNames) {
        if (cache.containsKey(fxmlPath)) {
            Parent cached = cache.get(fxmlPath);
            cached.setVisible(true);
            return cached;
        }

        try {
            FXMLLoader loader = new FXMLLoader(FXMLCacheLoader.class.getResource(fxmlPath));
            Parent root = loader.load();
            root.setVisible(true);

            // Aplica o estilo ao carregar
            StyleManager.getInstance().applyComponentStyles(root, componentNames);

            cache.put(fxmlPath, root);
            return root;
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar FXML: " + fxmlPath, e);
        }
    }

    public static void clearCache() {
        cache.clear();
    }

    public static void remove(String fxmlPath) {
        cache.remove(fxmlPath);
    }
}
