import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Configurações do PostgreSQL
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres"; // Mude 'postgres' para o nome do seu DB
    private static final String USER = "postgres"; // Seu usuário do PostgreSQL
    private static final String PASSWORD = "admin"; // Sua senha do PostgreSQL

    public static Connection getConnection() throws SQLException {
        try {
            // Garante que o driver seja carregado
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC do PostgreSQL não encontrado.", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
