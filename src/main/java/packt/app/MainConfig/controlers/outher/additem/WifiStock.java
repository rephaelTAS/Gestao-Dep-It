package packt.app.MainConfig.controlers.outher.additem;

import packt.app.MainConfig.filtragem.Filtro_IdProdut;
import packt.app.MainConfig.filtragem.Filtro_codDep;
import packt.app.MainConfig.notificacao.Notificacao;
import packt.database.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WifiStock {

    @FXML
    private  TextField Id_Produt;
    @FXML
    private TextField codDepField;
    @FXML
    private TextField tipoEquipamentoField;
    @FXML
    private TextField marcaField;
    @FXML
    private TextField quantidadeField;
    @FXML
    private DatePicker dataEntradaField;
    @FXML
    private TextField operadorField;
    @FXML
    private TextField funcaoField;
    @FXML
    private ComboBox<String> statusComboBox;
    @FXML
    private TextField obsField;
    @FXML
    private Button enviarButton;
    @FXML
    private Button limparButton;
    Notificacao notificacao = new Notificacao();

    private void handleEnviar() {
        // Captura os dados dos campos
        String codDep = codDepField.getText();
        String tipoEquipamento = tipoEquipamentoField.getText();
        String marca = marcaField.getText();
        String quantidade = quantidadeField.getText();
        String dataEntrada = dataEntradaField.getValue() != null ? dataEntradaField.getValue().toString() : null;
        String operador = operadorField.getText();
        String funcao = funcaoField.getText();
        String status = statusComboBox.getValue();
        String obs = obsField.getText();

        // Validação simples
        if (codDep.isEmpty() || tipoEquipamento.isEmpty() || marca.isEmpty() || quantidade.isEmpty() || status == null) {
            showAlert("Erro", "Por favor, preencha todos os campos obrigatórios.");
            return;
        }

        // Inserir dados no banco de dados
        try {
            insertData(codDep, tipoEquipamento, marca, Integer.parseInt(quantidade), dataEntrada, operador, funcao, status, obs);
            showAlert("Sucesso", "Dados enviados com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Erro", "Erro ao enviar dados: " + e.getMessage());
        } catch (NumberFormatException e) {
            showAlert("Erro", "Quantidade deve ser um número.");
        }
    }

    private void insertData(String codDep, String tipoEquipamento, String marca, int quantidade, String dataEntrada, String operador, String funcao, String status, String obs) throws SQLException {

        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection = dbConnection.connect();

        // Conexão com o banco de dados

        String sql = "INSERT INTO equipa_wifiStock (cod_dep, tipo_equipamento, marca, quantidade, dataEntrada, operador, funcao, status, obs) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, codDep);
            statement.setString(2, tipoEquipamento);
            statement.setString(3, marca);
            statement.setInt(4, quantidade);
            statement.setString(5, dataEntrada);
            statement.setString(6, operador);
            statement.setString(7, funcao);
            statement.setString(8, status);
            statement.setString(9, obs);
            statement.executeUpdate();
        }

    }

    public void buscarDadosFuncionario(){
        String codigoDep = codDepField.getText();

        Filtro_codDep filtro = new Filtro_codDep();


        if (codigoDep.isEmpty()) {
            showAlert("Erro", "O campo Código do Departamento está vazio.");
            return;
        }else {
            filtro.filtrar_codDep(codigoDep);
            operadorField.setText(filtro.getOperador());
            funcaoField.setText(filtro.getFuncao());
        }
    }



    @FXML
    private void filtrarID_Produt() {
        // Executa ao pressionar Enter
        String idProdutText = Id_Produt.getText();
        Filtro_IdProdut filtroIdProdut = new Filtro_IdProdut();


        if (idProdutText.isEmpty()) {
            notificacao.showError("Erro O campo Código do Departamento está vazio.");

        }else {
            filtroIdProdut.filtrarPorIdProdut(idProdutText);
            tipoEquipamentoField.setText(filtroIdProdut.getTipoEquipamento());
            marcaField.setText(filtroIdProdut.getMarca());
        }

    }

    private void handleLimpar() {
        // Limpa todos os campos
        codDepField.clear();
        tipoEquipamentoField.clear();
        marcaField.clear();
        quantidadeField.clear();
        dataEntradaField.setValue(null);
        operadorField.clear();
        funcaoField.clear();
        statusComboBox.getSelectionModel().clearSelection();
        obsField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void initialize() {
        // Inicialização do ComboBox
        statusComboBox.getItems().addAll("Em Funcionamento", "Ligado", "Desligado");

        // Configuração dos botões
        enviarButton.setOnAction(event -> handleEnviar());
        limparButton.setOnAction(event -> handleLimpar());

        // Adiciona um listener para o campo codDep
        codDepField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) { // Verifica se o campo perdeu o foco
                buscarDadosFuncionario(); // Chama o método para buscar os dados do funcionário
            }
        });

        Id_Produt.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) { // Verifica se o campo perdeu o foco
                filtrarID_Produt(); // Chama o método para buscar os dados do funcionário
            }
        });
    }
}