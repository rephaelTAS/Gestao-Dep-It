package packt.app.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import java.io.IOException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ModalDialog {
    private static final Logger LOGGER = Logger.getLogger(ModalDialog.class.getName());

    private ModalDialog() {
        throw new AssertionError("Classe utilitária não deve ser instanciada");
    }

    /**
     * Exibe um diálogo modal com tratamento robusto de fechamento
     */
    public static <T, R> Optional<R> showModalWithResult(
            String viewId,
            Window owner,
            String title,
            StageStyle style,
            Consumer<T> preShowCallback,
            Function<T, R> resultConverter) {

        try {
            // Validação do parâmetro viewId
            if (viewId == null || viewId.trim().isEmpty()) {
                throw new IllegalArgumentException("View ID não pode ser nulo ou vazio");
            }

            String viewPath = FXMLManager.getViewPath(viewId);
            if (viewPath == null) {
                throw new IllegalArgumentException("View ID não registrado: " + viewId);
            }

            // Carrega o FXML
            FXMLLoader loader = new FXMLLoader(ModalDialog.class.getResource(viewPath));
            Parent root = loader.load();

            // Configura o controller
            T controller = loader.getController();
            if (preShowCallback != null) {
                preShowCallback.accept(controller);
            }

            // Configura o estágio
            Stage dialogStage = createDialog(owner, title, style, root);

            // Configura o fechamento seguro se implementar a interface
            if (controller instanceof CloseableController) {
                ((CloseableController) controller).setDialogStage(dialogStage);
            }

            // Mostra e espera
            dialogStage.showAndWait();

            // Retorna o resultado processado
            return Optional.ofNullable(resultConverter != null ?
                    resultConverter.apply(controller) : null);

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Falha ao carregar o diálogo: " + viewId, e);
            throw new DialogLoadException("Falha ao exibir o diálogo: " + e.getMessage(), e);
        }
    }

    /**
     * Versão simplificada sem necessidade de retorno
     */
    public static <T> void showModal(
            String viewId,
            Window owner,
            String title,
            StageStyle style,
            Consumer<T> preShowCallback) {

        showModalWithResult(viewId, owner, title, style, preShowCallback, null);
    }

    /**
     * Cria e configura um diálogo modal
     */
    private static Stage createDialog(Window owner, String title, StageStyle style, Parent root) {
        // Verifica se o nó raiz já está em outra cena
        if (root.getParent() != null) {
            throw new IllegalStateException("O nó raiz já está associado a outra cena!");
        }

        Stage dialog = new Stage();
        dialog.setTitle(title);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(owner);
        dialog.initStyle(style);
        dialog.setResizable(false);

        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.centerOnScreen();

        return dialog;
    }

    /**
     * Exceção para erros de carregamento de diálogo
     */
    public static class DialogLoadException extends RuntimeException {
        public DialogLoadException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    /**
     * Interface para controllers que precisam fechar o diálogo
     */
    public interface CloseableController {
        void setDialogStage(Stage stage);
    }
}