package packt.database;

import packt.app.MainConfig.modules.Module_Wifi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DB_Wifi {

    private DatabaseConnection dbConnection = new DatabaseConnection();

    /**
     * Método para buscar todos os dados da tabela equipa_wifi.
     *
     * @return Uma lista de objetos Module_Wifi com os dados da tabela.
     */
    public List<Module_Wifi> mostrarDadosWifi() {
        List<Module_Wifi> dados = new ArrayList<>();
        String sql = "SELECT * FROM equipa_wifi"; // Certifique-se de que o nome da tabela está correto

        try (Connection connection = dbConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Module_Wifi wifi = new Module_Wifi();
                wifi.setCodDep(rs.getString("cod_dep"));
                wifi.setIdProduct(rs.getString("idProdut")); // Adicionando idProdut
                wifi.setTipoEquipamento(rs.getString("tipo_equipamento"));
                wifi.setMarca(rs.getString("marca"));
                wifi.setModelo(rs.getString("modelo"));
                wifi.setQuantidade(rs.getInt("quantidade"));
                wifi.setDataEntrada(rs.getDate("data_entrada") != null ? rs.getDate("data_entrada").toLocalDate() : null);
                wifi.setDataVerificacao(rs.getDate("ultima_verificacao") != null ? rs.getDate("ultima_verificacao").toLocalDate() : null);
                wifi.setOperador(rs.getString("operador"));
                wifi.setFuncao(rs.getString("funcao"));
                wifi.setStatus(rs.getString("status"));
                wifi.setSituacaoEquipamento(rs.getString("situacao"));
                wifi.setObs(rs.getString("obs"));

                dados.add(wifi);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar dados da tabela equipa_wifi:");
            e.printStackTrace();
        }

        return dados;
    }

    /**
     * Método para inserir um novo registro na tabela equipa_wifi.
     *
     * @param wifi O objeto Module_Wifi contendo os dados a serem inseridos.
     */
    public void inserirDadosWifi(Module_Wifi wifi) {
        String sql = "INSERT INTO equipa_wifi (cod_dep, idProdut, tipo_equipamento, marca, modelo, quantidade, data_entrada, ultima_verificacao, operador, funcao, status, situacao, obs) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = dbConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Define os valores dos parâmetros
            stmt.setString(1, wifi.getCodDep());
            stmt.setString(2, wifi.getIdProduct()); // Adicionando idProdut
            stmt.setString(3, wifi.getTipoEquipamento());
            stmt.setString(4, wifi.getMarca());
            stmt.setString(5, wifi.getModelo());
            stmt.setInt(6, wifi.getQuantidade());
            stmt.setDate(7, wifi.getDataEntrada() != null ? java.sql.Date.valueOf(wifi.getDataEntrada()) : null);
            stmt.setDate(8, wifi.getDataVerificacao() != null ? java.sql.Date.valueOf(wifi.getDataVerificacao()) : null);
            stmt.setString(9, wifi.getOperador());
            stmt.setString(10, wifi.getFuncao());
            stmt.setString(11, wifi.getStatus());
            stmt.setString(12, wifi.getSituacaoEquipamento());
            stmt.setString(13, wifi.getObs());

            // Executa a inserção
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " linha(s) inserida(s) com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir dados na tabela equipa_wifi:");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Erro inesperado ao inserir dados:");
            e.printStackTrace();
        }
    }

    /**
     * Método para atualizar um registro na tabela equipa_wifi.
     *
     * @param wifi O objeto Module_Wifi contendo os dados a serem atualizados.
     */
    public void atualizarDadosWifi(Module_Wifi wifi) {
        String sql = "UPDATE equipa_wifi SET tipo_equipamento = ?, marca = ?, modelo = ?, quantidade = ?, data_entrada = ?, ultima_verificacao = ?, operador = ?, funcao = ?, status = ?, situacao = ?, obs = ? WHERE cod_dep = ?";

        try (Connection connection = dbConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, wifi.getTipoEquipamento());
            stmt.setString(2, wifi.getMarca());
            stmt.setString(3, wifi.getModelo());
            stmt.setInt(4, wifi.getQuantidade());
            stmt.setDate(5, wifi.getDataEntrada() != null ? java.sql.Date.valueOf(wifi.getDataEntrada()) : null);
            stmt.setDate(6, wifi.getDataVerificacao() != null ? java.sql.Date.valueOf(wifi.getDataVerificacao()) : null);
            stmt.setString(7, wifi.getOperador());
            stmt.setString(8, wifi.getFuncao());
            stmt.setString(9, wifi.getStatus());
            stmt.setString(10, wifi.getSituacaoEquipamento());
            stmt.setString(11, wifi.getObs());
            stmt.setString(12, wifi.getCodDep());

            int rowsUpdated = stmt.executeUpdate();
            System.out.println(rowsUpdated + " registro(s) atualizado(s) com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar dados na tabela equipa_wifi:");
            e.printStackTrace();
        }
    }

    /**
     * Método para excluir um registro da tabela equipa_wifi.
     *
     * @param codDep O código do departamento do registro a ser excluído.
     */
    public void excluirDadosWifi(String codDep) {
        String sql = "DELETE FROM equipa_wifi WHERE cod_dep = ?";

        try (Connection connection = dbConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, codDep);

            int rowsDeleted = stmt.executeUpdate();
            System.out.println(rowsDeleted + " registro(s) excluído(s) com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao excluir dados da tabela equipa_wifi:");
            e.printStackTrace();
        }
    }
}
