package packt.app.MainConfig.controlers.outher.additem;


import packt.app.MainConfig.notificacao.Notificacao;
import packt.database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import packt.app.MainConfig.modules.Module_InventarioStock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class InventarioStock{

    @FXML private TextField line_cod_dep;
    @FXML private TextField line_tipo_equipamento;
    @FXML private TextField line_marca;
    @FXML private TextField line_quantidade;
    @FXML private DatePicker line_data_entrada;
    @FXML private DatePicker line_ultima_verificacao;
    @FXML private TextField line_operador;
    @FXML private TextField line_funcao;
    @FXML private TextField line_local;
    @FXML private TextField line_departamento;
    @FXML private ComboBox<String> line_situacao;
    @FXML private TextField line_obs;

    Module_InventarioStock inventarioStock = new Module_InventarioStock();

    Notificacao notificacao = new Notificacao();

    // Método chamado ao clicar no botão "Enviar"
    @FXML
    public void handleEnviar(ActionEvent event) {
        // Coleta os dados dos campos
        String codigoDep = line_cod_dep.getText();
        String tipoEquipamento = line_tipo_equipamento.getText();
        String marcaEquipamento = line_marca.getText();
        String quantidade = line_quantidade.getText();
        LocalDate dataEntradaServico = line_data_entrada.getValue();
        LocalDate dataUltimaVerificacao = line_ultima_verificacao.getValue();
        String operadorEquipamento = line_operador.getText();
        String funcaoEquipamento = line_funcao.getText();
        String localizacaoSala = line_local.getText();
        String departamentoEquipamento = line_departamento.getText();
        String situacao = line_situacao.getValue();
        String observacoes = line_obs.getText();

        // Validação dos campos obrigatórios
        if (codigoDep.isEmpty() || tipoEquipamento.isEmpty() || marcaEquipamento.isEmpty() || quantidade.isEmpty() || dataEntradaServico == null || operadorEquipamento.isEmpty() || funcaoEquipamento.isEmpty() ||
                localizacaoSala.isEmpty() || departamentoEquipamento.isEmpty() || situacao == null) {
            notificacao.showError("Erro");
            return;
        }

        inventarioStock.module_InventarioStock(
            codigoDep,
            tipoEquipamento,
            marcaEquipamento,
            Integer.parseInt(quantidade),
            dataEntradaServico,
            dataUltimaVerificacao,
            operadorEquipamento,
            funcaoEquipamento,
            localizacaoSala,
            departamentoEquipamento,
            situacao,
            observacoes
        );
        inventarioStock.cadastrar_inventarioStock();
        notificacao.showSuccess("Cadastrado com Sucesso");
        limparCampos();
    }

    // Método para consultar o banco de dados e preencher os campos
    @FXML
    private void buscarDadosFuncionario() {
        // Executa ao pressionar Enter
        String codigoDep = line_cod_dep.getText();

        if (codigoDep.isEmpty()) {
            notificacao.showError("Erro");
            return;
        }

        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection = dbConnection.connect();

        String sql = "SELECT nome, funcao, local, departamento FROM funcionarios WHERE cod_dep = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, codigoDep);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                line_operador.setText(rs.getString("nome"));
                line_funcao.setText(rs.getString("funcao"));
                line_local.setText(rs.getString("local"));
                line_departamento.setText(rs.getString("departamento"));
            } else {
                notificacao.showSuccess("Aviso. Nenhum funcionário encontrado com o código informado.");
                limparCamposFuncionario();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            notificacao.showError("Erro");
        }

    }

    // Método para limpar os campos relacionados ao funcionário
    private void limparCamposFuncionario() {
        line_operador.clear();
        line_funcao.clear();
        line_local.clear();
        line_departamento.clear();
    }

    // Método para limpar os campos do formulário
    @FXML
    private void limparCampos() {
        line_cod_dep.clear();
        line_tipo_equipamento.clear();
        line_marca.clear();
        line_quantidade.clear();
        line_data_entrada.setValue(null);
        line_ultima_verificacao.setValue(null);
        line_operador.clear();
        line_funcao.clear();
        line_local.clear();
        line_departamento.clear();
        line_situacao.getSelectionModel().clearSelection();

        line_obs.clear();
    }

    // Método para inicializar o controlador (opcional)
    @FXML
    private void initialize() {
        // Preenche as opções dos ComboBox
        line_situacao.getItems().addAll("Ativo", "Inativo", "Manutenção");

        // Adiciona um listener para o campo codDep
        line_cod_dep.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) { // Verifica se o campo perdeu o foco
                buscarDadosFuncionario(); // Chama o método para buscar os dados do funcionário
            }
        });
    }
}