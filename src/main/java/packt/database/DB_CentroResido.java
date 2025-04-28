package packt.database;

import packt.app.MainConfig.modules.Module_CentroRecido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DB_CentroResido {
    private DatabaseConnection dbConnection = new DatabaseConnection();

    // Método para inserir dados na tabela centroResido
    public void inserirHistorico(Module_CentroRecido inventario) {
        String sql = "INSERT INTO centroResido (cod_dep, tipo_equipamento, marca, modelo, num_serie, data_entrada, operador, funcao, local_sala, departamento, obs) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dbConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, inventario.getCodDep());
            stmt.setString(2, inventario.getTipoEquipamento());
            stmt.setString(3, inventario.getMarca());
            stmt.setString(4, inventario.getModelo());
            stmt.setString(5, inventario.getNumSerie());

            // Verifica se a data de entrada não é nula antes de definir
            if (inventario.getDataEntrada() != null) {
                stmt.setDate(6, java.sql.Date.valueOf(inventario.getDataEntrada()));
            } else {
                stmt.setDate(6, null);
            }

            stmt.setString(7, inventario.getOperador());
            stmt.setString(8, inventario.getFuncao());
            stmt.setString(9, inventario.getLocalSala());
            stmt.setString(10, inventario.getDepartamento());
            stmt.setString(11, inventario.getObs());

            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " linha(s) inserida(s).");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para buscar todos os dados da tabela centroResido
    public List<Module_CentroRecido> mostrarDadosCentroResido() {
        List<Module_CentroRecido> inventarios = new ArrayList<>();

        String sql = "SELECT * FROM centroResido";
        try (Connection connection = dbConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Module_CentroRecido inventario = new Module_CentroRecido();
                inventario.setCodDep(rs.getString("cod_dep"));
                inventario.setTipoEquipamento(rs.getString("tipo_equipamento"));
                inventario.setMarca(rs.getString("marca"));
                inventario.setModelo(rs.getString("modelo"));
                inventario.setNumSerie(rs.getString("num_serie"));
                inventario.setDataEntrada(rs.getDate("data_entrada") != null ? rs.getDate("data_entrada").toLocalDate() : null);
                inventario.setOperador(rs.getString("operador"));
                inventario.setFuncao(rs.getString("funcao"));
                inventario.setLocalSala(rs.getString("local_sala"));
                inventario.setDepartamento(rs.getString("departamento"));
                inventario.setObs(rs.getString("obs"));

                inventarios.add(inventario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return inventarios;
    }

    // Método para atualizar dados na tabela centroResido
    public void atualizarCentroResido(Module_CentroRecido inventario) {
        String sql = "UPDATE centroResido SET tipo_equipamento = ?, marca = ?, modelo = ?, num_serie = ?, data_entrada = ?, operador = ?, funcao = ?, local_sala = ?, departamento = ?, obs = ? WHERE cod_dep = ?";
        try (Connection connection = dbConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, inventario.getTipoEquipamento());
            stmt.setString(2, inventario.getMarca());
            stmt.setString(3, inventario.getModelo());
            stmt.setString(4, inventario.getNumSerie());

            // Verifica se a data de entrada não é nula antes de definir
            if (inventario.getDataEntrada() != null) {
                stmt.setDate(5, java.sql.Date.valueOf(inventario.getDataEntrada()));
            } else {
                stmt.setDate(5, null);
            }

            stmt.setString(6, inventario.getOperador());
            stmt.setString(7, inventario.getFuncao());
            stmt.setString(8, inventario.getLocalSala());
            stmt.setString(9, inventario.getDepartamento());
            stmt.setString(10, inventario.getObs());
            stmt.setString(11, inventario.getCodDep());

            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " linha(s) atualizada(s).");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para deletar dados da tabela centroResido
    public void deletarCentroResido(String codDep) {
        String sql = "DELETE FROM centroResido WHERE cod_dep = ?";
        try (Connection connection = dbConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, codDep);

            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " linha(s) deletada(s).");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}