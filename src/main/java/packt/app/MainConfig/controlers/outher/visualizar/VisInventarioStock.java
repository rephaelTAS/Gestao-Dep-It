package packt.app.MainConfig.controlers.outher.visualizar;

import javafx.stage.StageStyle;
import javafx.stage.Window;
import packt.app.MainConfig.controlers.outher.editar.EditarInventario;
import packt.app.MainConfig.modules.Module_Inventario;
import packt.app.MainConfig.notificacao.Notificacao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import packt.app.views.ModalDialog;
import packt.database.DB_Inventario;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;


public class VisInventarioStock implements Initializable {

    @FXML
    private TableView<Module_Inventario> tab_inventario;

    @FXML
    private TableColumn<Module_Inventario, String> colCodDep;

    @FXML
    private TableColumn<Module_Inventario, String> colTipoEquipamento;

    @FXML
    private TableColumn<Module_Inventario, String> colMarca;

    @FXML
    private TableColumn<Module_Inventario, Integer> colQuantidade;

    @FXML
    private TableColumn<Module_Inventario, LocalDate> colDataEntrada;

    @FXML
    private TableColumn<Module_Inventario, LocalDate> colUltimaVerificacao;

    @FXML
    private TableColumn<Module_Inventario, String> colOperador;

    @FXML
    private TableColumn<Module_Inventario, String> colFuncao;

    @FXML
    private TableColumn<Module_Inventario, String> colLocalSala;

    @FXML
    private TableColumn<Module_Inventario, String> colDepartamento;

    @FXML
    private TableColumn<Module_Inventario, String> colSituacaoEquipamento;

    @FXML
    private TableColumn<Module_Inventario, String> colObs;

    @FXML
    private Button btn_editarDados;

    private DB_Inventario dbInventarioStock = new DB_Inventario();

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

        carregarDados();
    }

    private void carregarDados() {
        // Obtém os dados do banco de dados
        List<Module_Inventario> dados = dbInventarioStock.mostrarInventario();

        // Converte a lista para um ObservableList
        ObservableList<Module_Inventario> listaObservavel = FXCollections.observableArrayList(dados);

        // Define os dados na TableView
        tab_inventario.setItems(listaObservavel);
    }

    @FXML
    private void excluirItem() {
        Module_Inventario itemSelecionado = tab_inventario.getSelectionModel().getSelectedItem();
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