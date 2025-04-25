package packt.app.MainConfig.controlers.outher.visualizar;

import packt.database.DB_Inventario_Stock;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import packt.app.MainConfig.modules.Module_InventarioStock;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class VisInventarioStock implements Initializable {

    @FXML
    private TableView<Module_InventarioStock> tab_inventario;

    @FXML
    private TableColumn<Module_InventarioStock, String> colCodDep;

    @FXML
    private TableColumn<Module_InventarioStock, String> colTipoEquipamento;

    @FXML
    private TableColumn<Module_InventarioStock, String> colMarca;

    @FXML
    private TableColumn<Module_InventarioStock, Integer> colQuantidade;

    @FXML
    private TableColumn<Module_InventarioStock, LocalDate> colDataEntrada;

    @FXML
    private TableColumn<Module_InventarioStock, LocalDate> colUltimaVerificacao;

    @FXML
    private TableColumn<Module_InventarioStock, String> colOperador;

    @FXML
    private TableColumn<Module_InventarioStock, String> colFuncao;

    @FXML
    private TableColumn<Module_InventarioStock, String> colLocalSala;

    @FXML
    private TableColumn<Module_InventarioStock, String> colDepartamento;

    @FXML
    private TableColumn<Module_InventarioStock, String> colSituacaoEquipamento;

    @FXML
    private TableColumn<Module_InventarioStock, String> colObs;

    private DB_Inventario_Stock dbInventarioStock = new DB_Inventario_Stock();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configura as colunas da TableView
        colCodDep.setCellValueFactory(new PropertyValueFactory<>("codDep"));
        colTipoEquipamento.setCellValueFactory(new PropertyValueFactory<>("tipoEquipamento"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colDataEntrada.setCellValueFactory(new PropertyValueFactory<>("dataEntradaServico"));
        colUltimaVerificacao.setCellValueFactory(new PropertyValueFactory<>("ultimaVerificacao"));
        colOperador.setCellValueFactory(new PropertyValueFactory<>("operador"));
        colFuncao.setCellValueFactory(new PropertyValueFactory<>("funcao"));
        colLocalSala.setCellValueFactory(new PropertyValueFactory<>("localizacao"));
        colDepartamento.setCellValueFactory(new PropertyValueFactory<>("departamento"));
        colSituacaoEquipamento.setCellValueFactory(new PropertyValueFactory<>("situacaoEquipamento"));
        colObs.setCellValueFactory(new PropertyValueFactory<>("obs"));

        // Carrega os dados do banco de dados
        carregarDados();
    }

    private void carregarDados() {
        // Obtém os dados do banco de dados
        List<Module_InventarioStock> dados = dbInventarioStock.mostrarDadosInventarioStock();

        // Converte a lista para um ObservableList
        ObservableList<Module_InventarioStock> listaObservavel = FXCollections.observableArrayList(dados);

        // Define os dados na TableView
        tab_inventario.setItems(listaObservavel);
    }
}

