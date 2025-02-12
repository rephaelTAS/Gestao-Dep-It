package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Connection {
    private Connection connectDB;

    public void connect() {
        try {
            // Substitua os valores abaixo pelos seus dados de conexão
            String url = "jdbc:mysql://localhost:3306/seu_banco_de_dados";
            String user = "seu_usuario";
            String password = "sua_senha";

            // Carregar o driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Estabelecer a conexão
            connectDB = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Driver não encontrado.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao conectar ao banco de dados.");
        }
    }

    public Connection getConnection() {
        return connectDB;
    }
}