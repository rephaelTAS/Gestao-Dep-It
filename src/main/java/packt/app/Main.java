package packt.app;


import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import packt.app.image.config.ImageConfig;
import packt.app.views.FXMLManager;
import packt.app.views.config.ViewConfig;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Inicializa os estilos e as views
        ViewInitializer.initialize();
        ImageConfig.initialize(ImageConfig.Environment.PROD, ImageConfig.Theme.LIGHT);

        // Carrega a view de login
        Parent root = FXMLManager.loadStaticView(ViewConfig.Main.LOGIN);

        // Cria a cena
        Scene scene = new Scene(root, 650, 550);
        stage.setTitle("Login!");

        // Configura e exibe a cena
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}