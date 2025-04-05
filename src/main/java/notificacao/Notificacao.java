package notificacao;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.Optional;

import java.net.URL;

public class Notificacao {

    // Configurações de estilo
    private String CSS_PATH = "/css/components/alerts.css";
    private String ICON_ERROR = "/icons/error.png";
    private String ICON_SUCCESS = "/icons/success.png";
    private String ICON_WARNING = "/icons/warning.png";
    private String ICON_INFO = "/icons/info.png";
    private String ICON_CONFIRM = "/icons/accept.png";

    // Método base para criar alertas
    private Alert createAlert(AlertType type, String title, String message, String iconPath) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        // Aplica estilo
        try {
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

            // Carrega CSS
            URL cssUrl = Notificacao.class.getResource(CSS_PATH);
            if (cssUrl != null) {
                stage.getScene().getStylesheets().add(cssUrl.toExternalForm());
            }

            // Carrega ícone
            URL iconUrl = Notificacao.class.getResource(iconPath);
            if (iconUrl != null) {
                stage.getIcons().add(new Image(iconUrl.toString()));
            }

        } catch (Exception e) {
            System.err.println("Erro ao carregar recursos: " + e.getMessage());
        }

        return alert;
    }

    // Notificação de ERRO
    public void showError(String message) {
        Alert alert = createAlert(AlertType.ERROR, "Erro", message, ICON_ERROR);
        alert.showAndWait();
    }

    // Notificação de SUCESSO
    public void showSuccess(String message) {
        Alert alert = createAlert(AlertType.INFORMATION, "Sucesso", message, ICON_SUCCESS);
        alert.showAndWait();
    }

    // Notificação de CONFIRMAÇÃO
    public boolean showConfirmation(String title, String message) {
        Alert alert = createAlert(AlertType.CONFIRMATION, title, message, ICON_CONFIRM);
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    // Notificação de AVISO
    public void showWarning(String message) {
        Alert alert = createAlert(AlertType.WARNING, "Aviso", message, ICON_WARNING);
        alert.show();
    }

    // Notificação de INFORMAÇÃO
    public void showInfo(String message) {
        Alert alert = createAlert(AlertType.INFORMATION, "Informação", message, ICON_INFO);
        alert.showAndWait();
    }

    // Notificação personalizada avançada
    public void showCustomAlert(AlertType type, String title, String header, String message, String iconPath, boolean wait) {
        Alert alert = createAlert(type, title, message, iconPath);
        alert.setHeaderText(header);

        if (wait) {
            alert.showAndWait();
        } else {
            alert.show();
        }
    }
}