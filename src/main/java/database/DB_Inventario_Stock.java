package database;
import database.DatabaseConnection;
import model.Module_Inventario;
import model.Module_InventarioStock;
import notificacao.Notificacao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB_Inventario_Stock {
    Notificacao notificacao = new Notificacao();

    DatabaseConnection dbConnection = new DatabaseConnection();

    public void inserir_DadosInventarioStock(Module_InventarioStock inventarioStock) {
        String sql = "INSERT INTO inventario_stock (cod_dep, tipo_equipamento, marca, quantidade, data_entrada, data_verificacao, operador, funcao, local_sala, departamento, situacao, obs) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = dbConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, inventarioStock.getCodDep());
            stmt.setString(2, inventarioStock.getTipoEquipamento());
            stmt.setString(3, inventarioStock.getMarca());
            stmt.setInt(4, inventarioStock.getQuantidade()); // Corrigido para int
            stmt.setDate(5, java.sql.Date.valueOf(inventarioStock.getDataEntradaServico()));
            stmt.setDate(6, inventarioStock.getUltimaVerificacao() != null ? java.sql.Date.valueOf(inventarioStock.getUltimaVerificacao()) : null); // Corrigido para 6
            stmt.setString(7, inventarioStock.getOperador());
            stmt.setString(8, inventarioStock.getFuncao());
            stmt.setString(9, inventarioStock.getLocalizacao());
            stmt.setString(10, inventarioStock.getDepartamento());
            stmt.setString(11, inventarioStock.getSituacaoEquipamento());
            stmt.setString(12, inventarioStock.getObs());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                notificacao.showAlert("Sucesso", "Equipamento cadastrado com sucesso!");

            } else {
                notificacao.showAlert("Erro", "Falha ao cadastrar equipamento.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            notificacao.showAlert("Erro", "Erro ao conectar ao banco de dados.");
        }
    }


    public List<Module_InventarioStock> mostrarInventarioStock() {
        List<Module_InventarioStock> inventariosStock = new ArrayList<>();
        String sql = "SELECT * FROM inventario";

        try (Connection connection = dbConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Module_InventarioStock inventarioStock = new Module_InventarioStock();
                inventarioStock.module_InventarioStock(
                        rs.getString("cod_dep"),
                        rs.getString("tipo_equipamento"),
                        rs.getString("marca"),
                        rs.getInt("quantidade"), // Corrigido para int
                        rs.getDate("data_entrada").toLocalDate(),
                        rs.getDate("data_verificacao") != null ? rs.getDate("data_verificacao").toLocalDate() : null,
                        rs.getString("operador"),
                        rs.getString("funcao"),
                        rs.getString("local_sala"),
                        rs.getString("departamento"),
                        rs.getString("situacao"),
                        rs.getString("obs")
                );
                inventariosStock.add(inventarioStock);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return inventariosStock; // Retorna a lista de inventários
    }
}


