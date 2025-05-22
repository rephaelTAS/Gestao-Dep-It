package packt.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import packt.app.MainConfig.modules.Module_UsuToner;

public class DB_UsuToner {

    private DatabaseConnection dbConnection = new DatabaseConnection();

    // CREATE: Inserir um novo registro na tabela usutoner
    public void createToner(Module_UsuToner toner) {
        String sql = "INSERT INTO usutoner (codDep, idProdut, toner, marca, cor, impressora, unidade, data, operador, funcao, localizacao, departamento, Obs) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = dbConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, toner.getCodDep());
            stmt.setString(2, toner.getIdProdut()); // Adicionando idProdut
            stmt.setString(3, toner.getToner());
            stmt.setString(4, toner.getMarca());
            stmt.setString(5, toner.getCor());
            stmt.setString(6, toner.getImpressora());
            stmt.setInt(7, toner.getUnidade());
            stmt.setString(8, toner.getData());
            stmt.setString(9, toner.getOperador());
            stmt.setString(10, toner.getFuncao());
            stmt.setString(11, toner.getLocalizacao());
            stmt.setString(12, toner.getDepartamento());
            stmt.setString(13, toner.getObs());

            int rowsInserted = stmt.executeUpdate();
            System.out.println(rowsInserted + " registro(s) inserido(s).");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir registro na tabela usutoner:");
            e.printStackTrace();
        }
    }

    // READ: Consultar todos os registros da tabela usutoner
    public List<Module_UsuToner> mostrarDados() {
        List<Module_UsuToner> toners = new ArrayList<>();
        String sql = "SELECT * FROM usutoner";

        try (Connection conn = dbConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Module_UsuToner toner = new Module_UsuToner(
                        rs.getString("codDep"),
                        rs.getString("idProdut"), // Adicionando idProdut
                        rs.getString("toner"),
                        rs.getString("marca"),
                        rs.getString("cor"),
                        rs.getString("impressora"),
                        rs.getInt("unidade"),
                        rs.getString("data"),
                        rs.getString("operador"),
                        rs.getString("funcao"),
                        rs.getString("localizacao"),
                        rs.getString("departamento"),
                        rs.getString("Obs")
                );
                toners.add(toner);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar registros da tabela usutoner:");
            e.printStackTrace();
        }

        return toners;
    }

    // UPDATE: Atualizar um registro na tabela usutoner
    public void updateToner(Module_UsuToner toner) {
        String sql = "UPDATE usutoner SET idProdut = ?, toner = ?, marca = ?, cor = ?, impressora = ?, unidade = ?, data = ?, operador = ?, funcao = ?, localizacao = ?, departamento = ? WHERE codDep = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, toner.getIdProdut()); // Adicionando idProdut
            stmt.setString(2, toner.getToner());
            stmt.setString(3, toner.getMarca());
            stmt.setString(4, toner.getCor());
            stmt.setString(5, toner.getImpressora());
            stmt.setInt(6, toner.getUnidade());
            stmt.setString(7, toner.getData());
            stmt.setString(8, toner.getOperador());
            stmt.setString(9, toner.getFuncao());
            stmt.setString(10, toner.getLocalizacao());
            stmt.setString(11, toner.getDepartamento());
            stmt.setString(12, toner.getCodDep());

            int rowsUpdated = stmt.executeUpdate();
            System.out.println(rowsUpdated + " registro(s) atualizado(s).");

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar registro na tabela usutoner:");
            e.printStackTrace();
        }
    }

    // DELETE: Excluir um registro da tabela usutoner
    public void deleteToner(String codDep) {
        String sql = "DELETE FROM usutoner WHERE codDep = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, codDep);

            int rowsDeleted = stmt.executeUpdate();
            System.out.println(rowsDeleted + " registro(s) excluído(s).");

        } catch (SQLException e) {
            System.err.println("Erro ao excluir registro da tabela usutoner:");
            e.printStackTrace();
        }
    }
}
