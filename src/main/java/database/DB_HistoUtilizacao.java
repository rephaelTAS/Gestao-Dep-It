package database;

import model.Module_Inventario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DB_HistoUtilizacao {

    DatabaseConnection dbconection = new DatabaseConnection();

    public void inserir_historico(Module_Inventario inventario){
        String sql = "INSERT INTO historico_utilizacao (cod_dep, tipo_equipamento, marca, modelo, num_serie, data_entrada, data_verificacao, operador, funcao, local_sala, departamento, status, situacao, obs) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection connection = dbconection.connect();
            PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, inventario.getCodDep());
            stmt.setString(2, inventario.getTipoEquipamento());
            stmt.setString(3, inventario.getMarca());
            stmt.setString(4, inventario.getModelo());
            stmt.setString(5, inventario.getNum_serie());
            stmt.setDate(6, java.sql.Date.valueOf(inventario.getDataEntradaServico()));
            stmt.setDate(7, inventario.getUltimaVerificacao() != null ? java.sql.Date.valueOf(inventario.getUltimaVerificacao()) : null);
            stmt.setString(8, inventario.getOperador());
            stmt.setString(9, inventario.getFuncao());
            stmt.setString(10, inventario.getLocalizacao());
            stmt.setString(11, inventario.getDepartamento());
            stmt.setString(12, inventario.getStatus());
            stmt.setString(13, inventario.getSituacaoEquipamento());
            stmt.setString(14, inventario.getObs());

            int rowsAffected = stmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }



    public List<Module_Inventario> mostrarHistorico() {
        List<Module_Inventario> inventarios = new ArrayList<>();
        String sql = "SELECT * FROM historico_utilizacao";

        try (Connection connection = dbconection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Module_Inventario inventario = new Module_Inventario();
                inventario.module_inventario(
                        rs.getString("cod_dep"),
                        rs.getString("tipo_equipamento"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getString("num_serie"),
                        rs.getDate("data_entrada").toLocalDate(),
                        rs.getDate("data_verificacao") != null ? rs.getDate("data_verificacao").toLocalDate() : null,
                        rs.getString("operador"),
                        rs.getString("funcao"),
                        rs.getString("local_sala"),
                        rs.getString("departamento"),
                        rs.getString("status"),
                        rs.getString("situacao"),
                        rs.getString("obs")
                );
                inventarios.add(inventario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return inventarios; // Retorna a lista de inventários
    }
}
