package packt.database;

import packt.app.MainConfig.modules.Module_Funcionario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB_Funcionario {
    private final DatabaseConnection dbConnection;


    public DB_Funcionario() {
        this.dbConnection = new DatabaseConnection();
    }

    // Método para inserir dados na tabela funcionario, incluindo imagem
    public void inserirFuncionario(Module_Funcionario funcionario) {
        String sql = "INSERT INTO funcionario (cod_dep, nome, funcao, localizacao, departamento, imagem) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = dbConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, funcionario.getCodDep());
            stmt.setString(2, funcionario.getNome());
            stmt.setString(3, funcionario.getFuncao());
            stmt.setString(4, funcionario.getLocal());
            stmt.setString(5, funcionario.getDepartamento());

            byte[] imagem = funcionario.getFoto();
            if (imagem != null) {
                stmt.setBytes(6, imagem);
            } else {
                stmt.setNull(6, Types.BLOB);
            }

            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " linha(s) inserida(s).");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir funcionário: " + e.getMessage());
        }
    }

    // Método para buscar todos os dados da tabela funcionario, incluindo imagem
    public List<Module_Funcionario> mostrarFuncionarios() {
        List<Module_Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionario";

        try (Connection connection = dbConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Module_Funcionario funcionario = new Module_Funcionario();
                funcionario.setCodDep(rs.getString("cod_dep"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setFuncao(rs.getString("funcao"));
                funcionario.setLocal(rs.getString("localizacao"));
                funcionario.setDepartamento(rs.getString("departamento"));

                Blob blob = rs.getBlob("imagem");
                if (blob != null) {
                    funcionario.setFoto(blob.getBytes(1, (int) blob.length()));
                } else {
                    funcionario.setFoto(null);
                }

                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao mostrar funcionários: " + e.getMessage());
        }

        return funcionarios;
    }

    // Método para atualizar dados na tabela funcionario, incluindo imagem
    public void atualizarFuncionario(Module_Funcionario funcionario, String codDep) {
        String sql = "UPDATE funcionario SET cod_dep = ?, nome = ?, funcao = ?, localizacao = ?, departamento = ?, imagem = ? WHERE cod_dep = ?";
        try (Connection connection = dbConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, funcionario.getCodDep());
            stmt.setString(2, funcionario.getNome());
            stmt.setString(3, funcionario.getFuncao());
            stmt.setString(4, funcionario.getLocal());
            stmt.setString(5, funcionario.getDepartamento());

            byte[] imagem = funcionario.getFoto();
            if (imagem != null) {
                stmt.setBytes(6, imagem);
            } else {
                stmt.setNull(6, Types.BLOB);
            }

            stmt.setString(7, codDep); // Usando codDep como identificador

            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " linha(s) atualizada(s).");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar funcionário: " + e.getMessage());
        }
    }

    // Método para deletar dados da tabela funcionario
    public void deletarFuncionario(String codDep) {
        String sql = "DELETE FROM funcionario WHERE cod_dep = ?";
        try (Connection connection = dbConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, codDep);

            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " linha(s) deletada(s).");
        } catch (SQLException e) {
            System.err.println("Erro ao deletar funcionário: " + e.getMessage());
        }
    }

    public Module_Funcionario buscarFuncionarioPorNome(String nome) {
        String sql = "SELECT cod_dep, nome, funcao, local, departamento, imagem FROM funcionarios WHERE nome = ?";

        try (Connection connection = dbConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Module_Funcionario funcionario = new Module_Funcionario();
                funcionario.setCodDep(rs.getString("cod_dep"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setFuncao(rs.getString("funcao"));
                funcionario.setLocal(rs.getString("local"));
                funcionario.setDepartamento(rs.getString("departamento"));
                funcionario.setFoto(rs.getBytes("imagem")); // Pode ser null

                return funcionario;
            } else {
                System.out.println("Funcionário não encontrado.");
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
