package controler.outher.relatorio;

import database.DB_Inventario_Stock;
import export_import.ExportToExcel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Module_InventarioStock;

import java.io.File;
import java.util.List;

public class RelaInventarioStock {

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
    private TableColumn<Module_InventarioStock, String> colDataEntrada;
    @FXML
    private TableColumn<Module_InventarioStock, String> colUltimaVerificacao;
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

    @FXML
    private Button btn_gerarExcel; // Botão "Gerar Excel"

    private ObservableList<Module_InventarioStock> inventarioListStock = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // Configurar as colunas da tabela
        colCodDep.setCellValueFactory(new PropertyValueFactory<>("codDep"));
        colTipoEquipamento.setCellValueFactory(new PropertyValueFactory<>("tipoEquipamento"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colDataEntrada.setCellValueFactory(new PropertyValueFactory<>("dataEntrada"));
        colUltimaVerificacao.setCellValueFactory(new PropertyValueFactory<>("ultimaVerificacao"));
        colOperador.setCellValueFactory(new PropertyValueFactory<>("operador"));
        colFuncao.setCellValueFactory(new PropertyValueFactory<>("funcao"));
        colLocalSala.setCellValueFactory(new PropertyValueFactory<>("localizacao"));
        colDepartamento.setCellValueFactory(new PropertyValueFactory<>("departamento"));
        colSituacaoEquipamento.setCellValueFactory(new PropertyValueFactory<>("situacaoEquipamento"));
        colObs.setCellValueFactory(new PropertyValueFactory<>("obs"));

        // Carregar os dados do inventário
        mostrarInventario();

        // Configurar o botão "Gerar Excel"
        btn_gerarExcel.setOnAction(event -> handleGerarExcel());
    }

    private void mostrarInventario() {
        DB_Inventario_Stock dbInventario = new DB_Inventario_Stock();
        List<Module_InventarioStock> inventariosStock = dbInventario.mostrarInventarioStock();

        // Limpar a lista antes de adicionar novos itens
        inventarioListStock.clear();

        // Adicionar os dados recebidos à lista observável
        inventarioListStock.addAll(inventariosStock);

        // Definir a lista na TableView
        tab_inventario.setItems(inventarioListStock);
    }

    private void handleGerarExcel() {
        Stage primaryStage = (Stage) btn_gerarExcel.getScene().getWindow(); // Obtendo a referência ao Stage
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar Arquivo Excel");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));

        File file = fileChooser.showSaveDialog(primaryStage); // Abrindo o diálogo para salvar o arquivo

        if (file != null) {
            ExportToExcel exporter = new ExportToExcel();
            exporter.exportarInventStockParaExcel(inventarioListStock, file.getAbsolutePath());
        } else {
            System.out.println("Exportação cancelada.");
        }
    }
}