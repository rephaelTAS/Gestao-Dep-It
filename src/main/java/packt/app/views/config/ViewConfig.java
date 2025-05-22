package packt.app.views.config;

import kotlin.internal.ProgressionUtilKt;
import packt.app.views.FXMLRegistry;

/**
 * Configuração centralizada de todas as views da aplicação
 */
public final class ViewConfig {


    public static class Main {
        public static final String DASHBOARD = "dashboard";
        public static final String ADD_ITEM = "add_item";
        public static final String RELATORIO = "relatorio";
        public static final String VISUALIZAR = "visualizar";
        public static final String LOGIN = "login_form";
        public static final String MAIN = "main";
        public static final String DEFINICOES = "definicoes";
        public static final String SOBRE = "sobre";
        public static final String TIME_REPORT = "time" ;
    }


    // Categorias de views (módulo AddItem)
    public static final class AddItem {
        public static final String INVENTARIO = "additem_inventario";
        public static final String INVENTARIO_STOCK = "additem_inventario_stock";
        public static final String TONER = "additem_toner";
        public static final String TONER_STOCK = "additem_toner_stock";
        public static final String WIFI = "additem_wifi";
        public static final String WIFI_STOCK = "additem_wifi_stock";
        public static final String FUNCIONARIO = "additem_funcionario";
        public static final String NEW_PRODUCT = "novo_produto";
    }


    public static final class Visualizar {
        public static final String INVENTARIO = "visualizar_inventario";
        public static final String HISTORICO_UTILIZACAO = "visualizar_historico_utilizacao";
        public static final String INVENTARIO_STOCK = "visualizar_inventario_stock";
        public static final String COM_DEFEITOS = "visualizar_com_defeitos";
        public static final String CENTRO_RESIDOS = "visualizar_centro_residos";
        public static final String WIFI = "visualizar_wifi";
        public static final String WIFI_STOCK = "visualizar_wifi_stock";
        public static final String USU_TONER = "visualizar_usu_toner";
        public static final String TONER_STOCK = "visualizar_toner_stock";
    }

    // ==================== MÓDULO RELATÓRIO ====================
    public static final class Relatorio {
        public static final String INVENTARIO = "relatorio_inventario";
        public static final String HISTORICO_UTILIZACAO = "relatorio_historico_utilizacao";
        public static final String INVENTARIO_STOCK = "relatorio_inventario_stock";
        public static final String COM_DEFEITOS = "relatorio_com_defeitos";
        public static final String CENTRO_RESIDOS = "relatorio_centro_residos";
        public static final String WIFI = "relatorio_wifi";
        public static final String WIFI_STOCK = "relatorio_wifi_stock";
        public static final String USU_TONER = "relatorio_usu_toner";
        public static final String TONER_STOCK = "relatorio_toner_stock";
    }

    public static final class Editar{
        public static final String INVENTARIO = "editar_inventario";
        public static final String INVENTARIOSTOCK = "editar_inventarioStock";
    }


    public static void initialize() {
        FXMLRegistry registry = FXMLRegistry.getInstance();

        //Main
        registry.registerView(Main.DASHBOARD, "/packt/templates/main/dashboard.fxml");
        registry.registerView(Main.ADD_ITEM, "/packt/templates/main/additem.fxml");
        registry.registerView(Main.RELATORIO, "/packt/templates/main/relatorio.fxml");
        registry.registerView(Main.VISUALIZAR, "/packt/templates/main/visualizar.fxml");
        registry.registerView(Main.LOGIN, "/packt/templates/login/login.fxml");
        registry.registerView(Main.MAIN, "/packt/templates/main/main.fxml");
        registry.registerView(Main.DEFINICOES, "/packt/templates/main/definicoes.fxml");
        registry.registerView(Main.SOBRE, "/packt/templates/main/sobre.fxml");
        registry.registerView(Main.TIME_REPORT, "/packt/templates/main/TimeReport.fxml");

        // Registro das views do módulo AddItem
        registry.registerView(AddItem.INVENTARIO, "/packt/templates/outher/addItem/inventario.fxml");
        registry.registerView(AddItem.INVENTARIO_STOCK, "/packt/templates/outher/addItem/inventario_stock.fxml");
        registry.registerView(AddItem.TONER, "/packt/templates/outher/addItem/add_toner.fxml");
        registry.registerView(AddItem.TONER_STOCK, "/packt/templates/outher/addItem/toner_stock.fxml");
        registry.registerView(AddItem.WIFI, "/packt/templates/outher/addItem/wifi.fxml");
        registry.registerView(AddItem.WIFI_STOCK, "/packt/templates/outher/addItem/wifi_stock.fxml");
        registry.registerView(AddItem.FUNCIONARIO, "/packt/templates/outher/addItem/add_funcionario.fxml");
        registry.registerView(AddItem.NEW_PRODUCT, "/packt/templates/outher/addItem/criar_produtos.fxml");

        // Visualizar
        registry.registerView(Visualizar.INVENTARIO, "/packt/templates/outher/visualizar/vis_inventario.fxml");
        registry.registerView(Visualizar.HISTORICO_UTILIZACAO, "/packt/templates/outher/visualizar/vis_historicoUtilizacao.fxml");
        registry.registerView(Visualizar.INVENTARIO_STOCK, "/packt/templates/outher/visualizar/vis_inventarioStock.fxml");
        registry.registerView(Visualizar.COM_DEFEITOS, "/packt/templates/outher/visualizar/vis_centroResido.fxml");
        registry.registerView(Visualizar.CENTRO_RESIDOS, "/packt/templates/outher/visualizar/vis_comDefeitos.fxml");
        registry.registerView(Visualizar.WIFI, "/packt/templates/outher/visualizar/vis_wifi.fxml");
        registry.registerView(Visualizar.WIFI_STOCK, "/packt/templates/outher/visualizar/vis_wifiStock.fxml");
        registry.registerView(Visualizar.USU_TONER, "/packt/templates/outher/visualizar/vis_usuToner.fxml");
        registry.registerView(Visualizar.TONER_STOCK, "/packt/templates/outher/visualizar/vis_tonerStock.fxml");

        //Relatorios
        registry.registerView(Relatorio.INVENTARIO, "/packt/templates/outher/relatorio/rela_inventario.fxml");
        registry.registerView(Relatorio.HISTORICO_UTILIZACAO, "/packt/templates/outher/relatorio/rela_historicoUtilizcao.fxml");
        registry.registerView(Relatorio.INVENTARIO_STOCK, "/packt/templates/outher/relatorio/rela_inventarioStock.fxml");
        registry.registerView(Relatorio.COM_DEFEITOS, "/packt/templates/outher/relatorio/rela_comDefeitos.fxml");
        registry.registerView(Relatorio.CENTRO_RESIDOS, "/packt/templates/outher/relatorio/rela_centroResido.fxml");
        registry.registerView(Relatorio.WIFI, "/packt/templates/outher/relatorio/rela_wifi.fxml");
        registry.registerView(Relatorio.WIFI_STOCK, "/packt/templates/outher/relatorio/rela_wifiStock.fxml");
        registry.registerView(Relatorio.USU_TONER, "/packt/templates/outher/relatorio/rela_usuToner.fxml");
        registry.registerView(Relatorio.TONER_STOCK, "/packt/templates/outher/relatorio/rela_tonerStock.fxml");

        // Editar
        registry.registerView(Editar.INVENTARIO, "/packt/templates/outher/editar/Editar_Inventario.fxml");
        registry.registerView(Editar.INVENTARIOSTOCK, "/packt/templates/outher/editar/Editar_inventarioStock.fxml");
    }
}
