package packt.database;

import packt.app.MainConfig.modules.Module_Inventario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DB_HistoUtilizacao {
    private DatabaseConnection dbConnection = new DatabaseConnection();

    /**
     * Método para inserir um novo registro na tabela historico_utilizacao.
     *
     * @param inventario O objeto Module_Inventario contendo os dados a serem inseridos.
     */
    public void inserirHistorico(Module_Inventario inventario) {
        String sql = "INSERT INTO historico_utilizacao (cod_dep, idProdut, tipo_equipamento, marca, modelo, num_serie, data_entrada, data_verificacao, operador, funcao, local_sala, departamento, status, situacao, obs) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


        try (Connection connection = dbConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Define os valores dos parâmetros
            stmt.setString(1, inventario.getCodDep());
            stmt.setString(2, inventario.getIdProdut()); // Adicionando idProdut
            stmt.setString(3, inventario.getTipoEquipamento());
            stmt.setString(4, inventario.getMarca());
            stmt.setString(5, inventario.getModelo());
            stmt.setString(6, inventario.getNum_serie());
            stmt.setDate(7, java.sql.Date.valueOf(inventario.getDataEntradaServico()));
            stmt.setDate(8, inventario.getUltimaVerificacao() != null ? java.sql.Date.valueOf(inventario.getUltimaVerificacao()) : null);
            stmt.setString(9, inventario.getOperador());
            stmt.setString(10, inventario.getFuncao());
            stmt.setString(11, inventario.getLocalizacao());
            stmt.setString(12, inventario.getDepartamento());
            stmt.setString(13, inventario.getStatus());
            stmt.setString(14, inventario.getSituacaoEquipamento());
            stmt.setString(15, inventario.getObs());

            // Executa a inserção
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " linha(s) inserida(s).");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir dados na tabela historico_utilizacao:");
            e.printStackTrace();
        }
    }

    /**
     * Método para buscar todos os registros da tabela historico_utilizacao.
     *
     * @return Uma lista de objetos Module_Inventario com os dados da tabela.
     */
    public List<Module_Inventario> mostrarHistorico() {
        List<Module_Inventario> inventarios = new ArrayList<>();
        String sql = "SELECT * FROM historico_utilizacao";

        try (Connection connection = dbConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Module_Inventario inventario = new Module_Inventario();

                // Preenche o objeto Module_Inventario usando os métodos set
                inventario.setCodDep(rs.getString("cod_dep"));
                inventario.setIdProdut(rs.getString("idProdut")); // Adicionando idProdut
                inventario.setTipoEquipamento(rs.getString("tipo_equipamento"));
                inventario.setMarca(rs.getString("marca"));
                inventario.setModelo(rs.getString("modelo"));
                inventario.setNum_serie(rs.getString("num_serie"));
                inventario.setDataEntradaServico(rs.getDate("data_entrada") != null ? rs.getDate("data_entrada").toLocalDate() : null);
                inventario.setUltimaVerificacao(rs.getDate("data_verificacao") != null ? rs.getDate("data_verificacao").toLocalDate() : null);
                inventario.setOperador(rs.getString("operador"));
                inventario.setFuncao(rs.getString("funcao"));
                inventario.setLocalizacao(rs.getString("local_sala"));
                inventario.setDepartamento(rs.getString("departamento"));
                inventario.setStatus(rs.getString("status"));
                inventario.setSituacaoEquipamento(rs.getString("situacao"));
                inventario.setObs(rs.getString("obs"));

                inventarios.add(inventario);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar dados da tabela historico_utilizacao:");
            e.printStackTrace();
        }

        return inventarios;
    }

    /**
     * Método para atualizar um registro na tabela historico_utilizacao.
     *
     * @param inventario O objeto Module_Inventario contendo os dados a serem atualizados.
     */
    public void atualizarHistorico(Module_Inventario inventario, String numSerie) {
        String sql = "UPDATE historico_utilizacao SET cod_dep = ?, idProdut = ?, tipo_equipamento = ?, marca = ?, modelo = ?, num_serie = ?, data_entrada = ?, data_verificacao = ?, operador = ?, funcao = ?, local_sala = ?, departamento = ?, status = ?, situacao = ?, obs = ? WHERE num_serie = ?";

        try (Connection connection = dbConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Define os valores dos parâmetros
            stmt.setString(1, inventario.getCodDep());
            stmt.setString(2, inventario.getIdProdut()); // Adicionando idProdut
            stmt.setString(3, inventario.getTipoEquipamento());
            stmt.setString(4, inventario.getMarca());
            stmt.setString(5, inventario.getModelo());
            stmt.setString(6, inventario.getNum_serie());
            stmt.setDate(7, java.sql.Date.valueOf(inventario.getDataEntradaServico()));
            stmt.setDate(8, inventario.getUltimaVerificacao() != null ? java.sql.Date.valueOf(inventario.getUltimaVerificacao()) : null);
            stmt.setString(9, inventario.getOperador());
            stmt.setString(10, inventario.getFuncao());
            stmt.setString(11, inventario.getLocalizacao());
            stmt.setString(12, inventario.getDepartamento());
            stmt.setString(13, inventario.getStatus());
            stmt.setString(14, inventario.getSituacaoEquipamento());
            stmt.setString(15, inventario.getObs());
            stmt.setString(16, numSerie); // Usando num_serie como identificador

            // Executa a atualização
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " linha(s) atualizada(s).");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar dados na tabela historico_utilizacao:");
            e.printStackTrace();
        }
    }

    /**
     * Método para deletar um registro da tabela historico_utilizacao.
     *
     * @param numSerie O número de série do equipamento a ser deletado.
     */
    public void deletarHistorico(String numSerie) {
        String sql = "DELETE FROM historico_utilizacao WHERE num_serie = ?";

        try (Connection connection = dbConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, numSerie);

            // Executa a deleção
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " linha(s) deletada(s).");
        } catch (SQLException e) {
            System.err.println("Erro ao deletar dados da tabela historico_utilizacao:");
            e.printStackTrace();
        }
    }
}
