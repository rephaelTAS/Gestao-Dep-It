package controler.outher.additem;

import database.DatabaseConnection;
import filtragem.Filtro_codDep;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import model.Module_Inventario;
import notificacao.Notificacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Inventario {

    @FXML
    private TextField codDep;

    @FXML
    private TextField tipoEqui;

    @FXML
    private TextField marca;

    @FXML
    private TextField modelo;

    @FXML
    private TextField numSerie;

    @FXML
    private DatePicker dataEntrada;

    @FXML
    private DatePicker dataVerificacao;

    @FXML
    private TextField operador;

    @FXML
    private TextField funcao;

    @FXML
    private TextField localSala;

    @FXML
    private TextField departamento;

    @FXML
    private ComboBox<String> statusCombo;

    @FXML
    private ComboBox<String> situacaoCombo;

    @FXML
    private TextField obs;

    @FXML
    private Button btnEnviar;

    @FXML
    private Button btnLimpar;

    Notificacao notificacao = new Notificacao();




    // Método chamado ao clicar no botão "Enviar"
    @FXML
    public void handleEnviar() {
        // Coleta os dados dos campos
        String codigoDep = codDep.getText();
        String tipoEquipamento = tipoEqui.getText();
        String marcaEquipamento = marca.getText();
        String modeloEquipamento = modelo.getText();
        String numeroSerie = numSerie.getText();
        LocalDate dataEntradaServico = dataEntrada.getValue();
        LocalDate dataUltimaVerificacao = dataVerificacao.getValue();
        String operadorEquipamento = operador.getText();
        String funcaoEquipamento = funcao.getText();
        String localizacaoSala = localSala.getText();
        String departamentoEquipamento = departamento.getText();
        String status = statusCombo.getValue();
        String situacao = situacaoCombo.getValue();
        String observacoes = obs.getText();

        // Validação dos campos obrigatórios
        if (codigoDep.isEmpty() || tipoEquipamento.isEmpty() || marcaEquipamento.isEmpty() || modeloEquipamento.isEmpty() ||
                numeroSerie.isEmpty() || dataEntradaServico == null || operadorEquipamento.isEmpty() || funcaoEquipamento.isEmpty() ||
                localizacaoSala.isEmpty() || departamentoEquipamento.isEmpty() || status == null || situacao == null) {
            notificacao.showAlert("Erro", "Todos os campos obrigatórios devem ser preenchidos!");
            return;
        }else {
            Module_Inventario inventario = new Module_Inventario();
            inventario.module_inventario(codigoDep, tipoEquipamento, marcaEquipamento, modeloEquipamento, numeroSerie, dataEntradaServico, dataUltimaVerificacao, operadorEquipamento, funcaoEquipamento, localizacaoSala, departamentoEquipamento, status, situacao, observacoes);
        }

    }

    // Método para consultar o banco de dados e preencher os campos
    @FXML
    private void buscarDadosFuncionario() {
    // Executa ao pressionar Enter
        String codigoDep = codDep.getText();

        Filtro_codDep filtro = new Filtro_codDep();


        if (codigoDep.isEmpty()) {
            notificacao.showAlert("Erro", "O campo Código do Departamento está vazio.");
            return;
        }else {
            filtro.filtrar_codDep(codigoDep);
            operador.setText(filtro.getOperador());
            funcao.setText(filtro.getFuncao());
            localSala.setText(filtro.getLocalSala());
            departamento.setText(filtro.getDepartamento());
        }

    }

    // Método para limpar os campos relacionados ao funcionário
    private void limparCamposFuncionario() {
        operador.clear();
        funcao.clear();
        localSala.clear();
        departamento.clear();
    }

    // Método para limpar os campos do formulário
    @FXML
    private void limparCampos() {
        codDep.clear();
        tipoEqui.clear();
        marca.clear();
        modelo.clear();
        numSerie.clear();
        dataEntrada.setValue(null);
        dataVerificacao.setValue(null);
        operador.clear();
        funcao.clear();
        localSala.clear();
        departamento.clear();
        statusCombo.getSelectionModel().clearSelection();
        situacaoCombo.getSelectionModel().clearSelection();
        obs.clear();
    }



    // Método para inicializar o controlador (opcional)
    @FXML
    private void initialize() {
        // Preenche as opções dos ComboBox
        statusCombo.getItems().addAll("Em Funcionamento", "Ligado", "Desligado");
        situacaoCombo.getItems().addAll("Novo Equipamento", "Bom Estado", "Com Defeitos", "Avariado");

        // Adiciona um listener para o campo codDep
        codDep.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) { // Verifica se o campo perdeu o foco
                buscarDadosFuncionario(); // Chama o método para buscar os dados do funcionário
            }
        });

        // Configuração do botão "Enviar"
        btnEnviar.setOnAction(event -> handleEnviar());

        // Configuração do botão "Limpar"
        btnLimpar.setOnAction(event -> limparCampos());
    }
}