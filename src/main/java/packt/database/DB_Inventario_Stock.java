package packt.database;

import packt.app.MainConfig.modules.Module_InventarioStock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DB_Inventario_Stock {

    private DatabaseConnection dbConnection = new DatabaseConnection();

    // Método para buscar todos os dados da tabela inventario_stock
    public List<Module_InventarioStock> mostrarDadosInventarioStock() {
        List<Module_InventarioStock> dados = new ArrayList<>();
        String sql = "SELECT * FROM inventario_stock";

        try (Connection connection = dbConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Module_InventarioStock inventario = new Module_InventarioStock();
                inventario.setCodDep(rs.getString("cod_dep"));
                inventario.setTipoEquipamento(rs.getString("tipo_equipamento"));
                inventario.setMarca(rs.getString("marca"));
                inventario.setQuantidade(rs.getInt("quantidade"));
                inventario.setDataEntradaServico(rs.getDate("data_entrada") != null ? rs.getDate("data_entrada").toLocalDate() : null);
                inventario.setUltimaVerificacao(rs.getDate("data_verificacao") != null ? rs.getDate("data_verificacao").toLocalDate() : null);
                inventario.setOperador(rs.getString("operador"));
                inventario.setFuncao(rs.getString("funcao"));
                inventario.setLocalizacao(rs.getString("local_sala"));
                inventario.setDepartamento(rs.getString("departamento"));
                inventario.setSituacaoEquipamento(rs.getString("situacao"));
                inventario.setObs(rs.getString("obs"));

                dados.add(inventario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dados;
    }

    // Método para inserir dados na tabela inventario_stock
    public void inserir_DadosInventarioStock(Module_InventarioStock inventario) {
        String sql = "INSERT INTO inventario_stock (cod_dep, tipo_equipamento, marca, quantidade, data_entrada, data_verificacao, operador, funcao, local_sala, departamento, situacao, obs) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dbConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, inventario.getCodDep());
            stmt.setString(2, inventario.getTipoEquipamento());
            stmt.setString(3, inventario.getMarca());
            stmt.setInt(4, inventario.getQuantidade());

            // Verifica se a data de entrada não é nula antes de definir
            if (inventario.getDataEntradaServico() != null) {
                stmt.setDate(5, java.sql.Date.valueOf(inventario.getDataEntradaServico()));
            } else {
                stmt.setDate(5, null);
            }

            // Verifica se a data de verificação não é nula antes de definir
            if (inventario.getUltimaVerificacao() != null) {
                stmt.setDate(6, java.sql.Date.valueOf(inventario.getUltimaVerificacao()));
            } else {
                stmt.setDate(6, null);
            }

            stmt.setString(7, inventario.getOperador());
            stmt.setString(8, inventario.getFuncao());
            stmt.setString(9, inventario.getLocalizacao());
            stmt.setString(10, inventario.getDepartamento());
            stmt.setString(11, inventario.getSituacaoEquipamento());
            stmt.setString(12, inventario.getObs());

            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " linha(s) inserida(s).");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para atualizar dados na tabela inventario_stock
    public void atualizarInventarioStock(Module_InventarioStock inventario, String codDep) {
        String sql = "UPDATE inventario_stock SET tipo_equipamento = ?, marca = ?, quantidade = ?, data_entrada = ?, data_verificacao = ?, operador = ?, funcao = ?, local_sala = ?, departamento = ?, situacao = ?, obs = ? WHERE cod_dep = ?";
        try (Connection connection = dbConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt .setString(1, inventario.getTipoEquipamento());
            stmt.setString(2, inventario.getMarca());
            stmt.setInt(3, inventario.getQuantidade());

            // Verifica se a data de entrada não é nula antes de definir
            if (inventario.getDataEntradaServico() != null) {
                stmt.setDate(4, java.sql.Date.valueOf(inventario.getDataEntradaServico()));
            } else {
                stmt.setDate(4, null);
            }

            // Verifica se a data de verificação não é nula antes de definir
            if (inventario.getUltimaVerificacao() != null) {
                stmt.setDate(5, java.sql.Date.valueOf(inventario.getUltimaVerificacao()));
            } else {
                stmt.setDate(5, null);
            }

            stmt.setString(6, inventario.getOperador());
            stmt.setString(7, inventario.getFuncao());
            stmt.setString(8, inventario.getLocalizacao());
            stmt.setString(9, inventario.getDepartamento());
            stmt.setString(10, inventario.getSituacaoEquipamento());
            stmt.setString(11, inventario.getObs());
            stmt.setString(12, codDep);

            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " linha(s) atualizada(s).");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para excluir dados da tabela inventario_stock
    public void excluirInventario(String codDep) {
        String sql = "DELETE FROM inventario_stock WHERE cod_dep = ?";
        try (Connection connection = dbConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, codDep);
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " linha(s) excluída(s).");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}