package packt.database;

import packt.app.MainConfig.modules.New_Produt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB_NewProdut {

    DatabaseConnection dbConnection = new DatabaseConnection();


    public void create(New_Produt produt) {
        String sql = "INSERT INTO produtos (id_produt, tipo_equipamento, marca, modelo, quantia, data_entrada, obs) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dbConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, produt.getIdProdut());
            stmt.setString(2, produt.getTipoEquipamento());
            stmt.setString(3, produt.getMarca());
            stmt.setString(4, produt.getModelo());
            stmt.setInt(5, produt.getQuantia());
            stmt.setDate(6, Date.valueOf(produt.getDataEntrada()));
            stmt.setString(7, produt.getObs());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<New_Produt> readAll() {
        List<New_Produt> lista = new ArrayList<>();
        String sql = "SELECT * FROM produtos";

        try (Connection connection = dbConnection.connect();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                New_Produt p = new New_Produt(
                        rs.getString("id_produt"),
                        rs.getString("tipo_equipamento"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getInt("quantia"),
                        rs.getDate("data_entrada").toLocalDate(),
                        rs.getString("obs")
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void update(New_Produt produt) {
        String sql = "UPDATE produtos SET tipo_equipamento=?, marca=?, modelo=?, quantia=?, data_entrada=?, obs=? WHERE id_produt=?";

        try (Connection connection = dbConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, produt.getTipoEquipamento());
            stmt.setString(2, produt.getMarca());
            stmt.setString(3, produt.getModelo());
            stmt.setInt(4, produt.getQuantia());
            stmt.setDate(5, Date.valueOf(produt.getDataEntrada()));
            stmt.setString(6, produt.getObs());
            stmt.setString(7, produt.getIdProdut());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String idProdut) {
        String sql = "DELETE FROM produtos WHERE id_produt=?";

        try (Connection connection = dbConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, idProdut);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
