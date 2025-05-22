package packt.app.MainConfig.controlers.outher.visualizar;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import packt.app.MainConfig.controlers.outher.editar.EditarInventario;
import packt.app.MainConfig.exportImport.ExportToExcel;
import packt.app.MainConfig.modules.Module_Inventario;
import packt.app.MainConfig.notificacao.Notificacao;
import packt.app.views.ModalDialog;
import packt.database.DB_Inventario;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Inventario {

    // Constantes
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final String DEFAULT_NULL_VALUE = "-";


    // Componentes FXML
    @FXML private TableView<Module_Inventario> tab_inventario;
    @FXML private TableColumn<Module_Inventario, String> colCodDep;
    @FXML private TableColumn<Module_Inventario, String> idProdut;
    @FXML private TableColumn<Module_Inventario, String> colTipoEquipamento;
    @FXML private TableColumn<Module_Inventario, String> colMarca;
    @FXML private TableColumn<Module_Inventario, String> colModelo;
    @FXML private TableColumn<Module_Inventario, String> colNumSerie;
    @FXML private TableColumn<Module_Inventario, String> colDataEntrada;
    @FXML private TableColumn<Module_Inventario, String> colDataVerificacao;
    @FXML private TableColumn<Module_Inventario, String> colOperador;
    @FXML private TableColumn<Module_Inventario, String> colFuncao;
    @FXML private TableColumn<Module_Inventario, String> colLocalSala;
    @FXML private TableColumn<Module_Inventario, String> colDepartamento;
    @FXML private TableColumn<Module_Inventario, String> colStatus;
    @FXML private TableColumn<Module_Inventario, String> colSituacao;
    @FXML private TableColumn<Module_Inventario, String> colObs;
    @FXML private Button Btn_GerarExcel;
    @FXML private Button btn_editarDados;

    // Dados e serviços
    private final ObservableList<Module_Inventario> inventarioList = FXCollections.observableArrayList();
    private final Notificacao notificacao = new Notificacao();
    private final ExportToExcel gerarExcel = new ExportToExcel();
    private final DB_Inventario dbInventario = new DB_Inventario();

    @FXML
    public void initialize() {
        configurarColunas();
        configurarBotoes();
        carregarDados();
    }

    private void configurarColunas() {
        // Configuração básica para colunas de texto
        colCodDep.setCellValueFactory(cellData -> formatStringProperty(cellData.getValue().getCodDep()));
        idProdut.setCellValueFactory(cellData -> formatStringProperty(cellData.getValue().getIdProdut()));
        colTipoEquipamento.setCellValueFactory(cellData -> formatStringProperty(cellData.getValue().getTipoEquipamento()));
        colMarca.setCellValueFactory(cellData -> formatStringProperty(cellData.getValue().getMarca()));
        colModelo.setCellValueFactory(cellData -> formatStringProperty(cellData.getValue().getModelo()));
        colNumSerie.setCellValueFactory(cellData -> formatStringProperty(cellData.getValue().getNum_serie()));
        colOperador.setCellValueFactory(cellData -> formatStringProperty(cellData.getValue().getOperador()));
        colFuncao.setCellValueFactory(cellData -> formatStringProperty(cellData.getValue().getFuncao()));
        colLocalSala.setCellValueFactory(cellData -> formatStringProperty(cellData.getValue().getLocalizacao()));
        colDepartamento.setCellValueFactory(cellData -> formatStringProperty(cellData.getValue().getDepartamento()));
        colStatus.setCellValueFactory(cellData -> formatStringProperty(cellData.getValue().getStatus()));
        colSituacao.setCellValueFactory(cellData -> formatStringProperty(cellData.getValue().getSituacaoEquipamento()));
        colObs.setCellValueFactory(cellData -> formatStringProperty(cellData.getValue().getObs()));

        // Configuração especial para colunas de data (convertendo LocalDate para String)
        colDataEntrada.setCellValueFactory(cellData ->
                formatDateProperty(cellData.getValue().getDataEntradaServico()));

        colDataVerificacao.setCellValueFactory(cellData ->
                formatDateProperty(cellData.getValue().getUltimaVerificacao()));
    }

    private SimpleStringProperty formatStringProperty(String value) {
        return new SimpleStringProperty(value != null ? value : DEFAULT_NULL_VALUE);
    }

    private SimpleStringProperty formatDateProperty(LocalDate date) {
        if (date == null) {
            return new SimpleStringProperty(DEFAULT_NULL_VALUE);
        }
        try {
            return new SimpleStringProperty(DATE_FORMATTER.format(date));
        } catch (DateTimeParseException e) {
            return new SimpleStringProperty("Data inválida");
        }
    }

    private void configurarBotoes() {
        Btn_GerarExcel.setOnAction(event -> exportarParaExcel());
        btn_editarDados.setOnAction(event -> editarItemSelecionado());
    }

    private void carregarDados() {
        try {
            List<Module_Inventario> inventarios = dbInventario.mostrarInventario();
            inventarioList.setAll(inventarios);
            tab_inventario.setItems(inventarioList);
        } catch (Exception e) {
            notificacao.showError("Erro ao carregar inventário: " + e.getMessage());
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, "Erro ao carregar dados", e);
        }
    }

    private void exportarParaExcel() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar Arquivo Excel");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));

        Window owner = Btn_GerarExcel.getScene().getWindow();
        File file = fileChooser.showSaveDialog(owner);

        if (file != null) {
            try {
                gerarExcel.exportarInventarioParaExcel(inventarioList, file.getAbsolutePath());
                notificacao.showSuccess("Exportação concluída com sucesso!");
            } catch (IOException e) {
                notificacao.showError("Erro ao exportar: " + e.getMessage());
                Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, "Erro na exportação", e);
            }
        } else {
            notificacao.showInfo("Exportação cancelada pelo usuário");
        }
    }

    private void editarItemSelecionado() {
        Module_Inventario itemSelecionado = tab_inventario.getSelectionModel().getSelectedItem();
        Window owner = btn_editarDados.getScene().getWindow();

        if (itemSelecionado != null) {
            Optional<Boolean> resultado = ModalDialog.<EditarInventario, Boolean>showModalWithResult(
                    "editar_inventario",
                    owner,
                    "Editar Item",
                    StageStyle.DECORATED,
                    controller -> {
                        if (controller instanceof EditarInventario) {
                            ((EditarInventario) controller).setItem(itemSelecionado);
                        }
                    },
                    controller -> {
                        // Aqui você deve retornar um valor do tipo Boolean
                        return controller.isItemEdited(); // Supondo que você tenha um método que retorna um booleano
                    }
            );

            if (resultado.isPresent() && resultado.get()) {
                int index = tab_inventario.getItems().indexOf(itemSelecionado);
                if (index >= 0) {
                    tab_inventario.getItems().set(index, itemSelecionado);
                }
            }
        } else {
            notificacao.showWarning("Seleção necessária" + "Por favor, selecione um item para editar");
        }
    }
}