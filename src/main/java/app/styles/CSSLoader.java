package app.styles;

import javafx.scene.Parent;
import notificacao.Notificacao;
import java.net.URL;

/**
 * Carrega arquivos CSS de forma robusta com notificação de erros
 */
public class CSSLoader {
    private static final Notificacao notificacao = new Notificacao();
    private static final String[] CLASS_PATH_BASES = {
            "/css/",
            "/styles/",
            "/view/css/",
            "/fxml/css/"
    };

    /**
     * Carrega um arquivo CSS em um nó Parent
     * @param node Nó JavaFX que receberá o CSS
     * @param cssPath Caminho relativo do arquivo CSS
     */
    public static void loadCSS(Parent node, String cssPath) {
        String fullPath = findCSS(cssPath);
        if (fullPath != null && !node.getStylesheets().contains(fullPath)) {
            node.getStylesheets().add(fullPath);
        }
    }

    /**
     * Localiza um arquivo CSS no classpath
     * @param relativePath Caminho relativo do CSS
     * @return URL completo do arquivo ou null se não encontrado
     */
    private static String findCSS(String relativePath) {
        // 1. Tenta o caminho exato fornecido
        URL cssUrl = CSSLoader.class.getResource(relativePath);
        if (cssUrl != null) {
            return cssUrl.toExternalForm();
        }

        // 2. Tenta com os caminhos base pré-definidos
        for (String base : CLASS_PATH_BASES) {
            String path = base.endsWith("/") ? base + relativePath : base + "/" + relativePath;
            cssUrl = CSSLoader.class.getResource(path);
            if (cssUrl != null) {
                return cssUrl.toExternalForm();
            }
        }

        // 3. Mostra notificação de erro
        showNotFoundError(relativePath);
        return null;
    }

    /**
     * Carrega os estilos padrão do sistema
     * @param node Nó que receberá os estilos
     */
    public static void loadDefaultStyles(Parent node) {
        loadCSS(node, "/css/core/main.css");
        loadCSS(node, "/css/core/variables.css");
        loadCSS(node, "/css/core/typography.css");
    }

    /**
     * Exibe notificação de arquivo CSS não encontrado
     * @param relativePath Caminho do arquivo não encontrado
     */
    private static void showNotFoundError(String relativePath) {
        StringBuilder message = new StringBuilder();
        message.append("Arquivo CSS não encontrado: ").append(relativePath).append("\n\n");
        message.append("Locais pesquisados:\n");
        message.append("- ").append(relativePath).append("\n");

        for (String base : CLASS_PATH_BASES) {
            message.append("- ").append(base).append(relativePath).append("\n");
        }

        notificacao.showError(message.toString());
    }
}