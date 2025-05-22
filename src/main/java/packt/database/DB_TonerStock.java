package packt.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import packt.app.MainConfig.modules.Module_TonerStock;

public class DB_TonerStock {
    private DatabaseConnection dbconnection = new DatabaseConnection();

    // CREATE: Inserir um novo registro na tabela tonerstock
    public void createTonerStock(Module_TonerStock tonerStock) {
        String sql = "INSERT INTO tonerstock (codDep, idProdut, toner, marca, modelo, cor, impressora, dataUsu, status, operador, funcao, localizacao, departamento, obs) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dbconnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tonerStock.getCodDep());
            stmt.setString(2, tonerStock.getIdProdut());
            stmt.setString(3, tonerStock.getToner());
            stmt.setString(4, tonerStock.getMarca());
            stmt.setString(5, tonerStock.getModelo());
            stmt.setString(6, tonerStock.getCor());
            stmt.setString(7, tonerStock.getImpressora());
            stmt.setDate(8, Date.valueOf(tonerStock.getDataUsu())); // Convertendo LocalDate para SQL Date
            stmt.setString(9, tonerStock.getStatus());
            stmt.setString(10, tonerStock.getOperador());
            stmt.setString(11, tonerStock.getFuncao());
            stmt.setString(12, tonerStock.getLocalizacao());
            stmt.setString(13, tonerStock.getDepartamento());
            stmt.setString(14, tonerStock.getObs());

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
                Module_TonerStock tonerStock = new Module_TonerStock();
                tonerStock.setCodDep(rs.getString("codDep"));
                tonerStock.setIdProdut(rs.getString("idProdut"));
                tonerStock.setToner(rs.getString("toner"));
                tonerStock.setMarca(rs.getString("marca"));
                tonerStock.setModelo(rs.getString("modelo"));
                tonerStock.setCor(rs.getString("cor"));
                tonerStock.setImpressora(rs.getString("impressora"));
                tonerStock.setDataUsu(rs.getDate("dataUsu").toLocalDate()); // Convertendo SQL Date para LocalDate
                tonerStock.setStatus(rs.getString("status"));
                tonerStock.setOperador(rs.getString("operador"));
                tonerStock.setFuncao(rs.getString("funcao"));
                tonerStock.setLocalizacao(rs.getString("localizacao"));
                tonerStock.setDepartamento(rs.getString("departamento"));
                tonerStock.setObs(rs.getString("obs"));

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
        String sql = "UPDATE tonerstock SET " +
                "toner = ?, marca = ?, modelo = ?, cor = ?, impressora = ?, " +
                "dataUsu = ?, status = ?, operador = ?, funcao = ?, " +
                "localizacao = ?, departamento = ?, obs = ? " +
                "WHERE codDep = ? AND idProdut = ?";

        try (Connection conn = dbconnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tonerStock.getToner());
            stmt.setString(2, tonerStock.getMarca());
            stmt.setString(3, tonerStock.getModelo());
            stmt.setString(4, tonerStock.getCor());
            stmt.setString(5, tonerStock.getImpressora());
            stmt.setDate(6, Date.valueOf(tonerStock.getDataUsu())); // Convertendo LocalDate para SQL Date
            stmt.setString(7, tonerStock.getStatus());
            stmt.setString(8, tonerStock.getOperador());
            stmt.setString(9, tonerStock.getFuncao());
            stmt.setString(10, tonerStock.getLocalizacao());
            stmt.setString(11, tonerStock.getDepartamento());
            stmt.setString(12, tonerStock.getObs());
            stmt.setString(13, tonerStock.getCodDep());
            stmt.setString(14, tonerStock.getIdProdut());

            int rowsUpdated = stmt.executeUpdate();
            System.out.println(rowsUpdated + " registro(s) atualizado(s).");

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar registro na tabela tonerstock:");
            e.printStackTrace();
        }
    }

    // DELETE: Excluir um registro da tabela tonerstock
    public void deleteTonerStock(String codDep, String idProdut) {
        String sql = "DELETE FROM tonerstock WHERE codDep = ? AND idProdut = ?";

        try (Connection conn = dbconnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, codDep);
            stmt.setString(2, idProdut);

            int rowsDeleted = stmt.executeUpdate();
            System.out.println(rowsDeleted + " registro(s) excluído(s).");

        } catch (SQLException e) {
            System.err.println("Erro ao excluir registro da tabela tonerstock:");
            e.printStackTrace();
        }
    }

    // Método adicional para buscar por idProdut
    public Module_TonerStock findByIdProdut(String idProdut) {
        String sql = "SELECT * FROM tonerstock WHERE idProdut = ?";

        try (Connection conn = dbconnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idProdut);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Module_TonerStock tonerStock = new Module_TonerStock();
                tonerStock.setCodDep(rs.getString("codDep"));
                tonerStock.setIdProdut(rs.getString("idProdut"));
                tonerStock.setToner(rs.getString("toner"));
                tonerStock.setMarca(rs.getString("marca"));
                tonerStock.setModelo(rs.getString("modelo"));
                tonerStock.setCor(rs.getString("cor"));
                tonerStock.setImpressora(rs.getString("impressora"));
                tonerStock.setDataUsu(rs.getDate("dataUsu").toLocalDate());
                tonerStock.setStatus(rs.getString("status"));
                tonerStock.setOperador(rs.getString("operador"));
                tonerStock.setFuncao(rs.getString("funcao"));
                tonerStock.setLocalizacao(rs.getString("localizacao"));
                tonerStock.setDepartamento(rs.getString("departamento"));
                tonerStock.setObs(rs.getString("obs"));

                return tonerStock;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar toner por idProdut:");
            e.printStackTrace();
        }
        return null;
    }

    // Método adicional para buscar por departamento
    public List<Module_TonerStock> findByDepartamento(String departamento) {
        List<Module_TonerStock> tonerStocks = new ArrayList<>();
        String sql = "SELECT * FROM tonerstock WHERE departamento LIKE ?";

        try (Connection conn = dbconnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + departamento + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Module_TonerStock tonerStock = new Module_TonerStock();
                tonerStock.setCodDep(rs.getString("codDep"));
                tonerStock.setIdProdut(rs.getString("idProdut"));
                tonerStock.setToner(rs.getString("toner"));
                tonerStock.setMarca(rs.getString("marca"));
                tonerStock.setModelo(rs.getString("modelo"));
                tonerStock.setCor(rs.getString("cor"));
                tonerStock.setImpressora(rs.getString("impressora"));
                tonerStock.setDataUsu(rs.getDate("dataUsu").toLocalDate());
                tonerStock.setStatus(rs.getString("status"));
                tonerStock.setOperador(rs.getString("operador"));
                tonerStock.setFuncao(rs.getString("funcao"));
                tonerStock.setLocalizacao(rs.getString("localizacao"));
                tonerStock.setDepartamento(rs.getString("departamento"));
                tonerStock.setObs(rs.getString("obs"));

                tonerStocks.add(tonerStock);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar toners por departamento:");
            e.printStackTrace();
        }
        return tonerStocks;
    }
}