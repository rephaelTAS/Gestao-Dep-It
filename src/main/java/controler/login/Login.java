package controler.login;

import database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    @FXML
    private TextField usuario;

    @FXML
    private PasswordField senha;

    @FXML
    private Button btn_log;

    @FXML
    private Button btn_cancelar;

    private DatabaseConnection dbConnection;

    // Método de ação do botão de login
    public void LoginBtnAction(ActionEvent e) {
        if (!usuario.getText().isBlank() && !senha.getText().isBlank()) {
            validarLogin();
        } else {
            showAlert(AlertType.WARNING, "Campos obrigatórios", "Preencha todos os campos.");
        }
    }

    // Método de fechar a janela
    public void closetBtn(ActionEvent e) {
        Stage stage = (Stage) btn_cancelar.getScene().getWindow();
        stage.close();
    }

    // Valida o login do usuário
    public void validarLogin() {
        dbConnection = new DatabaseConnection();
        Connection connectDB = null;

        try {
            connectDB = dbConnection.connect(); // Estabelece a conexão com o banco de dados

            String verifyLogin = "SELECT count(1) FROM usuarios WHERE nome = ? AND senha = ?";
            try (PreparedStatement preparedStatement = connectDB.prepareStatement(verifyLogin)) {
                preparedStatement.setString(1, usuario.getText());
                preparedStatement.setString(2, senha.getText()); // Sugiro adicionar criptografia aqui

                ResultSet queryResult = preparedStatement.executeQuery();

                if (queryResult.next() && queryResult.getInt(1) == 1) {
                    Main(); // Chama o método para abrir a tela principal
                } else {
                    showAlert(AlertType.ERROR, "Login inválido", "Nome de usuário ou senha incorretos.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Erro no banco de dados", "Erro ao tentar se conectar ao banco de dados.");
        } finally {
            if (connectDB != null) {
                try {
                    connectDB.close(); // Fecha a conexão com o banco de dados
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Exibe uma janela de alerta com a mensagem especificada
    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Método de abrir a tela principal
    public void Main() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/templates/main/main.fxml"));
            AnchorPane root = fxmlLoader.load();

            Stage newStage = new Stage();
            newStage.setTitle("Tela Inicial");
            newStage.setScene(new Scene(root, 556, 556));

            Stage currentStage = (Stage) btn_cancelar.getScene().getWindow();
            currentStage.close();

            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Erro", "Falha ao abrir a tela principal.");
        }
    }
}