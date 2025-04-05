package app.styles.config;
import app.styles.StyleManager;

public class StyleConfig {

    // Definição dos caminhos para os arquivos CSS
    private static final String LOGIN_CSS = "/css/modules/auth/login.css";
    private static final String MAIN_CSS = "/css/core/main.css";
    private static final String ADDITEM_CSS = "/css/core/additem.css";  // Novo CSS para Additem
    private static final String RELATORIO_CSS = "/css/core/relatorio.css";  // Novo CSS para Relatorio
    private static final String VISUALIZAR_CSS = "/css/core/visualizar.css";

    // Relatórios CSS
    private static final String RELATORIO_comDefeitos_CSS = "/css/core/relatorio/comDefeitos.css";
    private static final String RELATORIO_INVENTARIO_CSS = "/css/core/relatorio/inventario.css";
    private static final String RELATORIO_HistoriUtili_CSS = "/css/core/relatorio/historicoUtili.css";
    private static final String RELATORIO_INVENTARIO_STOCK_CSS = "/css/core/relatorio/inventarioStock.css";
    private static final String RELATORIO_TONER_STOCK_CSS = "/css/core/relatorio/tonerStock.css";
    private static final String RELATORIO_USU_TONER_CSS = "/css/core/relatorio/usuToner.css";
    private static final String RELATORIO_WIFI_CSS = "/css/core/relatorio/wifi.css";
    private static final String RELATORIO_WIFI_STOCK_CSS = "/css/core/relatorio/wifiStock.css";

    public static void init() {
        StyleManager styleManager = StyleManager.getInstance();

        // Registra os estilos da aplicação
        styleManager.registerStyle("login", LOGIN_CSS);
        styleManager.registerStyle("main", MAIN_CSS);
        styleManager.registerStyle("additem", ADDITEM_CSS);  // Registro do novo CSS para Additem
        styleManager.registerStyle("relatorio", RELATORIO_CSS);  // Registro do novo CSS para Relatorio
        styleManager.registerStyle("visualizar", VISUALIZAR_CSS);

        // Registra os estilos de relatórios
        styleManager.registerStyle("relatorioComDefeitos", RELATORIO_comDefeitos_CSS);
        styleManager.registerStyle("relatorioInventario", RELATORIO_INVENTARIO_CSS);
        styleManager.registerStyle("relatorioHistoricoUtili", RELATORIO_HistoriUtili_CSS);
        styleManager.registerStyle("relatorioInventarioStock", RELATORIO_INVENTARIO_STOCK_CSS);
        styleManager.registerStyle("relatorioTonerStock", RELATORIO_TONER_STOCK_CSS);
        styleManager.registerStyle("relatorioUsuToner", RELATORIO_USU_TONER_CSS);
        styleManager.registerStyle("relatorioWifi", RELATORIO_WIFI_CSS);
        styleManager.registerStyle("relatorioWifiStock", RELATORIO_WIFI_STOCK_CSS);

        // Adiciona temas
        styleManager.addTheme("light");
        styleManager.addTheme("dark");
    }
}
