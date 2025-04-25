package packt.app.MainConfig.controlers.outher.relatorio;


import packt.app.MainConfig.modules.Module_CentroRecido;
import packt.database.DB_CentroResido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.List;

public class RelaCentroResido {
    @FXML
    private TableView<Module_CentroRecido> tableView;

    @FXML
    private TableColumn<Module_CentroRecido, String> codDepColumn;

    @FXML
    private TableColumn<Module_CentroRecido, String> tipoEquipamentoColumn;

    @FXML
    private TableColumn<Module_CentroRecido, String> marcaColumn;

    @FXML
    private TableColumn<Module_CentroRecido, String> modeloColumn;

    @FXML
    private TableColumn<Module_CentroRecido, String> numeroSerieColumn;

    @FXML
    private TableColumn<Module_CentroRecido, LocalDate> dataEntradaColumn;

    @FXML
    private TableColumn<Module_CentroRecido, String> operadorColumn;

    @FXML
    private TableColumn<Module_CentroRecido, String> funcaoColumn;

    @FXML
    private TableColumn<Module_CentroRecido, String> localizacaoSalaColumn;

    @FXML
    private TableColumn<Module_CentroRecido, String> departamentoColumn;

    @FXML
    private TableColumn<Module_CentroRecido, String> obsColumn;

    // Adicionar dados à tabela
    ObservableList<Module_CentroRecido> equipamentos = FXCollections.observableArrayList();


    @FXML
    public void initialize() {
        // Configurar as colunas da tabela
        codDepColumn.setCellValueFactory(new PropertyValueFactory<>("codDep"));
        tipoEquipamentoColumn.setCellValueFactory(new PropertyValueFactory<>("tipoEquipamento"));
        marcaColumn.setCellValueFactory(new PropertyValueFactory<>("marca"));
        modeloColumn.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        numeroSerieColumn.setCellValueFactory(new PropertyValueFactory<>("numeroSerie"));
        dataEntradaColumn.setCellValueFactory(new PropertyValueFactory<>("dataEntrada"));
        operadorColumn.setCellValueFactory(new PropertyValueFactory<>("operador"));
        funcaoColumn.setCellValueFactory(new PropertyValueFactory<>("funcao"));
        localizacaoSalaColumn.setCellValueFactory(new PropertyValueFactory<>("localizacaoSala"));
        departamentoColumn.setCellValueFactory(new PropertyValueFactory<>("departamento"));
        obsColumn.setCellValueFactory(new PropertyValueFactory<>("obs"));

        mostrarDados();

    }

    public void mostrarDados(){
        DB_CentroResido db_centroResido = new DB_CentroResido();
        List<Module_CentroRecido> inventarios = db_centroResido.mostrarDadosCentroResido();

        equipamentos.clear();

        equipamentos.addAll(inventarios);

        tableView.setItems(equipamentos);

    }
}