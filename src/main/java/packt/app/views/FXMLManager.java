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
 * Gerenciador avançado de recursos FXML com:
 * - Controle preciso de instâncias
 * - Prevenção de duplicação
 * - Cache inteligente
 * - Gerenciamento de ciclo de vida
 */
public final class FXMLManager {
    private static final Logger LOGGER = Logger.getLogger(FXMLManager.class.getName());
    private static final FXMLRegistry registry = FXMLRegistry.getInstance();

    // Estruturas thread-safe para armazenamento
    private static final Map<String, FXMLLoader> activeLoaders = new ConcurrentHashMap<>();
    private static final Map<String, Parent> staticViewsCache = new ConcurrentHashMap<>();
    private static final Map<String, Object> controllersCache = new ConcurrentHashMap<>();

    private FXMLManager() {
        throw new AssertionError("Classe utilitária não deve ser instanciada");
    }

    /**
     * Carrega uma nova instância única da view (para diálogos modais)
     */
    public static Parent loadUniqueView(String viewId) throws IOException {
        validateViewId(viewId);
        FXMLLoader loader = createNewLoader(viewId);
        Parent view = loader.load();
        registerActiveLoader(viewId, loader);
        cacheController(viewId, loader.getController());
        applyStyles(view, viewId);
        return view;
    }

    /**
     * Carrega view estática (com cache controlado)
     */
    public static Parent loadStaticView(String viewId) throws IOException {
        validateViewId(viewId);
        return staticViewsCache.computeIfAbsent(viewId, id -> {
            try {
                FXMLLoader loader = createNewLoader(id);
                Parent view = loader.load();
                cacheController(id, loader.getController());
                applyStyles(view, id);
                return view;
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Falha ao carregar view estática: " + id, e);
                throw new IllegalStateException("Falha ao carregar view: " + id, e);
            }
        });
    }

    /**
     * Obtém controller associado a uma view
     */
    @SuppressWarnings("unchecked")
    public static <T> T getController(String viewId) {
        if (!controllersCache.containsKey(viewId)) {
            throw new IllegalStateException("Controller não disponível para: " + viewId);
        }
        return (T) controllersCache.get(viewId);
    }

    /**
     * Libera recursos de uma view ativa
     */
    public static void releaseView(String viewId) {
        activeLoaders.remove(viewId);
        LOGGER.log(Level.FINE, "View liberada: {0}", viewId);
    }

    /**
     * Limpa todos os caches (para reinicialização)
     */
    public static void purgeAllCaches() {
        staticViewsCache.clear();
        controllersCache.clear();
        activeLoaders.clear();
        LOGGER.info("Todos os caches foram purgados");
    }

    private static FXMLLoader createNewLoader(String viewId) {
        String fxmlPath = registry.getFxmlPath(viewId);
        if (fxmlPath == null) {
            throw new IllegalArgumentException("Caminho FXML não encontrado para: " + viewId);
        }
        return new FXMLLoader(FXMLManager.class.getResource(fxmlPath));
    }

    private static void registerActiveLoader(String viewId, FXMLLoader loader) {
        if (activeLoaders.containsKey(viewId)) {
            throw new IllegalStateException("View já está ativa: " + viewId);
        }
        activeLoaders.put(viewId, loader);
    }

    private static void cacheController(String viewId, Object controller) {
        if (controller != null) {
            controllersCache.put(viewId, controller);
        }
    }

    private static void applyStyles(Parent view, String viewId) {
        try {
            StyleManager.getInstance().applyComponentStyles(view, viewId);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Falha ao aplicar estilos à view: " + viewId, e);
        }
    }

    private static void validateViewId(String viewId) {
        if (viewId == null || viewId.trim().isEmpty()) {
            throw new IllegalArgumentException("View ID não pode ser nulo ou vazio");
        }
        if (!registry.isRegistered(viewId)) {
            throw new IllegalArgumentException("View ID não registrado: " + viewId);
        }
    }

    public static String getViewPath(String viewId) {
        return registry.getFxmlPath(viewId);
    }

    /**
     * Verifica se uma view está ativa (em uso)
     */
    public static boolean isViewActive(String viewId) {
        return activeLoaders.containsKey(viewId);
    }
}