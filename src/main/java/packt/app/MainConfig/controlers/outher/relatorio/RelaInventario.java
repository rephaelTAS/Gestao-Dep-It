package packt.app.MainConfig.controlers.outher.relatorio;

import packt.database.DB_Inventario;
import packt.app.MainConfig.exportImport.ExportToExcel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import packt.app.MainConfig.modules.Module_Inventario;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class RelaInventario {

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
    private Button Btn_GerarExcel; // Adicione este botão no seu FXML

    private ObservableList<Module_Inventario> inventarioList = FXCollections.observableArrayList();

    ExportToExcel gerarExcel = new ExportToExcel();

    @FXML
    private void initialize() {
        mostrarInventario();

        Btn_GerarExcel.setOnAction(event -> {
            try {
                handleExportButton();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        // Configurar as colunas da tabela
        colCodDep.setCellValueFactory(new PropertyValueFactory<>("codDep"));
        colTipoEquipamento.setCellValueFactory(new PropertyValueFactory<>("tipoEquipamento"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colNumSerie.setCellValueFactory(new PropertyValueFactory<>("num_serie"));
        colDataEntrada.setCellValueFactory(new PropertyValueFactory<>("dataEntradaServico"));
        colDataVerificacao.setCellValueFactory(new PropertyValueFactory<>("ultimaVerificacao"));
        colOperador.setCellValueFactory(new PropertyValueFactory<>("operador"));
        colFuncao.setCellValueFactory(new PropertyValueFactory<>("funcao"));
        colLocalSala.setCellValueFactory(new PropertyValueFactory<>("localizacao"));
        colDepartamento.setCellValueFactory(new PropertyValueFactory<>("departamento"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colSituacao.setCellValueFactory(new PropertyValueFactory<>("situacaoEquipamento "));
        colObs.setCellValueFactory(new PropertyValueFactory<>("obs"));
    }

    public void mostrarInventario() {
        DB_Inventario dbInventario = new DB_Inventario();
        List<Module_Inventario> inventarios = dbInventario.mostrarInventario();

        // Limpar a lista antes de adicionar novos itens
        inventarioList.clear();

        // Adicionar os dados recebidos à lista observável
        inventarioList.addAll(inventarios);

        // Definir a lista na TableView
        tab_inventario.setItems(inventarioList);
    }

    @FXML
    private void handleExportButton() throws IOException {
        Stage primaryStage = (Stage) Btn_GerarExcel.getScene().getWindow(); // Obtendo a referência ao Stage
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar Arquivo Excel");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));

        File file = fileChooser.showSaveDialog(primaryStage); // Abrindo o diálogo para salvar o arquivo

        if (file != null) {
            ExportToExcel exporter = new ExportToExcel();
            exporter.exportarInventarioParaExcel(inventarioList, file.getAbsolutePath());
        } else {
            System.out.println("Exportação cancelada.");
        }
    }

}