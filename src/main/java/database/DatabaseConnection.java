package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private Connection connectDB;

    public Connection connect() {
        try {
            // Substitua os valores abaixo pelos seus dados de conexão
            String databaseName = "gestao_it";
            String databaseUser = "root";
            String databasePassword = "@3570_RT.Mysql";
            String url = "jdbc:mysql://localhost:3306/" + databaseName;

            // Carregar o driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Estabelecer a conexão
            connectDB = DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Driver não encontrado.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao conectar ao banco de dados.");
        }
        return connectDB; // Retorna a conexão (pode ser nula se houver erro)
    }

    public void closeConnection() {
        if (connectDB != null) {
            try {
                connectDB.close(); // Fecha a conexão se estiver aberta
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Erro ao fechar a conexão.");
            }
        }
    }
}