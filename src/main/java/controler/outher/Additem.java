package controler.outher;

import controler.main.MainControler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import notificacao.Notificacao;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static util.FXMLCacheLoader.loadView;

public class Additem implements Initializable {

    @FXML
    private StackPane add_multPage;

    Notificacao notificacao = new Notificacao();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadView("/templates/outher/addItem/inventario.fxml", add_multPage, "");
    }



    public void inventario(ActionEvent e) throws IOException{
        loadView("/templates/outher/addItem/inventario.fxml", add_multPage, "");

    }

    public void inventario_stock(ActionEvent e) throws IOException{
        loadView("/templates/outher/addItem/inventario_stock.fxml", add_multPage, "");

    }

    public void Btn_TonerOnAction(ActionEvent e) throws IOException{
        loadView("/templates/outher/addItem/add_toner.fxml", add_multPage, "");
    }

    public void Btn_TonerStockOnAction(ActionEvent e) throws IOException{
        loadView("/templates/outher/addItem/toner_stock.fxml", add_multPage, "");
    }

    public void wifi(ActionEvent e) throws IOException{
        loadView("/templates/outher/addItem/wifi.fxml", add_multPage, "");

    }

    public void wifi_stock(ActionEvent e) throws IOException{
        loadView("/templates/outher/addItem/wifi_stock.fxml", add_multPage, "");

    }

    public void db_funcionarios(ActionEvent e) throws IOException{
        loadView("/templates/outher/addItem/add_funcionario.fxml", add_multPage, "");

    }

}
