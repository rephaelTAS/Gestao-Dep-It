package controler.outher.relatorio;

import database.DB_Inventario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Module_ComDefeitos;
import model.Module_Inventario;

import java.util.List;

public class RelaComDefeitos {

    @FXML
    private TableView<Module_ComDefeitos> tab_comDefeitos;

    @FXML
    private TableColumn<Module_ComDefeitos, String> colCodDep;

    @FXML
    private TableColumn<Module_ComDefeitos, String> colTipoEquipamento;

    @FXML
    private TableColumn<Module_ComDefeitos, String> colMarca;

    @FXML
    private TableColumn<Module_ComDefeitos, String> colNumSerie;

    @FXML
    private TableColumn<Module_ComDefeitos, String> colDataEntrada;

    @FXML
    private TableColumn<Module_ComDefeitos, String> colUltimaVerificacao;

    @FXML
    private TableColumn<Module_ComDefeitos, String> colOperador;

    @FXML
    private TableColumn<Module_ComDefeitos, String> colFuncao;

    @FXML
    private TableColumn<Module_ComDefeitos, String> colLocalSala;

    @FXML
    private TableColumn<Module_ComDefeitos, String> colDepartamento;

    @FXML
    private TableColumn<Module_ComDefeitos, String> colOBS;

    ObservableList<Module_ComDefeitos> equipamentos = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Configura as colunas da TableView
        colCodDep.setCellValueFactory(new PropertyValueFactory<>("codDep"));
        colTipoEquipamento.setCellValueFactory(new PropertyValueFactory<>("tipoEquipamento"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colNumSerie.setCellValueFactory(new PropertyValueFactory<>("numSerie"));
        colDataEntrada.setCellValueFactory(new PropertyValueFactory<>("dataEntrada"));
        colUltimaVerificacao.setCellValueFactory(new PropertyValueFactory<>("ultimaVerificacao"));
        colOperador.setCellValueFactory(new PropertyValueFactory<>("operador"));
        colFuncao.setCellValueFactory(new PropertyValueFactory<>("funcao"));
        colLocalSala.setCellValueFactory(new PropertyValueFactory<>("localSala"));
        colDepartamento.setCellValueFactory(new PropertyValueFactory<>("departamento"));
        colOBS.setCellValueFactory(new PropertyValueFactory<>("OBS"));

        mostrarComDefeitos();

    }



    public void mostrarComDefeitos() {
        // Limpar a lista antes de adicionar novos itens
        equipamentos.clear();

        // Obter os dados do banco de dados
        DB_Inventario dbInventario = new DB_Inventario();
        List<Module_Inventario> inventarios = dbInventario.mostrarInventario();

        // Filtrar os equipamentos com defeitos
        for (Module_Inventario item : inventarios) {
            if ("Com Defeitos".equalsIgnoreCase(item.getSituacaoEquipamento())) {
                // Converter Module_Inventario para Module_ComDefeitos
                Module_ComDefeitos defeito = new Module_ComDefeitos(
                        item.getCodDep(),
                        item.getTipoEquipamento(),
                        item.getMarca(),
                        item.getNum_serie(),
                        item.getDataEntradaServico(),
                        item.getUltimaVerificacao(),
                        item.getOperador(),
                        item.getFuncao(),
                        item.getLocalizacao(),
                        item.getDepartamento(),
                        item.getObs()
                );
                equipamentos.add(defeito);
            }
        }

        // Definir a lista na TableView
        tab_comDefeitos.setItems(equipamentos);
    }

}