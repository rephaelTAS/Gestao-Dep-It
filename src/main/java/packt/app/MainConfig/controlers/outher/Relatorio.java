package packt.app.MainConfig.controlers.outher;

import packt.app.MainConfig.controlers.main.MainControler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import packt.app.MainConfig.notificacao.Notificacao;
import packt.app.views.FXMLManager;
import packt.app.views.config.ViewConfig;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Relatorio {

    @FXML
    private Button btn_inventario;

    @FXML
    private Button btn_historicoUtilizacao;

    @FXML
    private Button btn_inventarioStock;

    @FXML
    private Button btn_comDefeitos;


    @FXML
    private Button btn_centroResidos;

    @FXML
    private Button btn_wifi;

    @FXML
    private Button btn_wifiStock;

    @FXML
    private Button btn_usuToner;

    @FXML
    private Button btn_tonerStock;

    @FXML
    private StackPane stackPane_relatorio;

    Notificacao notificacao = new Notificacao();

    @FXML
    private void initialize() {
        loadInitialView();
        // Adiciona eventos aos botões
        btn_inventario.setOnAction(event -> handleInventario());
        btn_historicoUtilizacao.setOnAction(event -> handleHistoricoUtilizacao());
        btn_inventarioStock.setOnAction(event -> handleInventarioStock());
        btn_comDefeitos.setOnAction(event -> handleComDefeitos());
        btn_centroResidos.setOnAction(event -> handleCentroResidos());
        btn_wifi.setOnAction(event -> handleWifi());
        btn_wifiStock.setOnAction(event -> handleWifiStock());
        btn_usuToner.setOnAction(event -> handleUsuToner());
        btn_tonerStock.setOnAction(event -> handleTonerStock());
    }

    private void loadInitialView() {
        loadView(ViewConfig.Relatorio.INVENTARIO);
    }

    private void loadView(String viewId) {
        try {
            Parent view = FXMLManager.loadView(viewId);
            stackPane_relatorio.getChildren().setAll(view);
        } catch (Exception e) {
            notificacao.showError("Erro ao carregar a view: " + viewId + "\n" + e.getMessage());
        }
    }

    private void handleTonerStock() {
        loadView(ViewConfig.Relatorio.TONER_STOCK);
    }

    private void handleUsuToner() {
        loadView(ViewConfig.Relatorio.USU_TONER);
    }

    private void handleWifiStock() {
        loadView(ViewConfig.Relatorio.WIFI_STOCK);
    }

    private void handleWifi() {
        loadView(ViewConfig.Relatorio.WIFI);
    }

    private void handleCentroResidos() {
        loadView(ViewConfig.Relatorio.CENTRO_RESIDOS);
    }

    private void handleInventario() {
        loadView(ViewConfig.Relatorio.INVENTARIO);
    }


    private void handleInventarioStock() {
        loadView(ViewConfig.Relatorio.INVENTARIO_STOCK);
    }

    private void handleComDefeitos() {
        loadView(ViewConfig.Relatorio.COM_DEFEITOS);
    }


    private void handleHistoricoUtilizacao(){
        loadView(ViewConfig.Relatorio.HISTORICO_UTILIZACAO);
    }

}
