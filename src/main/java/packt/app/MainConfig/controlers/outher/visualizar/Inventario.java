package packt.app.MainConfig.controlers.outher.visualizar;

import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import packt.app.MainConfig.controlers.outher.editar.EditarInventario;
import packt.app.MainConfig.exportImport.ExportToExcel;
import packt.app.MainConfig.notificacao.Notificacao;
import packt.app.services.InventarioService;
import packt.app.views.FXMLManager;
import packt.app.views.ModalDialog;
import packt.app.views.config.ViewConfig;
import packt.database.DB_Inventario;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import packt.app.MainConfig.modules.Module_Inventario;

import java.io.File;
import java.io.IOException;
import java.util.List;

import java.time.format.DateTimeFormatter;

public class Inventario {


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



    private ObservableList<Module_Inventario> listaEquipamentos = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        mostrarInventario();

        Btn_GerarExcel.setOnAction(event -> {
            try {
                handleExportButton();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


        // Inicializa a lista de equipamentos
        adicionarEquipamento();
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


    }

    // No seu controller principal
    public class InventarioController {
        @FXML private TableView<Module_Inventario> tabelaInventario;

        @FXML
        private void handleEditarItem() {
            Module_Inventario itemSelecionado = tabelaInventario.getSelectionModel().getSelectedItem();

            if (itemSelecionado != null) {
                InventarioService.editarItem(itemSelecionado, tabelaInventario.getScene().getWindow());

                // Atualiza a tabela se o item foi modificado
                EditarInventario controller =
                        FXMLManager.getController(ViewConfig.Editar.INVENTARIO);


            } else {
                new Notificacao().showWarning("Selecione um item para editar");
            }
        }
    }


    // Método para adicionar um novo equipamento à lista
    public void adicionarEquipamento() {

        DB_Inventario dbInventario = new DB_Inventario();
        List<Module_Inventario> inventarios = dbInventario.mostrarInventario();
        listaEquipamentos.clear();

        listaEquipamentos.addAll(inventarios);

        // Adiciona a lista de equipamentos à tabela
        tab_inventario.setItems(listaEquipamentos);
    }
}

