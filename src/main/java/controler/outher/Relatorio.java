package controler.outher;

import app.styles.config.StyleConfig;
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

import static util.FXMLCacheLoader.loadView;

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
        StyleConfig.init();
        loadView("/templates/outher/relatorio/rela_tonerStock.fxml", stackPane_relatorio, "relatorioTonerStock");

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



    private void handleTonerStock() {
        loadView("/templates/outher/relatorio/rela_tonerStock.fxml", stackPane_relatorio, "relatorioTonerStock");
    }

    private void handleUsuToner() {
        loadView("/templates/outher/relatorio/rela_usuToner.fxml", stackPane_relatorio, "relatorioUsuToner");
    }

    private void handleWifiStock() {
        loadView("/templates/outher/relatorio/rela_wifiStock.fxml", stackPane_relatorio, "relatorioWifiStock");
    }

    private void handleWifi() {
        loadView("/templates/outher/relatorio/rela_wifi.fxml", stackPane_relatorio, "relatorioWifi");
    }

    private void handleCentroResidos() {
        loadView("/templates/outher/relatorio/rela_centroResido.fxml", stackPane_relatorio, "");
    }

    private void handleInventario() {
        loadView("/templates/outher/relatorio/rela_inventario.fxml", stackPane_relatorio, "");
    }


    private void handleInventarioStock() {
        loadView("/templates/outher/relatorio/rela_inventarioStock.fxml", stackPane_relatorio, "");
        // Lógica para o botão "Inventário Stock"
    }

    private void handleComDefeitos() {
        loadView("/templates/outher/relatorio/rela_comDefeitos.fxml", stackPane_relatorio, "");
        // Lógica para o botão "Com Defeitos"
    }


    private void handleHistoricoUtilizacao(){
        loadView("/templates/outher/relatorio/rela_historicoUtilizcao.fxml", stackPane_relatorio, "");
    }

}
