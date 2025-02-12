package controler.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import notificacao.Notificacao;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainControler implements Initializable {

    @FXML
    private StackPane multiploPanel;

    Notificacao notificacao = new Notificacao();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadInitialView();
    }

    private void loadInitialView() {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("/templates/outher/dashboard.fxml"));
            multiploPanel.getChildren().clear();
            multiploPanel.getChildren().add(fxml);
        } catch (IOException e) {
            Logger.getLogger(MainControler.class.getName()).log(Level.SEVERE, null, e);
            notificacao.showErrorAlert("Erro ao carregar a visualização inicial.");
        }
    }

    private void loadView(String fxmlPath) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource(fxmlPath));
            multiploPanel.getChildren().clear();
            multiploPanel.getChildren().add(fxml);
        } catch (IOException e) {
            Logger.getLogger(MainControler.class.getName()).log(Level.SEVERE, null, e);
            notificacao.showErrorAlert("Erro ao carregar a visualização.");
        }
    }

    public void home(ActionEvent e) throws IOException{
        loadView("/templates/outher/dashboard.fxml");
    }

    public void add_item(ActionEvent e) throws IOException{
        loadView("/templates/outher/additem.fxml");
    }

    public void realotrios(ActionEvent e) throws IOException{
        loadView("/templates/outher/relatorio.fxml");
    }

    public void visualizar(ActionEvent e) throws IOException{
        loadView("/templates/outher/visualizar.fxml");

    }
}
