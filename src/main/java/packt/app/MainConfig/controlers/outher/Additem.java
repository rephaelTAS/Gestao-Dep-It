package packt.app.MainConfig.controlers.outher;

import javafx.scene.control.Button;
import packt.app.MainConfig.notificacao.Notificacao;
import packt.app.ViewInitializer;
import packt.app.views.FXMLManager;
import packt.app.views.config.ViewConfig;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import java.net.URL;
import java.util.ResourceBundle;

public class Additem implements Initializable {


    // ---- Componentes UI ----
    @FXML
    private Button btn_inventario;
    @FXML
    private Button btn_invent_stock;
    @FXML
    private Button btn_toner;
    @FXML
    private Button btn_tonerStock;
    @FXML
    private Button btn_wifi;
    @FXML
    private Button btn_wifistock;
    @FXML
    private Button btn_dbFuncionarios;
    @FXML
    private StackPane add_multPage;

    // ---- Serviços ----
    private final Notificacao notificacao = new Notificacao();

    // ---- Lifecycle ----
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ViewInitializer.initialize();
        loadDefaultView();
        btn_inventario.setOnAction(event ->handleInventory() );
        btn_invent_stock.setOnAction(event -> handleInventoryStock());
        btn_toner.setOnAction(event -> handleToner());
        btn_tonerStock.setOnAction(event -> handleTonerStock());
        btn_wifi.setOnAction(event -> handleWifi());
        btn_wifistock.setOnAction(event -> handleWifiStock());
        btn_dbFuncionarios.setOnAction(event -> handleEmployee());
    }

    // ---- Métodos de Carregamento ----
    private void loadDefaultView() {
        loadInventoryView();
    }

    private void loadInventoryView() {
        loadView(ViewConfig.AddItem.INVENTARIO);
    }

    private void loadView(String viewId) {
        try {
            Parent view = FXMLManager.loadView(viewId);
            add_multPage.getChildren().setAll(view);
        } catch (Exception e) {
            notificacao.showError("Erro ao carregar a view: " + viewId + "\n" + e.getMessage());
        }
    }

    // ---- Métodos de Ação ----
    public void handleInventory() {
        loadView(ViewConfig.AddItem.INVENTARIO);
    }

    public void handleInventoryStock() {
        loadView(ViewConfig.AddItem.INVENTARIO_STOCK);
    }

    public void handleToner() {
        loadView(ViewConfig.AddItem.TONER);
    }

    public void handleTonerStock() {
        loadView(ViewConfig.AddItem.TONER_STOCK);
    }

    public void handleWifi() {
        loadView(ViewConfig.AddItem.WIFI);
    }

    public void handleWifiStock() {
        loadView(ViewConfig.AddItem.WIFI_STOCK);
    }

    public void handleEmployee() {
        loadView(ViewConfig.AddItem.FUNCIONARIO);
    }
}