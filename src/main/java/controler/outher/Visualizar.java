package controler.outher;

import controler.main.MainControler;
import javafx.event.ActionEvent;
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
        btn_avariados.setOnAction(event -> handleAvariados());
        btn_centroResidos.setOnAction(event -> handleCentroResidos());
        btn_wifi.setOnAction(event -> handleWifi());
        btn_wifiStock.setOnAction(event -> handleWifiStock());
        btn_usuToner.setOnAction(event -> handleUsuToner());
        btn_tonerStock.setOnAction(event -> handleTonerStock());
    }

    private void loadInitialView(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/outher/visualizar/inventario.fxml"));
            Parent fxml = loader.load();
            multpage.getChildren().clear();
            multpage.getChildren().add(fxml);
        } catch (IOException e) {
            Logger.getLogger(MainControler.class.getName()).log(Level.SEVERE, null, e);
            notificacao.showErrorAlert("Erro ao carregar a visualização inicial.");
        }
    }

    private void loadlView(String fxmlPatch){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPatch));
            Parent fxml = loader.load();
            multpage.getChildren().clear();
            multpage.getChildren().add(fxml);
        } catch (IOException e) {
            Logger.getLogger(MainControler.class.getName()).log(Level.SEVERE, null, e);
            notificacao.showErrorAlert("Erro ao carregar a visualização inicial.");
        }
    }


    public void inventario(){
        loadlView("/templates/outher/visualizar/inventario.fxml");

    }

    public void historicoUtilizacao(){
        loadlView("/templates/outher/visualizar/historicoUtilizacao.fxml");

    }


    private void handleWifiStock() {
        loadlView("/templates/outher/visualizar/rela_wifiStock.fxml");
    }

    private void handleWifi() {
        loadlView("/templates/outher/visualizar/rela_wifi.fxml");
    }

    private void handleCentroResidos() {
        loadlView("/templates/outher/visualizar/rela_centroResido.fxml");
    }


    private void handleInventarioStock() {
        loadlView("/templates/outher/visualizar/rela_inventarioStock.fxml");
        // Lógica para o botão "Inventário Stock"visualizar
    }

    private void handleComDefeitos() {
        loadlView("/templates/outher/visualizar/rela_comDefeitos.fxml");
        // Lógica para o botão "Com Defeitos"
    }

    private void handleAvariados() {
        loadlView("/templates/outher/visualizar/rela_avariados.fxml");
        // Lógica para o botão "Avariados"
    }



    private void handleTonerStock() {
        loadlView("/templates/outher/visualizar/rela_tonerStock.fxml");
    }

    private void handleUsuToner() {
        loadlView("/templates/outher/visualizar/rela_usuToner.fxml");
    }
}
