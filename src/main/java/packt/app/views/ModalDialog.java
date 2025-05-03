package packt.app.views;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe utilitária para exibição de diálogos modais com integração total com FXMLManager.
 * Oferece:
 * - Carregamento seguro de views
 * - Controle de ciclo de vida
 * - Prevenção de vazamentos de recursos
 * - Integração com sistema de estilos
 */
public final class ModalDialog {
    private static final Logger LOGGER = Logger.getLogger(ModalDialog.class.getName());

    private ModalDialog() {
        throw new AssertionError("Classe utilitária não deve ser instanciada");
    }

    /**
     * Exibe um diálogo modal com tratamento robusto de fechamento
     *
     * @param <T> Tipo do controller
     * @param <R> Tipo do resultado
     * @param viewId ID da view registrada no FXMLRegistry
     * @param owner Janela pai (pode ser null)
     * @param title Título da janela
     * @param style Estilo da janela
     * @param preShowCallback Configuração inicial do controller
     * @param resultConverter Conversor de resultado
     * @return Optional com o resultado ou vazio se cancelado
     */
    public static <T, R> Optional<R> showModalWithResult(
            String viewId,
            Window owner,
            String title,
            StageStyle style,
            Consumer<T> preShowCallback,
            Function<T, R> resultConverter) {

        try {
            // Carrega uma nova instância única da view
            Parent root = FXMLManager.loadUniqueView(viewId);

            // Obtém o controller
            T controller = FXMLManager.getController(viewId);

            // Configuração pré-exibição
            if (preShowCallback != null) {
                preShowCallback.accept(controller);
            }

            // Cria e configura o diálogo
            Stage dialogStage = createDialog(owner, title, style, root);

            // Configura o fechamento seguro
            if (controller instanceof CloseableController) {
                ((CloseableController) controller).setDialogStage(dialogStage);
            }

            // Gerencia o ciclo de vida
            dialogStage.setOnHidden(e -> FXMLManager.releaseView(viewId));

            // Exibe e espera
            dialogStage.showAndWait();

            // Processa o resultado
            return Optional.ofNullable(resultConverter != null ?
                    resultConverter.apply(controller) : null);

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Falha ao exibir diálogo modal: " + viewId, e);
            FXMLManager.releaseView(viewId); // Limpeza em caso de erro
            throw new DialogLoadException("Falha ao exibir diálogo: " + e.getMessage(), e);
        }
    }

    /**
     * Versão simplificada sem retorno
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
     * Cria e configura um diálogo modal com validações
     */
    private static Stage createDialog(Window owner, String title, StageStyle style, Parent root) {
        if (root == null) {
            throw new IllegalArgumentException("Nó raiz não pode ser nulo");
        }

        Stage dialog = new Stage();
        dialog.setTitle(title != null ? title : "");
        dialog.initModality(Modality.APPLICATION_MODAL);

        if (owner != null) {
            dialog.initOwner(owner);
        }

        if (style != null) {
            dialog.initStyle(style);
        }

        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.setResizable(false);
        dialog.centerOnScreen();

        return dialog;
    }

    /**
     * Exceção especializada para erros de diálogo
     */
    public static class DialogLoadException extends RuntimeException {
        public DialogLoadException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    /**
     * Interface para controle de fechamento seguro
     */
    public interface CloseableController {
        void setDialogStage(Stage stage);
    }
}