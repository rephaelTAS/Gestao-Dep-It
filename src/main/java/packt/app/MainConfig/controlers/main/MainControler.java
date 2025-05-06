package packt.app.MainConfig.controlers.main;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import packt.app.MainConfig.modules.Module_Funcionario;
import packt.app.MainConfig.modules.Module_Usuario;
import packt.app.MainConfig.notificacao.Notificacao;
import packt.app.image.ImageManager;
import packt.app.image.config.ImageConfig;
import packt.app.views.FXMLManager;
import packt.app.views.config.ViewConfig;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import packt.database.DB_Usuario;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainControler implements Initializable {

    @FXML private VBox sidebar;
    @FXML private ImageView menuIcon;
    @FXML private ImageView dashboardIcon;
    @FXML private ImageView addItemIcon;
    @FXML private ImageView reportIcon;
    @FXML private ImageView viewIcon;
    @FXML private ImageView imageUser ;
    @FXML private Label nameUser;
    @FXML private StackPane multiploPanel;
    @FXML private Button btn_dashboard;
    @FXML private Button btn_additem;
    @FXML private Button btn_relatorio;
    @FXML private Button btn_visualizar;

    private boolean isMenuCollapsed = false;

    private final Notificacao notificacao = new Notificacao();

    private static final Logger LOGGER = Logger.getLogger(MainControler.class.getName());

    // Supondo que você tenha um objeto Module_Funcionario que representa o usuário atual
    private Module_Usuario moduleUsuario;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadView(ViewConfig.Main.DASHBOARD);

        this.moduleUsuario = new Module_Usuario();


        setCurrentUser();


        // Adiciona um evento de clique à imagem do usuário
        imageUser.setOnMouseClicked(event -> showContextMenu());

        // Carrega os ícones
        loadIcons();

        // Configura o menu
        setupMenu();

        btn_dashboard.setOnAction(event -> home());
        btn_additem.setOnAction(event -> add_item());
        btn_relatorio.setOnAction(event -> realotrios());
        btn_visualizar.setOnAction(event -> visualizar());
    }

    public void home() {
        loadView(ViewConfig.Main.DASHBOARD);
    }

    public void add_item() {
        loadView(ViewConfig.Main.ADD_ITEM);
    }

    public void realotrios() {
        loadView(ViewConfig.Main.RELATORIO);
    }

    public void visualizar() {
        loadView(ViewConfig.Main.VISUALIZAR);
    }

    private void loadView(String viewId) {
        try {
            Parent view = FXMLManager.loadStaticView(viewId);
            multiploPanel.getChildren().setAll(view);
        } catch (Exception e) {
            notificacao.showError("Erro ao carregar a view: " + viewId + "\n" + e.getMessage());
        }
    }


    private void loadIcons() {
        try {
            menuIcon.setImage(ImageManager.loadImage(ImageConfig.Images.MENU_ICON));
            dashboardIcon.setImage(ImageManager.loadImage(ImageConfig.Images.DASHBOARD_ICON));
            addItemIcon.setImage(ImageManager.loadImage(ImageConfig.Images.ADD_ITEM_ICON));
            reportIcon.setImage(ImageManager.loadImage(ImageConfig.Images.REPORT_ICON));
            viewIcon.setImage(ImageManager.loadImage(ImageConfig.Images.VIEW_ICON));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupMenu() {
        // Inicializa o menu como expandido
        sidebar.getStyleClass().remove("collapsed");
    }

    @FXML
    private void toggleMenu() {
        isMenuCollapsed = !isMenuCollapsed;

        if (isMenuCollapsed) {
            sidebar.getStyleClass().add("collapsed");
            // Rotaciona o ícone 180 graus
            menuIcon.setRotate(180);
        } else {
            sidebar.getStyleClass().remove("collapsed");
            // Retorna o ícone à posição original
            menuIcon.setRotate(0);
        }
    }


    private void showContextMenu() {
        ContextMenu contextMenu = new ContextMenu();

        MenuItem alterarImagem = new MenuItem("Alterar imagem");
        alterarImagem.setOnAction(event -> alterarImagem());

        MenuItem definicoes = new MenuItem("Definições");
        definicoes.setOnAction(event -> {
            loadView(ViewConfig.Main.DEFINICOES);
        });

        MenuItem sobre = new MenuItem("Sobre");
        sobre.setOnAction(event -> {
            loadView(ViewConfig.Main.SOBRE);
        });

        contextMenu.getItems().addAll(alterarImagem, definicoes, sobre);
        contextMenu.show(imageUser , javafx.geometry.Side.BOTTOM, 0, 0);
    }

    private void alterarImagem() {
        // Verificação robusta do usuário atual
        if (moduleUsuario == null) {
            notificacao.showWarning("Ação não disponível: Nenhum usuário autenticado");
            return;
        }

        // Configuração do FileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecionar Imagem do Perfil");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imagens", "*.png", "*.jpg", "*.jpeg"),
                new FileChooser.ExtensionFilter("Todos os arquivos", "*.*")
        );

        // Exibir diálogo e validar seleção
        File file = fileChooser.showOpenDialog(imageUser .getScene().getWindow());
        if (file == null) {
            return; // Usuário cancelou
        }

        try {
            // Validações do arquivo
            if (!file.exists()) {
                notificacao.showError("Erro: Arquivo não encontrado");
                return;
            }

            if (file.length() > 2 * 1024 * 1024) { // 2MB
                notificacao.showWarning("Imagem muito grande: Selecione uma imagem menor que 2MB");
                return;
            }

            // Carregar a imagem usando o ImageManager
            Image image = ImageManager.loadImage(file.getAbsolutePath(), ImageManager.Source.FILE);

            // Converter a imagem para bytes
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", baos);
            byte[] imageBytes = baos.toByteArray();

            // Atualizar o usuário
            moduleUsuario.setImagem(imageBytes);
            moduleUsuario.atulizarUser(moduleUsuario);
            pegarImagem();

        } catch (IOException e) {
            notificacao.showError("Erro de leitura: Falha ao ler a imagem: " + e.getMessage());
            LOGGER.log(Level.SEVERE, "Erro ao alterar imagem", e);
        } catch (IllegalArgumentException e) {
            notificacao.showError("Formato inválido: A imagem selecionada não é válida");
        }
    }

    // Método auxiliar para criar chave segura
    private String createImageKey(Module_Funcionario funcionario) {
        return "user_img_" + funcionario.getCodDep() + "_" +
                System.currentTimeMillis() + "_" +
                UUID.randomUUID().toString().substring(0, 8);
    }

    public void pegarImagem(){
        // Supondo que imageUser  seja um ImageView
        byte[] imageBytes = moduleUsuario.getImagem();
        if (imageBytes != null && imageBytes.length > 0) {
            // Converter byte[] para Image
            Image image = new Image(new ByteArrayInputStream(imageBytes));
            imageUser.setImage(image);
        } else {
            // Defina uma imagem padrão ou trate o caso em que não há imagem
            imageUser.setImage(null); // ou uma imagem padrão
        }
    }



    public void setCurrentUser () {
        List<String> dados = moduleUsuario.buscaUserLog();
        if (dados.isEmpty()) {
            System.out.println("Nenhum dado encontrado.");
            return;
        }

        List<String> usuarioList = moduleUsuario.userLogado(dados.get(0));
        if (usuarioList.size() < 4) {
            System.out.println("Dados do usuário incompletos.");
            return;
        }

        // Se a imagem estiver em Base64, decodifique-a
        byte[] imageBytes = Base64.getDecoder().decode(usuarioList.get(3));

        moduleUsuario.module_Usuario(
                Integer.valueOf(usuarioList.get(0)),
                usuarioList.get(1),
                usuarioList.get(2),
                imageBytes
        );


        pegarImagem();
        nameUser .setText(moduleUsuario.getNome());
    }

}
