package packt.app.MainConfig.controlers.outher.visualizar;

import packt.database.DB_HistoUtilizacao;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import packt.app.MainConfig.modules.Module_Inventario;

import java.time.format.DateTimeFormatter;
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

    private ObservableList<Module_Inventario> listaEquipamentos;

    @FXML
    public void initialize() {
        // Inicializa a lista de equipamentos
        listaEquipamentos = FXCollections.observableArrayList();

        // Configura as colunas da tabela
        colCodDep.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodDep()));
        colTipoEquipamento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTipoEquipamento()));
        colMarca.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMarca()));
        colModelo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModelo()));
        colNumSerie.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNum_serie()));

        // Formatação das datas
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        colDataEntrada.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDataEntradaServico().format(formatter)));
        colDataVerificacao.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUltimaVerificacao().format(formatter)));

        colOperador.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOperador()));
        colFuncao.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFuncao()));
        colLocalSala.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLocalizacao()));
        colDepartamento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDepartamento()));
        colStatus.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));
        colSituacao.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSituacaoEquipamento()));
        colObs.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getObs()));

        mostrarHistorico();

    }

    public void mostrarHistorico(){
        DB_HistoUtilizacao dbhistorico = new DB_HistoUtilizacao();
        List<Module_Inventario> inventarios = dbhistorico.mostrarHistorico();


        listaEquipamentos.clear();

        listaEquipamentos.addAll(inventarios);

        // Adiciona a lista de equipamentos à tabela
        tab_inventario.setItems(listaEquipamentos);
    }

 
}