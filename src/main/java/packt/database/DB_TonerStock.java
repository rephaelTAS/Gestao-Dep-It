package packt.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import packt.app.MainConfig.modules.Module_TonerStock;

public class DB_TonerStock {
    private DatabaseConnection dbconnection = new DatabaseConnection();

    // CREATE: Inserir um novo registro na tabela tonerstock
    public void createTonerStock(Module_TonerStock tonerStock) {
        String sql = "INSERT INTO tonerstock (codDep, idProdut, toner, marca, cor, impressora, unidade, Status, operador, funcao, localizacao, departamento, Obs) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dbconnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tonerStock.getCodDep());
            stmt.setString(2, tonerStock.getIdProdut()); // Adicionando idProdut
            stmt.setString(3, tonerStock.getToner());
            stmt.setString(4, tonerStock.getMarca());
            stmt.setString(5, tonerStock.getCor());
            stmt.setString(6, tonerStock.getImpressora());
            stmt.setInt(7, tonerStock.getUnidade());
            stmt.setString(8, tonerStock.getStatus());
            stmt.setString(9, tonerStock.getOperador());
            stmt.setString(10, tonerStock.getFuncao());
            stmt.setString(11, tonerStock.getLocalizacao());
            stmt.setString(12, tonerStock.getDepartamento());
            stmt.setString(13, tonerStock.getObs()); // Adicionando o campo Obs

            int rowsInserted = stmt.executeUpdate();
            System.out.println(rowsInserted + " registro(s) inserido(s).");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir registro na tabela tonerstock:");
            e.printStackTrace();
        }
    }

    // READ: Consultar todos os registros da tabela tonerstock
    public List<Module_TonerStock> readAllTonerStocks() {
        List<Module_TonerStock> tonerStocks = new ArrayList<>();
        String sql = "SELECT * FROM tonerstock";

        try (Connection conn = dbconnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Module_TonerStock tonerStock = new Module_TonerStock(
                        rs.getString("codDep"),
                        rs.getString("idProdut"), // Adicionando idProdut
                        rs.getString("toner"),
                        rs.getString("marca"),
                        rs.getString("cor"),
                        rs.getString("impressora"),
                        rs.getInt("unidade"),
                        rs.getString("Status"),
                        rs.getString("operador"),
                        rs.getString("funcao"),
                        rs.getString("localizacao"),
                        rs.getString("departamento"),
                        rs.getString("Obs") // Adicionando o campo Obs
                );
                tonerStocks.add(tonerStock);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar registros da tabela tonerstock:");
            e.printStackTrace();
        }

        return tonerStocks;
    }

    // UPDATE: Atualizar um registro na tabela tonerstock
    public void updateTonerStock(Module_TonerStock tonerStock) {
        String sql = "UPDATE tonerstock SET idProdut = ?, toner = ?, marca = ?, cor = ?, impressora = ?, unidade = ?, Status = ?, operador = ?, funcao = ?, localizacao = ?, departamento = ?, Obs = ? WHERE codDep = ?";

        try (Connection conn = dbconnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tonerStock.getIdProdut()); // Adicionando idProdut
            stmt.setString(2, tonerStock.getToner());
            stmt.setString(3, tonerStock.getMarca());
            stmt.setString(4, tonerStock.getCor());
            stmt.setString(5, tonerStock.getImpressora());
            stmt.setInt(6, tonerStock.getUnidade());
            stmt.setString(7, tonerStock.getStatus());
            stmt.setString(8, tonerStock.getOperador());
            stmt.setString(9, tonerStock.getFuncao());
            stmt.setString(10, tonerStock.getLocalizacao());
            stmt.setString(11, tonerStock.getDepartamento());
            stmt.setString(12, tonerStock.getObs()); // Adicionando o campo Obs
            stmt.setString(13, tonerStock.getCodDep());

            int rowsUpdated = stmt.executeUpdate();
            System.out.println(rowsUpdated + " registro(s) atualizado(s).");

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar registro na tabela tonerstock:");
            e.printStackTrace();
        }
    }

    // DELETE: Excluir um registro da tabela tonerstock
    public void deleteTonerStock(String codDep) {
        String sql = "DELETE FROM tonerstock WHERE codDep = ?";

        try (Connection conn = dbconnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, codDep);

            int rowsDeleted = stmt.executeUpdate();
            System.out.println(rowsDeleted + " registro(s) excluído(s).");

        } catch (SQLException e) {
            System.err.println("Erro ao excluir registro da tabela tonerstock:");
            e.printStackTrace();
        }
    }
}
