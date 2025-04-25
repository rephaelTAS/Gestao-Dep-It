package packt.app.views;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Callback;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe utilitária para exibição de diálogos modais integrados com o FXMLManager.
 * Oferece diversas formas de exibir modais com diferentes níveis de controle.
 */
public final class ModalDialog {
    private static final Logger LOGGER = Logger.getLogger(ModalDialog.class.getName());

    private ModalDialog() {
        throw new AssertionError("Classe utilitária não deve ser instanciada");
    }

    /**
     * Exibe um diálogo modal e retorna um resultado processado
     *
     * @param <T> Tipo do controller
     * @param <R> Tipo do resultado
     * @param viewId ID da view registrada no FXMLRegistry
     * @param owner Janela pai (para centralização)
     * @param title Título da janela
     * @param style Estilo da janela
     * @param preShowCallback Callback para configurar o controller antes de exibir
     * @param resultConverter Função para converter o controller em resultado
     * @return Optional contendo o resultado ou vazio se cancelado
     */
    public static <T, R> Optional<R> showModalWithResult(
            String viewId,
            Window owner,
            String title,
            StageStyle style,
            Consumer<T> preShowCallback,
            Function<T, R> resultConverter) {

        try {
            // Validação do viewId
            validateViewId(viewId);

            // Carrega a view e obtém o controller
            Parent root = FXMLManager.loadView(viewId);
            @SuppressWarnings("unchecked")
            T controller = (T) FXMLManager.getController(viewId);

            // Configuração pré-exibição
            if (preShowCallback != null) {
                preShowCallback.accept(controller);
            }

            // Configuração do diálogo
            Stage dialog = createDialog(owner, title, style, root);

            // Exibe e espera
            dialog.showAndWait();

            // Processa o resultado
            return Optional.ofNullable(resultConverter != null ? resultConverter.apply(controller) : null);

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Falha ao abrir diálogo modal: " + viewId, e);
            throw new DialogLoadException("Falha ao carregar diálogo", e);
        }
    }

    /**
     * Versão simplificada de showModalWithResult
     */
    public static <T, R> Optional<R> showModalWithResult(
            String viewId,
            Window owner,
            String title,
            Function<T, R> resultConverter) {

        return showModalWithResult(viewId, owner, title, StageStyle.DECORATED, null, resultConverter);
    }

    /**
     * Exibe um diálogo modal sem retorno de resultado
     *
     * @param viewId ID da view registrada
     * @param owner Janela pai
     * @param title Título da janela
     * @param style Estilo da janela
     * @param preShowCallback Callback para configurar antes de exibir
     */
    public static void showModal(
            String viewId,
            Window owner,
            String title,
            StageStyle style,
            Consumer<Object> preShowCallback) {

        showModalWithResult(viewId, owner, title, style, preShowCallback, c -> null);
    }

    /**
     * Versão simplificada de showModal
     */
    public static void showModal(String viewId, Window owner, String title) {
        showModal(viewId, owner, title, StageStyle.DECORATED, null);
    }

    /**
     * Exibe um diálogo modal com configuração completa
     *
     * @param viewId ID da view registrada
     * @param owner Janela pai
     * @param title Título da janela
     * @param style Estilo da janela
     * @param sceneConfig Configuração adicional da cena
     * @param stageConfig Configuração adicional do stage
     * @param preShowCallback Configuração do controller
     */
    public static void showCustomModal(
            String viewId,
            Window owner,
            String title,
            StageStyle style,
            Consumer<Scene> sceneConfig,
            Consumer<Stage> stageConfig,
            Consumer<Object> preShowCallback) {

        try {
            validateViewId(viewId);

            Parent root = FXMLManager.loadView(viewId);
            Object controller = FXMLManager.getController(viewId);

            if (preShowCallback != null) {
                preShowCallback.accept(controller);
            }

            Stage dialog = createDialog(owner, title, style, root);

            if (sceneConfig != null) {
                sceneConfig.accept(dialog.getScene());
            }

            if (stageConfig != null) {
                stageConfig.accept(dialog);
            }

            dialog.showAndWait();

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Falha ao abrir diálogo customizado: " + viewId, e);
            throw new DialogLoadException("Falha ao carregar diálogo customizado", e);
        }
    }

    /**
     * Cria e configura um diálogo básico
     */
    private static Stage createDialog(Window owner, String title, StageStyle style, Parent root) {
        Stage dialog = new Stage();
        dialog.setTitle(title);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(owner);
        dialog.initStyle(style);
        dialog.setScene(new Scene(root));
        dialog.centerOnScreen();
        return dialog;
    }

    /**
     * Valida se o viewId está registrado
     */
    private static void validateViewId(String viewId) {
        if (!FXMLRegistry.getInstance().isRegistered(viewId)) {
            String errorMsg = "View ID não registrado: " + viewId;
            LOGGER.severe(errorMsg);
            throw new IllegalArgumentException(errorMsg);
        }
    }

    /**
     * Exceção personalizada para erros de carregamento de diálogo
     */
    public static class DialogLoadException extends RuntimeException {
        public DialogLoadException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}