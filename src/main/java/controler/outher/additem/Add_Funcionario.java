package controler.outher.additem;

import database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Add_Funcionario {

    @FXML
    private TextField line_cod_dep;

    @FXML
    private TextField line_nome_funcionario;

    @FXML
    private TextField line_funcao;

    @FXML
    private TextField line_local;

    @FXML
    private TextField line_departamento;

    @FXML
    private Button btn_enviar;

    @FXML
    private Button btn_limpar;

    // Método chamado ao clicar no botão "Enviar"
    @FXML
    public void handleEnviar(ActionEvent event) {
        String codDep = line_cod_dep.getText();
        String nome = line_nome_funcionario.getText();
        String funcao = line_funcao.getText();
        String local = line_local.getText();
        String departamento = line_departamento.getText();

        if (codDep.isEmpty() || nome.isEmpty() || funcao.isEmpty() || local.isEmpty() || departamento.isEmpty()) {
            showAlert("Campos obrigatórios", "Por favor, preencha todos os campos.");
            return;
        }

        // Chama o método para inserir os dados no banco de dados
        if (insertFuncionario(codDep, nome, funcao, local, departamento)) {
            showAlert("Sucesso", "Funcionário cadastrado com sucesso!");
            clearFields(); // Limpa os campos após o cadastro
        } else {
            showAlert("Erro", "Erro ao cadastrar funcionário.");
        }
    }

    // Método para inserir funcionário no banco de dados
    private boolean insertFuncionario(String codDep, String nome, String funcao, String local, String departamento) {

        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection = dbConnection.connect();

        String insertSQL = "INSERT INTO funcionarios (cod_dep, nome, funcao, local, departamento) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, codDep);
            preparedStatement.setString(2, nome);
            preparedStatement.setString(3, funcao);
            preparedStatement.setString(4, local);
            preparedStatement.setString(5, departamento);
            preparedStatement.executeUpdate();
            return true; // Retorna verdadeiro se a inserção for bem-sucedida
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Retorna falso se ocorrer um erro
        } finally {
            dbConnection.closeConnection(); // Fecha a conexão
        }
    }

    // Método para limpar os campos de entrada
    private void clearFields() {
        line_cod_dep.clear();
        line_nome_funcionario.clear();
        line_funcao.clear();
        line_local.clear();
        line_departamento.clear();
    }

    // Método para exibir alertas
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Método chamado ao clicar no botão "Limpar"
    @FXML
    public void handleLimpar(ActionEvent event) {
        clearFields(); // Limpa os campos
    }
}