package controler.outher;

import controler.main.MainControler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import notificacao.Notificacao;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static util.FXMLCacheLoader.loadView;

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
        loadView("/templates/outher/visualizar/inventario.fxml", multpage, "");

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

    public void inventario(){
        loadView("/templates/outher/visualizar/inventario.fxml", multpage, "");

    }

    public void historicoUtilizacao(){
        loadView("/templates/outher/visualizar/historicoUtilizacao.fxml", multpage, "");

    }


    private void handleWifiStock() {
        loadView("/templates/outher/visualizar/rela_wifiStock.fxml", multpage, "");
    }

    private void handleWifi() {
        loadView("/templates/outher/visualizar/rela_wifi.fxml", multpage, "");
    }

    private void handleCentroResidos() {
        loadView("/templates/outher/visualizar/rela_centroResido.fxml", multpage, "");
    }


    private void handleInventarioStock() {
        loadView("/templates/outher/visualizar/rela_inventarioStock.fxml", multpage, "");
        // Lógica para o botão "Inventário Stock"visualizar
    }

    private void handleComDefeitos() {
        loadView("/templates/outher/visualizar/rela_comDefeitos.fxml", multpage, "");
        // Lógica para o botão "Com Defeitos"
    }



    private void handleTonerStock() {
        loadView("/templates/outher/visualizar/rela_tonerStock.fxml", multpage, "");
    }

    private void handleUsuToner() {
        loadView("/templates/outher/visualizar/rela_usuToner.fxml", multpage, "");
    }
}
