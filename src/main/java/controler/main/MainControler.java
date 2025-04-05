package controler.main;

import app.styles.config.StyleConfig;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import notificacao.Notificacao;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static util.FXMLCacheLoader.loadView;

public class MainControler implements Initializable {

    @FXML
    private StackPane multiploPanel;

    Notificacao notificacao = new Notificacao();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        StyleConfig.init();
        loadView("/templates/outher/dashboard.fxml", multiploPanel, "");
    }

    public void home(ActionEvent e) throws IOException{
        loadView("/templates/outher/dashboard.fxml", multiploPanel, "");
    }

    public void add_item(ActionEvent e) throws IOException{
        loadView("/templates/outher/additem.fxml", multiploPanel, "additem");
    }

    public void realotrios(ActionEvent e) throws IOException{
        loadView("/templates/outher/relatorio.fxml", multiploPanel, "relatorio");
    }

    public void visualizar(ActionEvent e) throws IOException{
        loadView("/templates/outher/visualizar.fxml", multiploPanel, "visualizar");

    }
}
