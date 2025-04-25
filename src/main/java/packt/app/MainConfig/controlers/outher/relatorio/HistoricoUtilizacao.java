package packt.app.MainConfig.controlers.outher.relatorio;

import packt.database.DB_HistoUtilizacao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import packt.app.MainConfig.modules.Module_Inventario;

import java.util.List;

public class HistoricoUtilizacao {

    @FXML
    private TableView<Module_Inventario> tab_inventario;

    @FXML
    private TableColumn<Module_Inventario, String> colCodDep;

    @FXML
    private TableColumn<Module_Inventario, String> colTipoEquipamento;

    @FXML
    private TableColumn<Module_Inventario, String> colMarca;

    @FXML
    private TableColumn<Module_Inventario, String> colModelo;

    @FXML
    private TableColumn<Module_Inventario, String> colNumSerie;

    @FXML
    private TableColumn<Module_Inventario, String> colDataEntrada;

    @FXML
    private TableColumn<Module_Inventario, String> colDataVerificacao;

    @FXML
    private TableColumn<Module_Inventario, String> colOperador;

    @FXML
    private TableColumn<Module_Inventario, String> colFuncao;

    @FXML
    private TableColumn<Module_Inventario, String> colLocalSala;

    @FXML
    private TableColumn<Module_Inventario, String> colDepartamento;

    @FXML
    private TableColumn<Module_Inventario, String> colStatus;

    @FXML
    private TableColumn<Module_Inventario, String> colSituacao;

    @FXML
    private TableColumn<Module_Inventario, String> colObs;

    @FXML
    private Button btn_exportar;

    ObservableList<Module_Inventario> equipamentos = FXCollections.observableArrayList();
    @FXML
    public void initialize() {

        mostrarHistorico();
        // Configura as colunas da TableView
        colCodDep.setCellValueFactory(new PropertyValueFactory<>("codDep"));
        colTipoEquipamento.setCellValueFactory(new PropertyValueFactory<>("tipoEquipamento"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colNumSerie.setCellValueFactory(new PropertyValueFactory<>("numSerie"));
        colDataEntrada.setCellValueFactory(new PropertyValueFactory<>("dataEntrada"));
        colDataVerificacao.setCellValueFactory(new PropertyValueFactory<>("dataVerificacao"));
        colOperador.setCellValueFactory(new PropertyValueFactory<>("operador"));
        colFuncao.setCellValueFactory(new PropertyValueFactory<>("funcao"));
        colLocalSala.setCellValueFactory(new PropertyValueFactory<>("localSala"));
        colDepartamento.setCellValueFactory(new PropertyValueFactory<>("departamento"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colSituacao.setCellValueFactory(new PropertyValueFactory<>("situacao"));
        colObs.setCellValueFactory(new PropertyValueFactory<>("obs"));

        // Preenche a TableView com dados de exemplo
    }

    public void mostrarHistorico(){
        DB_HistoUtilizacao dbHistoUtilizacao = new DB_HistoUtilizacao();
        List<Module_Inventario> inventarios = dbHistoUtilizacao.mostrarHistorico();

        equipamentos.clear();

        equipamentos.addAll(inventarios);

        tab_inventario.setItems(equipamentos);
    }
}