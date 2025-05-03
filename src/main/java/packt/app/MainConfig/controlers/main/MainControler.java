package packt.app.MainConfig.controlers.main;

import javafx.scene.control.Button;
import packt.app.MainConfig.notificacao.Notificacao;
import packt.app.ViewInitializer;
import packt.app.views.FXMLManager;
import packt.app.views.config.ViewConfig;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainControler implements Initializable {

    @FXML
    private StackPane multiploPanel;

    @FXML
    private Button btn_dashboard;

    @FXML
    private Button btn_additem;

    @FXML
    private Button btn_relatorio;

    @FXML
    private Button btn_visualizar;



    private final Notificacao notificacao = new Notificacao();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadView(ViewConfig.Main.DASHBOARD);

        btn_dashboard.setOnAction(event -> home());
        btn_additem.setOnAction(event -> add_item());
        btn_relatorio.setOnAction(event -> realotrios());
        btn_visualizar.setOnAction(event -> visualizar());
    }

    public void home() {
        loadView(ViewConfig.Main.DASHBOARD);
    }

    public void add_item() {
        loadView(ViewConfig.Main.ADD_ITEM);
    }

    public void realotrios() {
        loadView(ViewConfig.Main.RELATORIO);
    }

    public void visualizar() {
        loadView(ViewConfig.Main.VISUALIZAR);
    }

    private void loadView(String viewId) {
        try {
            Parent view = FXMLManager.loadStaticView(viewId);
            multiploPanel.getChildren().setAll(view);
        } catch (Exception e) {
            notificacao.showError("Erro ao carregar a view: " + viewId + "\n" + e.getMessage());
        }
    }
}
