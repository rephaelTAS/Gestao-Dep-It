package packt.app.MainConfig.controlers.outher.relatorio;

import packt.database.DB_Wifi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import packt.app.MainConfig.modules.Module_Wifi;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class RelaWifi implements Initializable {

    @FXML
    private TableView<Module_Wifi> tab_inventario;

    @FXML
    private TableColumn<Module_Wifi, String> colCodDep;

    @FXML
    private TableColumn<Module_Wifi, String> colTipoEquipamento;

    @FXML
    private TableColumn<Module_Wifi, String> colMarca;

    @FXML
    private TableColumn<Module_Wifi, String> colModelo;

    @FXML
    private TableColumn<Module_Wifi, String> colNumSerie;

    @FXML
    private TableColumn<Module_Wifi, LocalDate> colDataEntrada;

    @FXML
    private TableColumn<Module_Wifi, LocalDate> colDataVerificacao;

    @FXML
    private TableColumn<Module_Wifi, String> colOperador;

    @FXML
    private TableColumn<Module_Wifi, String> colFuncao;

    @FXML
    private TableColumn<Module_Wifi, String> colStatus;

    @FXML
    private TableColumn<Module_Wifi, String> colSituacao;

    @FXML
    private TableColumn<Module_Wifi, String> colObs;

    private DB_Wifi dbWifi = new DB_Wifi();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colSituacao.setCellValueFactory(new PropertyValueFactory<>("situacaoEquipamento"));
        colObs.setCellValueFactory(new PropertyValueFactory<>("obs"));

        // Carrega os dados do banco de dados
        carregarDados();
    }

    private void carregarDados() {
        // Obtém os dados do banco de dados
        List<Module_Wifi> dados = dbWifi.mostrarDadosWifi();

        // Converte a lista para um ObservableList
        ObservableList<Module_Wifi> listaObservavel = FXCollections.observableArrayList(dados);

        // Define os dados na TableView
        tab_inventario.setItems(listaObservavel);
    }
}