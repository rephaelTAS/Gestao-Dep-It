package controler.outher.additem;


import database.DB_Inventario_Stock;
import database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Module_InventarioStock;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

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
            showAlert("Erro", "Todos os campos obrigatórios devem ser preenchidos!");
            return;
        }

        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection = dbConnection.connect();

        String sql = "INSERT INTO inventario_stock (cod_dep, tipo_equipamento, marca, quantidade, data_entrada, data_verificacao, operador, funcao, local_sala, departamento, situacao, obs) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, codigoDep);
            stmt.setString(2, tipoEquipamento);
            stmt.setString(3, marcaEquipamento);
            stmt.setString(4, quantidade);
            stmt.setDate(5, java.sql.Date.valueOf(dataEntradaServico));
            stmt.setDate(6, dataUltimaVerificacao != null ? java.sql.Date.valueOf(dataUltimaVerificacao) : null);
            stmt.setString(7, operadorEquipamento);
            stmt.setString(8, funcaoEquipamento);
            stmt.setString(9, localizacaoSala);
            stmt.setString(10, departamentoEquipamento);
            stmt.setString(11, situacao);
            stmt.setString(12, observacoes);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                showAlert("Sucesso", "Equipamento cadastrado com sucesso!");
                limparCampos();
            } else {
                showAlert("Erro", "Falha ao cadastrar equipamento.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Erro", "Erro ao conectar ao banco de dados.");
        }
    }

    // Método para consultar o banco de dados e preencher os campos
    @FXML
    private void buscarDadosFuncionario() {
        // Executa ao pressionar Enter
        String codigoDep = line_cod_dep.getText();

        if (codigoDep.isEmpty()) {
            showAlert("Erro", "O campo Código do Departamento está vazio.");
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
                showAlert("Aviso", "Nenhum funcionário encontrado com o código informado.");
                limparCamposFuncionario();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Erro", "Erro ao consultar o banco de dados.");
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

    // Método para exibir alertas
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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