package packt.app.MainConfig.filtragem;

import packt.database.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Filtro_codDep {
    private String codigo;
    private String codDep;
    private String operador;
    private String funcao;
    private String localSala;
    private String departamento;

    public void filtrar_codDep(String codigo) {
        this.codigo = codigo;
        buscarDadosFuncionario();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodDep() {
        return codDep;
    }

    public void setCodDep(String codDep) {
        this.codDep = codDep;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getLocalSala() {
        return localSala;
    }

    public void setLocalSala(String localSala) {
        this.localSala = localSala;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    // Método para consultar o banco de dados e preencher os campos
    @FXML
    private void buscarDadosFuncionario() {
        // Executa ao pressionar Enter
        String codigoDep = getCodigo();

        if (codigoDep.isEmpty()) {
            showAlert("Erro", "O campo Código do Departamento está vazio.");
            return;
        }

        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection = dbConnection.connect();

        String sql = "SELECT nome, funcao, local, departamento FROM funcionarios WHERE cod_dep = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, codigoDep);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                setOperador(rs.getString("nome"));
                setFuncao(rs.getString("funcao"));
                setLocalSala(rs.getString("local"));
                setDepartamento(rs.getString("departamento"));
            } else {
                showAlert("Aviso", "Nenhum funcionário encontrado com o código informado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Erro", "Erro ao consultar o banco de dados.");
        }

    }

    // Método para exibir alertas
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
