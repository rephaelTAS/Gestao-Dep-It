package packt.app.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import packt.app.styles.StyleManager;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Gerenciador centralizado para carregamento e cache de views FXML.
 * Implementa padrão Singleton para garantir uma única instância.
 */
public final class FXMLManager {
    private static final Logger LOGGER = Logger.getLogger(FXMLManager.class.getName());

    private static final FXMLRegistry registry = FXMLRegistry.getInstance();
    private static final Map<String, Parent> viewCache = new ConcurrentHashMap<>();
    private static final Map<String, Object> controllerCache = new ConcurrentHashMap<>();
    private static final Map<String, FXMLLoader> loaderCache = new ConcurrentHashMap<>();

    private FXMLManager() {
        throw new AssertionError("Classe utilitária não deve ser instanciada");
    }

    /**
     * Carrega uma view FXML a partir de seu ID registrado.
     *
     * @param viewId ID da view registrada no FXMLRegistry
     * @return Parent contendo a hierarquia de nós carregada
     * @throws IOException se ocorrer erro ao carregar o arquivo FXML
     * @throws IllegalArgumentException se o viewId não estiver registrado
     */
    public static Parent loadView(String viewId) throws IOException {
        validateViewId(viewId);

        return viewCache.computeIfAbsent(viewId, id -> {
            try {
                FXMLLoader loader = createLoader(id);
                Parent view = loader.load();

                // Armazena controller e loader
                controllerCache.put(id, loader.getController());
                loaderCache.put(id, loader);

                // Aplica estilos
                StyleManager.getInstance().applyComponentStyles(view, id);

                LOGGER.log(Level.FINE, "View carregada com sucesso: {0}", id);
                return view;
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Falha ao carregar view: " + id, e);
                throw new RuntimeException("Falha ao carregar view: " + id, e);
            }
        });
    }

    /**
     * Obtém o controller associado a uma view.
     *
     * @param <T> Tipo do controller
     * @param viewId ID da view registrada
     * @return Instância do controller
     * @throws IllegalStateException se o controller não estiver disponível
     */
    @SuppressWarnings("unchecked")
    public static <T> T getController(String viewId) {
        if (!controllerCache.containsKey(viewId)) {
            throw new IllegalStateException("Controller não disponível: " + viewId);
        }
        return (T) controllerCache.get(viewId);
    }

    /**
     * Obtém o FXMLLoader usado para carregar uma view.
     *
     * @param viewId ID da view registrada
     * @return Instância do FXMLLoader
     * @throws IllegalStateException se o loader não estiver disponível
     */
    public static FXMLLoader getLoader(String viewId) {
        if (!loaderCache.containsKey(viewId)) {
            throw new IllegalStateException("FXMLLoader não disponível: " + viewId);
        }
        return loaderCache.get(viewId);
    }

    /**
     * Limpa o cache de views, controllers e loaders.
     */
    public static void clearCache() {
        viewCache.clear();
        controllerCache.clear();
        loaderCache.clear();
        LOGGER.log(Level.INFO, "Cache de views limpo");
    }

    /**
     * Remove uma view específica e seus recursos associados do cache.
     *
     * @param viewId ID da view a ser removida do cache
     */
    public static void removeFromCache(String viewId) {
        viewCache.remove(viewId);
        controllerCache.remove(viewId);
        loaderCache.remove(viewId);
        LOGGER.log(Level.FINE, "View removida do cache: {0}", viewId);
    }

    private static FXMLLoader createLoader(String viewId) {
        String fxmlPath = registry.getFxmlPath(viewId);
        if (fxmlPath == null) {
            throw new IllegalArgumentException("Caminho FXML não encontrado para: " + viewId);
        }
        return new FXMLLoader(FXMLManager.class.getResource(fxmlPath));
    }

    private static void validateViewId(String viewId) {
        if (!registry.isRegistered(viewId)) {
            String errorMsg = "View ID não registrado: " + viewId;
            LOGGER.log(Level.SEVERE, errorMsg);
            throw new IllegalArgumentException(errorMsg);
        }
    }
}