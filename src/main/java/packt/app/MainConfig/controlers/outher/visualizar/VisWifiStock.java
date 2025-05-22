package packt.app.MainConfig.controlers.outher.visualizar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import packt.app.MainConfig.modules.Module_Wifi;
import packt.database.DB_Wifi;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class VisWifiStock implements Initializable {

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
    private TableColumn<Module_Wifi, Integer> colNumSerie;

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

    // Converte a lista para um ObservableList
    ObservableList<Module_Wifi> listaObservavel = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configura as colunas da TableView
        colCodDep.setCellValueFactory(new PropertyValueFactory<>("codDep"));
        colTipoEquipamento.setCellValueFactory(new PropertyValueFactory<>("tipoEquipamento"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colNumSerie.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colDataEntrada.setCellValueFactory(new PropertyValueFactory<>("dataEntrada"));
        colDataVerificacao.setCellValueFactory(new PropertyValueFactory<>("ultimaVerificacao"));
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
        DB_Wifi dbWifiStock = new DB_Wifi();
        List<Module_Wifi> dados = dbWifiStock.mostrarDadosWifi();


        listaObservavel.clear();
        listaObservavel.addAll(dados);
        // Define os dados na TableView
        tab_inventario.setItems(listaObservavel);
    }
}
