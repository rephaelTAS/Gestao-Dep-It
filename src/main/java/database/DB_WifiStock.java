package database;

import model.Module_WifiStock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DB_WifiStock {

    private DatabaseConnection dbConnection = new DatabaseConnection();

    // Operação CREATE (Inserir)
    public void inserirDadosWifiStock(Module_WifiStock wifiStock) {
        String sql = "INSERT INTO equipa_wifistock (cod_dep, tipo_equipamento, marca, modelo, quantidade, dataEntrada, ultimaVerificacao, operador, situacaoEquipamento, obs) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dbConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Define os valores dos parâmetros
            stmt.setString(1, wifiStock.getCodDep());
            stmt.setString(2, wifiStock.getTipoEquipamento());
            stmt.setString(3, wifiStock.getMarca());
            stmt.setString(4, wifiStock.getModelo());
            stmt.setInt(5, wifiStock.getQuantidade());
            stmt.setDate(6, java.sql.Date.valueOf(wifiStock.getDataEntrada()));
            stmt.setDate(7, java.sql.Date.valueOf(wifiStock.getUltimaVerificacao()));
            stmt.setString(8, wifiStock.getOperador());
            stmt.setString(9, wifiStock.getSituacaoEquipamento());
            stmt.setString(10, wifiStock.getObs());

            // Executa a inserção
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " linha(s) inserida(s).");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir dados na tabela equipa_wifistock:");
            e.printStackTrace();
        }
    }

    // Operação READ (Buscar todos os dados)
    public List<Module_WifiStock> mostrarDadosWifiStock() {
        List<Module_WifiStock> dados = new ArrayList<>();

        String sql = "SELECT * FROM equipa_wifistock";
        try (Connection connection = dbConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Module_WifiStock wifiStock = new Module_WifiStock();
                wifiStock.setCodDep(rs.getString("cod_dep"));
                wifiStock.setTipoEquipamento(rs.getString("tipo_equipamento"));
                wifiStock.setMarca(rs.getString("marca"));
                wifiStock.setModelo(rs.getString("modelo"));
                wifiStock.setQuantidade(rs.getInt("quantidade"));
                wifiStock.setDataEntrada(rs.getDate("dataEntrada") != null ? rs.getDate("dataEntrada").toLocalDate() : null);
                wifiStock.setUltimaVerificacao(rs.getDate("ultimaVerificacao") != null ? rs.getDate("ultimaVerificacao").toLocalDate() : null);
                wifiStock.setOperador(rs.getString("operador"));
                wifiStock.setSituacaoEquipamento(rs.getString("situacaoEquipamento"));
                wifiStock.setObs(rs.getString("obs"));

                dados.add(wifiStock);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar dados da tabela equipa_wifistock:");
            e.printStackTrace();
        }

        return dados;
    }

    // Operação UPDATE (Atualizar)
    public void atualizarDadosWifiStock(Module_WifiStock wifiStock) {
        String sql = "UPDATE equipa_wifistock SET tipo_equipamento = ?, marca = ?, modelo = ?, quantidade = ?, dataEntrada = ?, ultimaVerificacao = ?, operador = ?, situacaoEquipamento = ?, obs = ? WHERE cod_dep = ?";
        try (Connection connection = dbConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Define os valores dos parâmetros
            stmt.setString(1, wifiStock.getTipoEquipamento());
            stmt.setString(2, wifiStock.getMarca());
            stmt.setString(3, wifiStock.getModelo());
            stmt.setInt(4, wifiStock.getQuantidade());
            stmt.setDate(5, java.sql.Date.valueOf(wifiStock.getDataEntrada()));
            stmt.setDate(6, java.sql.Date.valueOf(wifiStock.getUltimaVerificacao()));
            stmt.setString(7, wifiStock.getOperador());
            stmt.setString(8, wifiStock.getSituacaoEquipamento());
            stmt.setString(9, wifiStock.getObs());
            stmt.setString(10, wifiStock.getCodDep());

            // Executa a atualização
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " linha(s) atualizada(s).");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar dados na tabela equipa_wifistock:");
            e.printStackTrace();
        }
    }

    // Operação DELETE (Excluir)
    public void excluirDadosWifiStock(String codDep) {
        String sql = "DELETE FROM equipa_wifistock WHERE cod_dep = ?";
        try (Connection connection = dbConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Define o valor do parâmetro
            stmt.setString(1, codDep);

            // Executa a exclusão
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " linha(s) excluída(s).");
        } catch (SQLException e) {
            System.err.println("Erro ao excluir dados da tabela equipa_wifistock:");
            e.printStackTrace();
        }
    }
}