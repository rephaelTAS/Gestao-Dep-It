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
 * Gerenciador avançado para carregamento de views FXML com suporte a:
 * - Cache inteligente de views
 * - Controle de ciclo de vida
 * - Integração com registro de views
 * - Aplicação automática de estilos
 */
public final class FXMLManager {
    private static final Logger LOGGER = Logger.getLogger(FXMLManager.class.getName());
    private static final FXMLRegistry registry = FXMLRegistry.getInstance();

    // Caches separados para diferentes finalidades
    private static final Map<String, FXMLLoader> loaderCache = new ConcurrentHashMap<>();
    private static final Map<String, Parent> viewCache = new ConcurrentHashMap<>();
    private static final Map<String, Object> controllerCache = new ConcurrentHashMap<>();

    private FXMLManager() {
        throw new AssertionError("Classe utilitária não deve ser instanciada");
    }

    /**
     * Carrega uma nova instância da view (sem cache)
     */
    public static Parent loadFreshView(String viewId) throws IOException {
        validateViewId(viewId);
        FXMLLoader loader = createNewLoader(viewId);
        Parent view = loader.load();
        cacheResources(viewId, loader, view);
        applyStyles(view, viewId);
        return view;
    }

    /**
     * Carrega view com cache (para uso em cenários estáticos)
     */
    public static Parent loadView(String viewId) throws IOException {
        validateViewId(viewId);
        return viewCache.computeIfAbsent(viewId, id -> {
            try {
                FXMLLoader loader = createNewLoader(id);
                Parent view = loader.load();
                cacheResources(id, loader, view);
                applyStyles(view, id);
                return view;
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Falha ao carregar view: " + id, e);
                throw new RuntimeException("Falha ao carregar view: " + id, e);
            }
        });
    }

    /**
     * Obtém o controller associado (para views cacheadas)
     */
    @SuppressWarnings("unchecked")
    public static <T> T getController(String viewId) {
        if (!controllerCache.containsKey(viewId)) {
            throw new IllegalStateException("Controller não carregado para: " + viewId);
        }
        return (T) controllerCache.get(viewId);
    }

    /**
     * Cria um novo loader para a view (sem cache)
     */
    public static FXMLLoader createNewLoader(String viewId) {
        String fxmlPath = getViewPath(viewId);
        return new FXMLLoader(FXMLManager.class.getResource(fxmlPath));
    }

    public static String getViewPath(String viewId) {
        return registry.getFxmlPath(viewId);
    }

    /**
     * Limpa todos os caches
     */
    public static void clearAllCaches() {
        loaderCache.clear();
        viewCache.clear();
        controllerCache.clear();
        LOGGER.info("Todos os caches foram limpos");
    }

    /**
     * Remove uma view específica dos caches
     */
    public static void removeFromCache(String viewId) {
        loaderCache.remove(viewId);
        viewCache.remove(viewId);
        controllerCache.remove(viewId);
        LOGGER.log(Level.FINE, "Recursos removidos do cache: {0}", viewId);
    }

    private static void cacheResources(String viewId, FXMLLoader loader, Parent view) {
        controllerCache.put(viewId, loader.getController());
        loaderCache.put(viewId, loader);
    }

    private static void applyStyles(Parent view, String viewId) {
        StyleManager.getInstance().applyComponentStyles(view, viewId);
    }

    private static void validateViewId(String viewId) {
        if (!registry.isRegistered(viewId)) {
            String errorMsg = "View ID não registrado: " + viewId;
            LOGGER.log(Level.SEVERE, errorMsg);
            throw new IllegalArgumentException(errorMsg);
        }
    }
}