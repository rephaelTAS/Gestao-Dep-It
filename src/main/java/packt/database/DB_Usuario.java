package packt.database;
import javafx.scene.image.Image;
import packt.app.MainConfig.modules.Module_Usuario;

import java.io.ByteArrayInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

public class DB_Usuario {

    private final DatabaseConnection dbConnection;
    private String nomeAntigo;

    public DB_Usuario() {
        this.dbConnection = new DatabaseConnection();
    }



    // CREATE
    public boolean criarUsuario(Module_Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome, senha, image) VALUES (?, ?, ?)";

        try (Connection connection = dbConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSenha());

            if (usuario.getImagem() != null) {
                stmt.setBytes(3, usuario.getImagem());
            } else {
                stmt.setNull(3, Types.BLOB);
            }

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        usuario.setId(rs.getInt(1));
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<String> buscarUsuarioPorNome(String nome) {
        List<String> dadosUsuario = new ArrayList<>();
        String sql = "SELECT id, nome, senha, image FROM usuarios WHERE nome = ?";

        try (Connection connection = dbConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, nome.trim());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Adiciona dados básicos como String
                    dadosUsuario.add(String.valueOf(rs.getInt("id")));
                    dadosUsuario.add(rs.getString("nome"));
                    dadosUsuario.add(rs.getString("senha"));

                    // Converte imagem para Base64 (String)
                    byte[] imagemBytes = rs.getBytes("image");
                    String imagemBase64 = (imagemBytes != null) ?
                            Base64.getEncoder().encodeToString(imagemBytes) :
                            "null";
                    dadosUsuario.add(imagemBase64);

                    return dadosUsuario;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário: " + e.getMessage());
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    // UPDATE
    public boolean atualizarUsuario(Module_Usuario usuario) {
        String sql = "UPDATE usuarios SET nome = ?, senha = ?, image = ? WHERE id = ?";

        try (Connection connection = dbConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSenha());

            if (usuario.getImagem() != null) {
                stmt.setBytes(3, usuario.getImagem());
            } else {
                stmt.setNull(3, Types.BLOB);
            }

            stmt.setInt(4, usuario.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // DELETE
    public boolean deletarUsuario(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (Connection connection = dbConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



    // Read: listar todos os nomes da tabela usuariologado
    public List<String> listarUsuarios() {
        List<String> usuarios = new ArrayList<>();
        String sql = "SELECT nome FROM usuariologado";
        try (Connection connection = dbConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                usuarios.add(rs.getString("nome"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    private void buslog(){
        List<String> dados = listarUsuarios();
        nomeAntigo = dados.get(0);

    }

    // Update: atualizar nome na tabela usuariologado
    public boolean atualizarUsuario(String nomeNovo) {
        buslog();
        String sql = "UPDATE usuariologado SET nome = ? WHERE nome = ?";
        try (Connection connection = dbConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nomeNovo);
            stmt.setString(2, nomeAntigo);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<String> buscarUsuario(){
        List<String> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuariologado";
        try (Connection connection = dbConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                usuarios.add(rs.getString("nome"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }
}