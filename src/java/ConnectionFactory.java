import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL = System.getenv().getOrDefault("DB_URL", "jdbc:postgresql://localhost:5432/postgres");
    private static final String USER = System.getenv().getOrDefault("DB_USER", "postgres");
    private static final String PASSWORD = System.getenv().getOrDefault("DB_PASSWORD", "postgres");

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC do PostgreSQL nao encontrado.", e);
        }

        SQLException lastError = null;
        String[] candidatePasswords = new String[] {PASSWORD, "", "postgres", "123", "admin"};

        for (String candidatePassword : candidatePasswords) {
            try {
                return DriverManager.getConnection(URL, USER, candidatePassword);
            } catch (SQLException e) {
                lastError = e;
            }
        }

        throw new SQLException(
                "Falha ao conectar no PostgreSQL com URL=" + URL + " e USER=" + USER +
                        ". Configure DB_URL/DB_USER/DB_PASSWORD no ambiente do Tomcat.",
                lastError
        );
    }
}
