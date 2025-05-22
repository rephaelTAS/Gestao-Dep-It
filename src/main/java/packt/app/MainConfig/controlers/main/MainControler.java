package packt.app.MainConfig.controlers.main;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import packt.app.MainConfig.modules.Module_Usuario;
import packt.app.MainConfig.notificacao.Notificacao;
import packt.app.image.ImageManager;
import packt.app.image.config.ImageConfig;
import packt.app.views.FXMLManager;
import packt.app.views.config.ViewConfig;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainControler implements Initializable {

    // Componentes FXML
    @FXML private Label lblFooter;
    @FXML private VBox sidebar;
    @FXML private ImageView menuIcon;
    @FXML private ImageView dashboardIcon;
    @FXML private ImageView addItemIcon;
    @FXML private ImageView reportIcon;
    @FXML private ImageView viewIcon;
    @FXML private ImageView timeReportIcon;
    @FXML private ImageView imageUser;
    @FXML private Label nameUser;
    @FXML private Label timeLabel;
    @FXML private Label dateLabel;
    @FXML private StackPane multiploPanel;
    @FXML private Button btn_dashboard;
    @FXML private Button btn_additem;
    @FXML private Button btn_relatorio;
    @FXML private Button btn_visualizar;
    @FXML private Button btn_timeReport;

    // Labels do menu (para controle de visibilidade)
    @FXML private Label lblDashboard;
    @FXML private Label lblAddItem;
    @FXML private Label lblRelatorio;
    @FXML private Label lblVisualizar;
    @FXML private Label lblTimeReport;

    // Variáveis de estado
    private boolean isMenuCollapsed = false;
    private Module_Usuario moduleUsuario;
    private final Notificacao notificacao = new Notificacao();
    private static final Logger LOGGER = Logger.getLogger(MainControler.class.getName());

    // Formatadores de data e hora
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            // Configurações iniciais
            setupUser();
            loadIcons();
            setupMenu();
            setupDateTime();
            setupEventHandlers();

            // Carrega a view padrão
            loadView(ViewConfig.Main.DASHBOARD);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro durante a inicialização", e);
            notificacao.showError("Erro crítico durante a inicialização: " + e.getMessage());
        }
    }

    // Configuração do usuário
    private void setupUser() {
        moduleUsuario = new Module_Usuario();
        setCurrentUser();
        imageUser.setOnMouseClicked(event -> showUserContextMenu());
    }

    // Configuração dos ícones
    private void loadIcons() {
        try {
            menuIcon.setImage(ImageManager.loadImage(ImageConfig.Images.MENU_ICON));
            dashboardIcon.setImage(ImageManager.loadImage(ImageConfig.Images.DASHBOARD_ICON));
            addItemIcon.setImage(ImageManager.loadImage(ImageConfig.Images.ADD_ITEM_ICON));
            reportIcon.setImage(ImageManager.loadImage(ImageConfig.Images.REPORT_ICON));
            viewIcon.setImage(ImageManager.loadImage(ImageConfig.Images.VIEW_ICON));
            timeReportIcon.setImage(ImageManager.loadImage(ImageConfig.Images.TIME_REPORT_ICON));
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Erro ao carregar ícones", e);
        }
    }

    // Configuração do menu
    private void setupMenu() {
        sidebar.getStyleClass().remove("collapsed");
    }

    // Configuração da data e hora
    private void setupDateTime() {
        Timeline clock = new Timeline(
                new KeyFrame(Duration.ZERO, e -> updateDateTime()),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    private void updateDateTime() {
        LocalDateTime now = LocalDateTime.now();
        timeLabel.setText(now.format(timeFormatter));
        dateLabel.setText(now.format(dateFormatter));
    }

    // Configuração dos eventos
    private void setupEventHandlers() {
        btn_dashboard.setOnAction(event -> loadView(ViewConfig.Main.DASHBOARD));
        btn_additem.setOnAction(event -> loadView(ViewConfig.Main.ADD_ITEM));
        btn_relatorio.setOnAction(event -> loadView(ViewConfig.Main.RELATORIO));
        btn_visualizar.setOnAction(event -> loadView(ViewConfig.Main.VISUALIZAR));
        btn_timeReport.setOnAction(event -> loadView(ViewConfig.Main.TIME_REPORT));
    }

    // Métodos de navegação
    public void home() { loadView(ViewConfig.Main.DASHBOARD); }
    public void add_item() { loadView(ViewConfig.Main.ADD_ITEM); }
    public void realotrios() { loadView(ViewConfig.Main.RELATORIO); }
    public void visualizar() { loadView(ViewConfig.Main.VISUALIZAR); }
    public void timeReport() { loadView(ViewConfig.Main.TIME_REPORT); }

    // Carregamento de views
    private void loadView(String viewId) {
        try {
            Parent view = FXMLManager.loadStaticView(viewId);
            multiploPanel.getChildren().setAll(view);
        } catch (Exception e) {
            String errorMsg = "Erro ao carregar a view: " + viewId;
            LOGGER.log(Level.SEVERE, errorMsg, e);
            notificacao.showError(errorMsg + "\n" + e.getMessage());
        }
    }

    // Toggle do menu lateral
    @FXML
    private void toggleMenu() {
        isMenuCollapsed = !isMenuCollapsed;

        if (isMenuCollapsed) {
            // Menu retraído
            sidebar.setPrefWidth(70);
            menuIcon.setRotate(180);

            // Esconder textos dos botões
            lblDashboard.setVisible(false);
            lblAddItem.setVisible(false);
            lblRelatorio.setVisible(false);
            lblVisualizar.setVisible(false);
            lblTimeReport.setVisible(false);
        } else {
            // Menu expandido
            sidebar.setPrefWidth(220);
            menuIcon.setRotate(0);

            // Mostrar textos dos botões
            lblDashboard.setVisible(true);
            lblAddItem.setVisible(true);
            lblRelatorio.setVisible(true);
            lblVisualizar.setVisible(true);
            lblTimeReport.setVisible(true);
        }
    }

    // Menu de contexto do usuário
    private void showUserContextMenu() {
        ContextMenu contextMenu = new ContextMenu();

        MenuItem alterarImagem = new MenuItem("Alterar imagem");
        alterarImagem.setOnAction(this::alterarImagem);

        MenuItem definicoes = new MenuItem("Definições");
        definicoes.setOnAction(e -> loadView(ViewConfig.Main.DEFINICOES));

        MenuItem sobre = new MenuItem("Sobre");
        sobre.setOnAction(e -> loadView(ViewConfig.Main.SOBRE));

        contextMenu.getItems().addAll(alterarImagem, definicoes, sobre);
        contextMenu.show(imageUser, javafx.geometry.Side.BOTTOM, 0, 0);
    }

    // Alteração de imagem do usuário
    private void alterarImagem(ActionEvent event) {
        if (moduleUsuario == null) {
            notificacao.showWarning("Nenhum usuário autenticado");
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecionar Imagem do Perfil");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imagens", "*.png", "*.jpg", "*.jpeg"),
                new FileChooser.ExtensionFilter("Todos os arquivos", "*.*")
        );

        File file = fileChooser.showOpenDialog(imageUser.getScene().getWindow());
        if (file == null) return;

        try {
            validateImageFile(file);
            updateUserImage(file);
        } catch (IOException e) {
            handleImageError(e);
        }
    }

    private void validateImageFile(File file) throws IOException {
        if (!file.exists()) {
            throw new IOException("Arquivo não encontrado");
        }
        if (file.length() > 2 * 1024 * 1024) {
            throw new IOException("Imagem muito grande (máximo 2MB)");
        }
    }

    private void updateUserImage(File file) throws IOException {
        Image image = ImageManager.loadImage(file.getAbsolutePath(), ImageManager.Source.FILE);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(javafx.embed.swing.SwingFXUtils.fromFXImage(image, null), "png", baos);

        moduleUsuario.setImagem(baos.toByteArray());
        moduleUsuario.atulizarUser(moduleUsuario);
        loadUserImage();
    }

    private void handleImageError(Exception e) {
        LOGGER.log(Level.WARNING, "Erro ao processar imagem", e);
        notificacao.showError(e.getMessage());
    }

    // Carregar imagem do usuário
    public void loadUserImage() {
        byte[] imageBytes = moduleUsuario.getImagem();
        if (imageBytes != null && imageBytes.length > 0) {
            imageUser.setImage(new Image(new ByteArrayInputStream(imageBytes)));
        } else {
            imageUser.setImage(null); // Ou carregar uma imagem padrão
        }
    }

    // Configurar usuário atual
    public void setCurrentUser() {
        try {
            List<String> dados = moduleUsuario.buscaUserLog();
            if (dados.isEmpty()) {
                LOGGER.warning("Nenhum dado de usuário encontrado");
                return;
            }

            List<String> usuarioList = moduleUsuario.userLogado(dados.get(0));
            if (usuarioList.size() < 4) {
                LOGGER.warning("Dados do usuário incompletos");
                return;
            }

            byte[] imageBytes = Base64.getDecoder().decode(usuarioList.get(3));
            moduleUsuario.module_Usuario(
                    Integer.valueOf(usuarioList.get(0)),
                    usuarioList.get(1),
                    usuarioList.get(2),
                    imageBytes
            );

            loadUserImage();
            nameUser.setText(moduleUsuario.getNome());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao configurar usuário", e);
            notificacao.showError("Erro ao carregar dados do usuário");
        }
    }
}