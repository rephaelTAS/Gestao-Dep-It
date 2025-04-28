package packt.database;

import packt.app.MainConfig.modules.Module_Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DB_Funcionario {
    private DatabaseConnection dbConnection = new DatabaseConnection();

    // Método para inserir dados na tabela funcionario
    public void inserirFuncionario(Module_Funcionario funcionario) {
        String sql = "INSERT INTO funcionario (cod_dep, nome, funcao, localizacao, departamento) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = dbConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, funcionario.getCodDep());
            stmt.setString(2, funcionario.getNome());
            stmt.setString(3, funcionario.getFuncao());
            stmt.setString(4, funcionario.getLocalizacao());
            stmt.setString(5, funcionario.getDepartamento());

            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " linha(s) inserida(s).");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para buscar todos os dados da tabela funcionario
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
                funcionario.setLocalizacao(rs.getString("localizacao"));
                funcionario.setDepartamento(rs.getString("departamento"));

                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return funcionarios;
    }

    // Método para atualizar dados na tabela funcionario
    public void atualizarFuncionario(Module_Funcionario funcionario, String codDep) {
        String sql = "UPDATE funcionario SET cod_dep = ?, nome = ?, funcao = ?, localizacao = ?, departamento = ? WHERE cod_dep = ?";
        try (Connection connection = dbConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, funcionario.getCodDep());
            stmt.setString(2, funcionario.getNome());
            stmt.setString(3, funcionario.getFuncao());
            stmt.setString(4, funcionario.getLocalizacao());
            stmt.setString(5, funcionario.getDepartamento());
            stmt.setString(6, codDep); // Usando codDep como identificador

            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " linha(s) atualizada(s).");
        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }
}