package controler.outher;

import controler.main.MainControler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import notificacao.Notificacao;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Relatorio {

    @FXML
    private Button btn_inventario;

    @FXML
    private Button btn_inventarioStock;

    @FXML
    private Button btn_comDefeitos;

    @FXML
    private Button btn_avariados;

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
        btn_inventarioStock.setOnAction(event -> handleInventarioStock());
        btn_comDefeitos.setOnAction(event -> handleComDefeitos());
        btn_avariados.setOnAction(event -> handleAvariados());
        btn_centroResidos.setOnAction(event -> handleCentroResidos());
        btn_wifi.setOnAction(event -> handleWifi());
        btn_wifiStock.setOnAction(event -> handleWifiStock());
        btn_usuToner.setOnAction(event -> handleUsuToner());
        btn_tonerStock.setOnAction(event -> handleTonerStock());
    }

    private void loadInitialView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/outher/relatorio/rela_inventario.fxml"));
            Parent fxml = loader.load();
            stackPane_relatorio.getChildren().clear();
            stackPane_relatorio.getChildren().add(fxml);
        } catch (IOException e) {
            Logger.getLogger(MainControler.class.getName()).log(Level.SEVERE, null, e);
            notificacao.showErrorAlert("Erro ao carregar a visualização inicial.");
        }
    }

    private void loadView(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent fxml = loader.load();
            stackPane_relatorio.getChildren().clear();
            stackPane_relatorio.getChildren().add(fxml);
        } catch (IOException e) {
            Logger.getLogger(MainControler.class.getName()).log(Level.SEVERE, null, e);
            notificacao.showErrorAlert("Erro ao carregar a visualização.");
        }
    }

    private void handleTonerStock() {
        loadView("/templates/outher/relatorio/rela_tonerStock.fxml");
    }

    private void handleUsuToner() {
        loadView("/templates/outher/relatorio/rela_usuToner.fxml");
    }

    private void handleWifiStock() {
        loadView("/templates/outher/relatorio/rela_wifiStock.fxml");
    }

    private void handleWifi() {
        loadView("/templates/outher/relatorio/rela_wifi.fxml");
    }

    private void handleCentroResidos() {
        loadView("/templates/outher/relatorio/rela_centroResido.fxml");
    }

    private void handleInventario() {
        loadView("/templates/outher/relatorio/rela_inventario.fxml");
    }


    private void handleInventarioStock() {
        loadView("/templates/outher/relatorio/rela_inventarioStock.fxml");
        // Lógica para o botão "Inventário Stock"
    }

    private void handleComDefeitos() {
        loadView("/templates/outher/relatorio/rela_comDefeitos.fxml");
        // Lógica para o botão "Com Defeitos"
    }

    private void handleAvariados() {
        loadView("/templates/outher/relatorio/rela_avariados.fxml");
        // Lógica para o botão "Avariados"
    }

}
