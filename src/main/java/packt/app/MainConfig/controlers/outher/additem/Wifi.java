package packt.app.MainConfig.controlers.outher.additem;

import packt.app.MainConfig.filtragem.Filtro_IdProdut;
import packt.app.MainConfig.filtragem.Filtro_codDep;
import packt.app.MainConfig.notificacao.Notificacao;
import packt.database.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Wifi {

    @FXML
    private TextField Id_Produt;
    @FXML
    private TextField codDepField;
    @FXML
    private TextField tipoEquipamentoField;
    @FXML
    private TextField marcaField;
    @FXML
    private TextField modeloField;
    @FXML
    private TextField quantidadeField;
    @FXML
    private DatePicker dataEntradaField;
    @FXML
    private DatePicker ultimaVerificacaoField;
    @FXML
    private TextField operadorField;
    @FXML
    private TextField funcaoField;
    @FXML
    private ComboBox<String> statusComboBox;
    @FXML
    private ComboBox<String> situacaoComboBox;
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
        String modelo = modeloField.getText();
        String quantidade = quantidadeField.getText();
        String dataEntrada = dataEntradaField.getValue() != null ? dataEntradaField.getValue().toString() : "Não informado";
        String ultimaVerificacao = ultimaVerificacaoField.getValue() != null ? ultimaVerificacaoField.getValue().toString() : "Não informado";
        String operador = operadorField.getText();
        String funcao = funcaoField.getText();
        String status = statusComboBox.getValue();
        String situacao = situacaoComboBox.getValue();
        String obs = obsField.getText();


        // Validação simples
        if (codDep.isEmpty() || tipoEquipamento.isEmpty() || marca.isEmpty() || modelo.isEmpty() || quantidade.isEmpty()) {
            showAlert("Erro", "Por favor, preencha todos os campos obrigatórios.");
            return;
        }

        // Inserir dados no banco de dados
        try {
            insertData(codDep, tipoEquipamento, marca, modelo, Integer.parseInt(quantidade), dataEntrada, ultimaVerificacao, operador, funcao, status, situacao, obs);
            showAlert("Sucesso", "Dados enviados com sucesso!");
            handleLimpar();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Erro", "Erro ao enviar dados: " + e.getMessage());
        } catch (NumberFormatException e) {
            showAlert("Erro", "Quantidade deve ser um número.");
        }
    }



    private void insertData(String codDep, String tipoEquipamento, String marca, String modelo, int quantidade, String dataEntrada, String ultimaVerificacao, String operador, String funcao, String status, String situacao, String obs) throws SQLException {

        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection = dbConnection.connect();

        String sql = "INSERT INTO equipa_wifi (cod_dep, tipo_equipamento, marca, modelo, quantidade, data_entrada, ultima_verificacao, operador, funcao, status, situacao, obs) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        // Conexão com o banco de dados


        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, codDep);
            statement.setString(2, tipoEquipamento);
            statement.setString(3, marca);
            statement.setString(4, modelo);
            statement.setInt(5, quantidade);
            statement.setString(6, dataEntrada);
            statement.setString(7, ultimaVerificacao);
            statement.setString(8, operador);
            statement.setString(9, funcao);
            statement.setString(10, status);
            statement.setString(11, situacao);
            statement.setString(12, obs);
            statement.executeUpdate();
        }

    }


    // Método para consultar o banco de dados e preencher os campos
    @FXML
    private void buscarDadosFuncionario() {
        // Executa ao pressionar Enter
        String codigoDep = codDepField.getText();

        Filtro_codDep filtro = new Filtro_codDep();
        Filtro_IdProdut filtroIdProdut = new Filtro_IdProdut();


        if (codigoDep.isEmpty()) {
            notificacao.showError("Erro O campo Código do Departamento está vazio.");

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
            modeloField.setText(filtroIdProdut.getModelo());
        }

    }

    private void handleLimpar() {
        // Limpa todos os campos
        codDepField.clear();
        tipoEquipamentoField.clear();
        marcaField.clear();
        modeloField.clear();
        quantidadeField.clear();
        dataEntradaField.setValue(null);
        ultimaVerificacaoField.setValue(null);
        operadorField.clear();
        funcaoField.clear();
        statusComboBox.getSelectionModel().clearSelection();
        situacaoComboBox.getSelectionModel().clearSelection();
        obsField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void initialize() {
        // Inicialização dos ComboBoxes com opções
        statusComboBox.getItems().addAll("Ativo", "Inativo");
        situacaoComboBox.getItems().addAll("Funcionando", "Em Manutenção", "Desativado");

        // Configuração do botão "Enviar"
        enviarButton.setOnAction(event -> handleEnviar());

        codDepField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue){
                buscarDadosFuncionario();
            }
        });

        Id_Produt.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue){
                filtrarID_Produt();
            }
        });

        // Configuração do botão "Limpar"
        limparButton.setOnAction(event -> handleLimpar());
    }
}



