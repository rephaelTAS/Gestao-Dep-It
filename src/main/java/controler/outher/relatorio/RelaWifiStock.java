package controler.outher.relatorio;

import database.DB_WifiStock;
import export_import.ExportToExcel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Module_WifiStock;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class RelaWifiStock implements Initializable {

    @FXML
    private TableView<Module_WifiStock> tab_wifiStock;

    @FXML
    private TableColumn<Module_WifiStock, String> colCodDep;

    @FXML
    private TableColumn<Module_WifiStock, String> colTipoEquipamento;

    @FXML
    private TableColumn<Module_WifiStock, String> colMarca;

    @FXML
    private TableColumn<Module_WifiStock, String> colModelo;

    @FXML
    private TableColumn<Module_WifiStock, Integer> colQuantidade;

    @FXML
    private TableColumn<Module_WifiStock, LocalDate> colDataEntrada;

    @FXML
    private TableColumn<Module_WifiStock, LocalDate> colDataVerificacao;

    @FXML
    private TableColumn<Module_WifiStock, String> colOperador;

    @FXML
    private TableColumn<Module_WifiStock, String> colSituacao;

    @FXML
    private TableColumn<Module_WifiStock, String> colObs;

    @FXML
    private Button Btn_GerarExcel;

    // Lista observável para armazenar os dados da tabela
    private ObservableList<Module_WifiStock> listaObservavel = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configura as colunas da TableView
        configurarColunas();

        // Carrega os dados do banco de dados
        carregarDados();

        Btn_GerarExcel.setOnAction(event -> {
            try {
                gerarExcel();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void configurarColunas() {
        // Vincula cada coluna ao atributo correspondente da classe Module_WifiStock
        colCodDep.setCellValueFactory(new PropertyValueFactory<>("codDep"));
        colTipoEquipamento.setCellValueFactory(new PropertyValueFactory<>("tipoEquipamento"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colDataEntrada.setCellValueFactory(new PropertyValueFactory<>("dataEntrada"));
        colDataVerificacao.setCellValueFactory(new PropertyValueFactory<>("ultimaVerificacao"));
        colOperador.setCellValueFactory(new PropertyValueFactory<>("operador"));
        colSituacao.setCellValueFactory(new PropertyValueFactory<>("situacaoEquipamento"));
        colObs.setCellValueFactory(new PropertyValueFactory<>("obs"));
    }

    private void carregarDados() {
        // Obtém os dados do banco de dados
        DB_WifiStock dbWifiStock = new DB_WifiStock();
        List<Module_WifiStock> dados = dbWifiStock.mostrarDadosWifiStock();

        // Limpa a lista observável e adiciona os novos dados
        listaObservavel.clear();
        listaObservavel.addAll(dados);

        // Define os dados na TableView
        tab_wifiStock.setItems(listaObservavel);
    }

    public void gerarExcel() throws IOException {
        Stage primaryStage = (Stage) Btn_GerarExcel.getScene().getWindow(); // Obtendo a referência ao Stage
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar Arquivo Excel");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));

        File file = fileChooser.showSaveDialog(primaryStage); // Abrindo o diálogo para salvar o arquivo

        if (file != null) {
            ExportToExcel exporter = new ExportToExcel();
            exporter.exportarWifiStockParaExcel(listaObservavel, file.getAbsolutePath());
        } else {
            System.out.println("Exportação cancelada.");
        }
    }
}