package packt.app.MainConfig.controlers.outher.visualizar;

import javafx.stage.StageStyle;
import javafx.stage.Window;
import packt.app.MainConfig.controlers.outher.editar.EditarInventario;
import packt.app.MainConfig.controlers.outher.editar.EditarInventarioStock;
import packt.app.MainConfig.notificacao.Notificacao;
import packt.database.DB_Inventario_Stock;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import packt.app.MainConfig.modules.Module_InventarioStock;
import packt.app.views.ModalDialog;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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

    @FXML
    private Button btn_editarDados;

    private DB_Inventario_Stock dbInventarioStock = new DB_Inventario_Stock();

    Notificacao notificacao = new Notificacao();

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

        btn_editarDados.setOnAction(event -> editarItemSelecionado());
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
    private void editarItemSelecionado() {
        Module_InventarioStock itemSelecionado = tab_inventario.getSelectionModel().getSelectedItem();
        Window owner = btn_editarDados.getScene().getWindow();

        if (itemSelecionado != null) {
            Optional<Boolean> resultado = ModalDialog.<EditarInventarioStock, Boolean>showModalWithResult(
                    "editar_inventarioStock",
                    owner,
                    "Editar Item",
                    StageStyle.DECORATED,
                    controller -> {
                        if (controller instanceof EditarInventarioStock) {
                            ((EditarInventarioStock) controller).setItem(itemSelecionado);
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


    @FXML
    private void excluirItem() {
        Module_InventarioStock itemSelecionado = tab_inventario.getSelectionModel().getSelectedItem();
        if (itemSelecionado != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Tem certeza que deseja excluir este item?");
            alert.setHeaderText("Confirmação de Exclusão");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    dbInventarioStock.excluirInventario(itemSelecionado.getCodDep()); // Chama o método de exclusão
                    carregarDados(); // Recarrega os dados após a exclusão
                }
            });
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Selecione um item para excluir.");
            alert.showAndWait();
        }
    }
}