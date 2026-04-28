import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.*;
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usuario = request.getParameter("usuario");
        String senha = request.getParameter("senha");

        if (usuario == null || senha == null ||
                usuario.trim().isEmpty() || senha.trim().isEmpty()) {

            response.getWriter().println("Preencha todos os campos!");
            return;
        }

        String usuarioNormalizado = usuario.trim();
        String senhaNormalizada = senha.trim();

        try (Connection conn = ConnectionFactory.getConnection()) {
            ensureSchema(conn);

            try (PreparedStatement check = conn.prepareStatement("SELECT id FROM usuarios WHERE usuario = ?");
                 PreparedStatement stmt = conn.prepareStatement("INSERT INTO usuarios (usuario, senha) VALUES (?, ?)")) {

                check.setString(1, usuarioNormalizado);
                try (ResultSet rs = check.executeQuery()) {
                    if (rs.next()) {
                        response.sendRedirect("register.jsp?error=exists");
                        return;
                    }
                }

                stmt.setString(1, usuarioNormalizado);
                stmt.setString(2, senhaNormalizada);

                stmt.executeUpdate();

                response.sendRedirect("login.jsp?created=1");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("register.jsp?error=db");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("register.jsp?error=server");
        }
    }

    private void ensureSchema(Connection conn) throws SQLException {
        try (Statement statement = conn.createStatement()) {
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS usuarios (" +
                            "id SERIAL PRIMARY KEY, " +
                            "usuario VARCHAR(50) UNIQUE NOT NULL, " +
                            "senha VARCHAR(255) NOT NULL" +
                            ")"
            );
        }
    }
}
