package app;

import app.styles.CSSLoader;
import app.styles.StyleManager;
import app.styles.config.StyleConfig;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    // Caminhos de recursos (constantes em maiúsculas)
    private static final String LOGIN_FXML = "/templates/login/login.fxml";
    private static final String APP_ICON = "/icons/app-icon.png";

    @Override
    public void start(Stage stage) throws IOException {
        // 1. Inicializa o gerenciador de estilos
        StyleManager styleManager = StyleManager.getInstance();
        StyleConfig.init();



        // 3. Adiciona temas disponíveis
        styleManager.addTheme("light");
        styleManager.addTheme("dark");

        // 4. Carrega a cena principal
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(LOGIN_FXML));
        Parent root = fxmlLoader.load();

        // 5. Aplica os estilos
        styleManager.applyComponentStyles(root,  "login");

        // 6. Configura a cena
        Scene scene = new Scene(root, 800, 600);

        // 7. Configura o ícone (opcional)
        try {
            stage.getIcons().add(new Image(getClass().getResourceAsStream(APP_ICON)));
        } catch (Exception e) {
            System.err.println("Ícone não encontrado: " + APP_ICON);
        }

        // 8. Configurações finais da janela
        stage.setTitle("Sistema de Gestão");
        stage.setScene(scene);
        stage.setMinWidth(800);
        stage.setMinHeight(600);
        stage.show();
    }

    public static void main(String[] args) {
        // Configuração adicional pode ser feita aqui antes do launch
        launch(args);
    }
}