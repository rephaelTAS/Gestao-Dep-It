package controler.login;

import app.styles.config.StyleConfig;
import database.DatabaseConnection;
import app.styles.StyleManager;
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
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import notificacao.Notificacao;

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

    @FXML private BorderPane rootPane;

    Notificacao notificacao = new Notificacao();


    private DatabaseConnection dbConnection;



    // Método de ação do botão de login
    public void LoginBtnAction(ActionEvent e) {
        if (!usuario.getText().isBlank() && !senha.getText().isBlank()) {
            validarLogin();
        } else {
            notificacao.showInfo("Campos obrigatórios. Preencha todos os campos.");
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
                    notificacao.showError("Login inválido. Nome de usuário ou senha incorretos.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            notificacao.showError("Erro no banco de dados. Erro ao tentar se conectar ao banco de dados.");
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



    // Método de abrir a tela principal
    public void Main() {


        StyleManager styleManager = StyleManager.getInstance();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/templates/main/main.fxml"));
            AnchorPane root = fxmlLoader.load();

            StyleConfig.init();
            styleManager.applyComponentStyles(root,  "main");

            Stage newStage = new Stage();
            newStage.setTitle("Tela Inicial");
            newStage.setScene(new Scene(root, 556, 556));

            Stage currentStage = (Stage) btn_cancelar.getScene().getWindow();
            currentStage.close();

            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            notificacao.showError("Falha ao abrir a tela principal.");
        }
    }
}