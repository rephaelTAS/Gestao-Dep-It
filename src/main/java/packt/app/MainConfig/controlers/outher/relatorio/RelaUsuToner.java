package packt.app.MainConfig.controlers.outher.relatorio;

import packt.database.DB_UsuToner;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import packt.app.MainConfig.modules.Module_UsuToner;


import java.util.List;

public class RelaUsuToner {

    @FXML
    private TableView<Module_UsuToner> tab_wifiStock;

    @FXML
    private TableColumn<Module_UsuToner, String> colCodDep;

    @FXML
    private TableColumn<Module_UsuToner, String> colToner;

    @FXML
    private TableColumn<Module_UsuToner, String> colMarca;

    @FXML
    private TableColumn<Module_UsuToner, String> colCor;

    @FXML
    private TableColumn<Module_UsuToner, String> colImpressora;

    @FXML
    private TableColumn<Module_UsuToner, Integer> colUnidade;

    @FXML
    private TableColumn<Module_UsuToner, String> colData;

    @FXML
    private TableColumn<Module_UsuToner, String> colOperador;

    @FXML
    private TableColumn<Module_UsuToner, String> colFuncao;

    @FXML
    private TableColumn<Module_UsuToner, String> colLocalizacao;

    @FXML
    private TableColumn<Module_UsuToner, String> colDepartamento;

    @FXML
    private TableColumn<Module_UsuToner, String> colObs;

    // Método inicializador
    @FXML
    public void initialize() {
        // Configura as colunas para usar as propriedades do modelo Module_UsuToner
        colCodDep.setCellValueFactory(new PropertyValueFactory<>("codDep"));
        colToner.setCellValueFactory(new PropertyValueFactory<>("toner"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colCor.setCellValueFactory(new PropertyValueFactory<>("cor"));
        colImpressora.setCellValueFactory(new PropertyValueFactory<>("impressora"));
        colUnidade.setCellValueFactory(new PropertyValueFactory<>("unidade"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colOperador.setCellValueFactory(new PropertyValueFactory<>("operador"));
        colFuncao.setCellValueFactory(new PropertyValueFactory<>("funcao"));
        colLocalizacao.setCellValueFactory(new PropertyValueFactory<>("localizacao"));
        colDepartamento.setCellValueFactory(new PropertyValueFactory<>("departamento"));
        colObs.setCellValueFactory(new  PropertyValueFactory<>("Obs"));

        // Carrega os dados do banco de dados
        loadData();
    }

    // Método para carregar os dados do banco de dados
    private void loadData() {
        DB_UsuToner usuToner = new DB_UsuToner();
        List<Module_UsuToner> toners = usuToner.mostrarDados();
        tab_wifiStock.getItems().addAll(toners);
    }
}