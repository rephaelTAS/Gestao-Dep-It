package controler.outher.relatorio;


import database.DB_TonerStock;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Module_TonerStock;


import java.util.List;

public class RelaTonerStock {

    @FXML
    private TableView<Module_TonerStock> tab_wifiStock;

    @FXML
    private TableColumn<Module_TonerStock, String> colCodDep;

    @FXML
    private TableColumn<Module_TonerStock, String> colToner;

    @FXML
    private TableColumn<Module_TonerStock, String> colMarca;

    @FXML
    private TableColumn<Module_TonerStock, String> colCor;

    @FXML
    private TableColumn<Module_TonerStock, String> colImpressora;

    @FXML
    private TableColumn<Module_TonerStock, Integer> colUnidade;

    @FXML
    private TableColumn<Module_TonerStock, String> colStatus;

    @FXML
    private TableColumn<Module_TonerStock, String> colOperador;

    @FXML
    private TableColumn<Module_TonerStock, String> colFuncao;

    @FXML
    private TableColumn<Module_TonerStock, String> colLocalizacao;

    @FXML
    private TableColumn<Module_TonerStock, String> colDepartamento;

    // Método inicializador
    @FXML
    public void initialize() {
        // Configura as colunas para usar as propriedades do modelo Module_TonerStock
        colCodDep.setCellValueFactory(new PropertyValueFactory<>("codDep"));
        colToner.setCellValueFactory(new PropertyValueFactory<>("toner"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colCor.setCellValueFactory(new PropertyValueFactory<>("cor"));
        colImpressora.setCellValueFactory(new PropertyValueFactory<>("impressora"));
        colUnidade.setCellValueFactory(new PropertyValueFactory<>("unidade"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colOperador.setCellValueFactory(new PropertyValueFactory<>("operador"));
        colFuncao.setCellValueFactory(new PropertyValueFactory<>("funcao"));
        colLocalizacao.setCellValueFactory(new PropertyValueFactory<>("localizacao"));
        colDepartamento.setCellValueFactory(new PropertyValueFactory<>("departamento"));

        // Carrega os dados do banco de dados
        loadData();
    }

    // Método para carregar os dados do banco de dados
    private void loadData() {
        DB_TonerStock tonerStock = new DB_TonerStock();
        List<Module_TonerStock> tonerStocks = tonerStock.readAllTonerStocks();
        tab_wifiStock.getItems().addAll(tonerStocks);
    }
}