package packt.app.MainConfig.controlers.main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import packt.app.MainConfig.notificacao.Notificacao;
import packt.app.views.FXMLManager;
import packt.app.views.config.ViewConfig;

import java.net.URL;
import java.util.ResourceBundle;

public class Visualizar implements Initializable {
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
    private StackPane multpage;

    Notificacao notificacao = new Notificacao();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadInitialView();
        // Adiciona eventos aos botões
        btn_inventario.setOnAction(event -> inventario());
        btn_historicoUtilizacao.setOnAction(event -> historicoUtilizacao());
        btn_inventarioStock.setOnAction(event -> handleInventarioStock());
        btn_comDefeitos.setOnAction(event -> handleComDefeitos());
        btn_centroResidos.setOnAction(event -> handleCentroResidos());
        btn_wifi.setOnAction(event -> handleWifi());
        btn_wifiStock.setOnAction(event -> handleWifiStock());
        btn_usuToner.setOnAction(event -> handleUsuToner());
        btn_tonerStock.setOnAction(event -> handleTonerStock());
    }

    private void loadInitialView(){
        loadView(ViewConfig.Visualizar.INVENTARIO);
    }

    private void loadView(String viewId) {
        try {
            Parent view = FXMLManager.loadStaticView(viewId);
            multpage.getChildren().setAll(view);
        } catch (Exception e) {
            notificacao.showError("Erro ao carregar a view: " + viewId + "\n" + e.getMessage());
        }
    }


    public void inventario(){
        loadView(ViewConfig.Visualizar.INVENTARIO);

    }

    public void historicoUtilizacao(){
        loadView(ViewConfig.Visualizar.HISTORICO_UTILIZACAO);

    }


    private void handleWifiStock() {
        loadView(ViewConfig.Visualizar.WIFI_STOCK);
    }

    private void handleWifi() {
        loadView(ViewConfig.Visualizar.WIFI);
    }

    private void handleCentroResidos() {
        loadView(ViewConfig.Visualizar.CENTRO_RESIDOS);
    }


    private void handleInventarioStock() {
        loadView(ViewConfig.Visualizar.INVENTARIO_STOCK);
    }

    private void handleComDefeitos() {
        loadView(ViewConfig.Visualizar.COM_DEFEITOS);
        // Lógica para o botão "Com Defeitos"
    }



    private void handleTonerStock() {
        loadView(ViewConfig.Visualizar.TONER_STOCK);
    }

    private void handleUsuToner() {
        loadView(ViewConfig.Visualizar.USU_TONER);
    }
}
