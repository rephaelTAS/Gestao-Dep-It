package packt.app.MainConfig.controlers.login;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import packt.app.MainConfig.controlers.main.MainControler;
import packt.app.MainConfig.modules.Module_Funcionario;
import packt.app.MainConfig.modules.Module_Usuario;
import packt.app.image.ImageManager;
import packt.app.image.ImageRegistry;
import packt.app.image.config.ImageConfig;
import packt.app.views.FXMLManager;
import packt.app.views.config.ViewConfig;
import packt.database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Login implements Initializable {
    public ImageView logoView;
    @FXML
    private TextField usuario;
    @FXML
    private PasswordField senha;
    @FXML
    private Button btn_log;
    @FXML
    private Button btn_cancelar;

    Module_Usuario moduleUsuario = new Module_Usuario();

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
                preparedStatement.setString(2, senha.getText());

                ResultSet queryResult = preparedStatement.executeQuery();

                if (queryResult.next() && queryResult.getInt(1) == 1) {

                    moduleUsuario.atualizarUsuario(usuario.getText());
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
            Parent root = FXMLManager.loadStaticView(ViewConfig.Main.MAIN);

            Stage newStage = new Stage();
            newStage.setTitle("Tela Inicial");
            newStage.setScene(new Scene(root, 800, 656));

            Stage currentStage = (Stage) btn_cancelar.getScene().getWindow();
            currentStage.close();

            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Erro", "Falha ao abrir a tela principal.");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            logoView.setImage(ImageManager.loadImage(ImageConfig.Icons.LOGO));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}