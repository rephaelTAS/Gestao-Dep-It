package packt.app.MainConfig.controlers.main;

import javafx.scene.control.Button;
import packt.app.MainConfig.notificacao.Notificacao;
import packt.app.views.FXMLManager;
import packt.app.views.config.ViewConfig;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import java.net.URL;
import java.util.ResourceBundle;

public class Additem implements Initializable {



    // ---- Componentes UI ----
    @FXML private Button btn_novoProduto;
    @FXML private Button btn_inventario;
    @FXML private Button btn_toner;
    @FXML private Button btn_tonerStock;
    @FXML private Button btn_wifi;
    @FXML private Button btn_dbFuncionarios;
    @FXML private StackPane add_multPage;

    // ---- Serviços ----
    private final Notificacao notificacao = new Notificacao();

    // ---- Lifecycle ----
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDefaultView();
        btn_inventario.setOnAction(event ->loadView(ViewConfig.AddItem.INVENTARIO));
        btn_toner.setOnAction(event ->loadView(ViewConfig.AddItem.TONER));
        btn_tonerStock.setOnAction(event ->loadView(ViewConfig.AddItem.TONER_STOCK));
        btn_wifi.setOnAction(event ->loadView(ViewConfig.AddItem.WIFI));
        btn_dbFuncionarios.setOnAction(event ->loadView(ViewConfig.AddItem.FUNCIONARIO));
        btn_novoProduto.setOnAction(event -> loadView(ViewConfig.AddItem.NEW_PRODUCT));
    }

    // ---- Métodos de Carregamento ----
    private void loadDefaultView() {
        loadView(ViewConfig.AddItem.INVENTARIO);
    }


    private void loadView(String viewId) {
        try {
            Parent view = FXMLManager.loadStaticView(viewId);
            add_multPage.getChildren().setAll(view);
        } catch (Exception e) {
            notificacao.showError("Erro ao carregar a view: " + viewId + "\n" + e.getMessage());
        }
    }

}