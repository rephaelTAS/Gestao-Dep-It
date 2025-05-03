package packt.app;

import packt.app.styles.config.StyleConfig;
import packt.app.views.config.ViewConfig;
import packt.app.views.FXMLRegistry;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class ViewInitializer {
    private static final Logger LOGGER = Logger.getLogger(ViewInitializer.class.getName());
    private static boolean initialized = false;

    // Construtor privado para evitar instância
    private ViewInitializer() {
        throw new AssertionError("Classe utilitária não deve ser instanciada");
    }

    /**
     * Inicializa o sistema de views do aplicativo.
     * Segue a sequência: estilos → views → validação.
     *
     * @throws RuntimeException se ocorrer falha na inicialização
     */
    public static synchronized void initialize() {
        if (initialized) {
            LOGGER.warning("Tentativa de inicialização duplicada detectada");
            return;
        }

        try {
            LOGGER.info("Iniciando inicialização do sistema de views...");

            // 1. Configuração de estilos
            StyleConfig.init();
            LOGGER.fine("Configuração de estilos inicializada");

            // 2. Configuração de views
            ViewConfig.initialize();
            LOGGER.fine("Configuração de views inicializada");

            // 3. Validação crítica
            validateCriticalViews();
            LOGGER.fine("Validação de views críticas concluída");

            initialized = true;
            LOGGER.info("Sistema de views inicializado com sucesso");

        } catch (Exception e) {
            String errorMsg = "Falha na inicialização do sistema de views: " + e.getMessage();
            LOGGER.log(Level.SEVERE, errorMsg, e);
            throw new RuntimeException(errorMsg, e);
        }
    }

    /**
     * Valida a presença de views críticas no registro.
     *
     * @throws IllegalStateException se alguma view crítica não estiver registrada
     */
    private static void validateCriticalViews() {
        String[] criticalViews = {
                ViewConfig.Main.LOGIN,
                ViewConfig.Main.MAIN
        };

        FXMLRegistry registry = FXMLRegistry.getInstance();

        for (String viewId : criticalViews) {
            if (!registry.isRegistered(viewId)) {
                String errorMsg = "View crítica não registrada: " + viewId;
                LOGGER.severe(errorMsg);
                throw new IllegalStateException(errorMsg);
            }
            LOGGER.fine("View crítica validada: " + viewId);
        }
    }

    /**
     * Verifica se o sistema de views foi inicializado.
     *
     * @return true se o sistema foi inicializado, false caso contrário
     */
    public static boolean isInitialized() {
        return initialized;
    }
}