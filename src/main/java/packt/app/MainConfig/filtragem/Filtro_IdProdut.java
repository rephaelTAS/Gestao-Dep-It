package packt.app.MainConfig.filtragem;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import packt.app.MainConfig.modules.New_Produt;
import packt.database.DB_NewProdut;
import packt.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Filtro_IdProdut {
    private String idProdut;
    private String tipoEquipamento;
    private String marca;
    private String modelo;
    private String obs;

    DB_NewProdut dbNewProdut = new DB_NewProdut();

    public void filtrarPorIdProdut(String idProdut) {
        this.idProdut = idProdut;
        buscarDadosProduto();
    }

    @FXML
    private void buscarDadosProduto() {
        if (idProdut == null || idProdut.isEmpty()) {
            showAlert("Erro", "O campo ID Produto está vazio.");
            return;
        }

        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection = dbConnection.connect();

        String sql = "SELECT tipo_equipamento, marca, modelo, obs FROM produtos WHERE id_produt = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idProdut);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                setTipoEquipamento(rs.getString("tipo_equipamento"));
                setMarca(rs.getString("marca"));
                setModelo(rs.getString("modelo"));
                setObs(rs.getString("obs"));
            } else {
                showAlert("Aviso", "Nenhum produto encontrado com o ID informado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Erro", "Erro ao consultar o banco de dados.");
        }
    }



    // Getters e Setters atualizados
    public String getIdProdut() {
        return idProdut;
    }

    public void setIdProdut(String idProdut) {
        this.idProdut = idProdut;
    }

    public String getTipoEquipamento() {
        return tipoEquipamento;
    }

    public void setTipoEquipamento(String tipoEquipamento) {
        this.tipoEquipamento = tipoEquipamento;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    // Método para exibir alertas padrão corporativo
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public List<New_Produt> buscarProduto(){

        List<New_Produt> lista = new ArrayList<>();
        return lista = dbNewProdut.readAll();
    }
}
