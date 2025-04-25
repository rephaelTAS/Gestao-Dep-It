package packt.app.styles.config;

import packt.app.styles.StyleManager;

/**
 * Configuração centralizada de todos os estilos CSS da aplicação
 * Organizado por módulos e componentes
 */
public final class StyleConfig {

    // ==================== MÓDULO PRINCIPAL ====================
    public static final class Main {
        public static final String DASHBOARD = "dashboard";
        public static final String LOGIN = "login-form";
        public static final String NAVIGATION = "main-navigation";
    }

    // ==================== MÓDULO ADD ITEM ====================
    public static final class AddItem {
        public static final String BASE = "additem-base";
        public static final String INVENTARIO = "additem-inventario";
        public static final String TONER = "additem-toner";
        public static final String WIFI = "additem-wifi";
    }

    // ==================== MÓDULO RELATÓRIOS ====================
    public static final class Relatorio {
        public static final String BASE = "relatorio-base";
        public static final String INVENTARIO = "relatorio-inventario";
        public static final String TONER = "relatorio-toner";
        public static final String WIFI = "relatorio-wifi";
        public static final String TABLE = "relatorio-table";
    }

    // ==================== MÓDULO VISUALIZAR ====================
    public static final class Visualizar {
        public static final String BASE = "visualizar-base";
        public static final String DETAILS = "visualizar-details";
        public static final String TABLE = "visualizar-table";
    }

    /**
     * Registra todos os estilos da aplicação
     */
    public static void init() {
        StyleManager styleManager = StyleManager.getInstance();

        // Módulo Principal
        styleManager.registerStyle(Main.DASHBOARD, "/packt/styles/main/dashboard.css");
        styleManager.registerStyle(Main.LOGIN, "/packt/css/modules/auth/login.css");

    }
}