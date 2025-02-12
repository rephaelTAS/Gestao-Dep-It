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

public class Additem implements Initializable {

    @FXML
    private StackPane add_multPage;

    Notificacao notificacao = new Notificacao();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadInitialView();
    }

    private void loadInitialView() {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("/templates/outher/addItem/inventario.fxml"));
            add_multPage.getChildren().clear();
            add_multPage.getChildren().add(fxml);
        } catch (IOException e) {
            Logger.getLogger(MainControler.class.getName()).log(Level.SEVERE, null, e);
            notificacao.showErrorAlert("Erro ao carregar a visualização inicial.");
        }
    }

    private void loadView(String fxmlPath) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource(fxmlPath));
            add_multPage.getChildren().clear();
            add_multPage.getChildren().add(fxml);
        } catch (IOException e) {
            Logger.getLogger(MainControler.class.getName()).log(Level.SEVERE, null, e);
            notificacao.showErrorAlert("Erro ao carregar a visualização.");
        }
    }

    public void inventario(ActionEvent e) throws IOException{
        loadView("/templates/outher/addItem/inventario.fxml");

    }

    public void inventario_stock(ActionEvent e) throws IOException{
        loadView("/templates/outher/addItem/inventario_stock.fxml");

    }

    public void wifi(ActionEvent e) throws IOException{
        loadView("/templates/outher/addItem/wifi.fxml");

    }

    public void wifi_stock(ActionEvent e) throws IOException{
        loadView("/templates/outher/addItem/wifi_stock.fxml");

    }

    public void db_funcionarios(ActionEvent e) throws IOException{
        loadView("/templates/outher/addItem/add_funcionario.fxml");

    }


}
