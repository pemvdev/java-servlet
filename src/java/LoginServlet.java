import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usuario = request.getParameter("usuario");
        String senha = request.getParameter("senha");

        if (usuario == null || senha == null || usuario.trim().isEmpty() || senha.isEmpty()) {
            response.sendRedirect("login.jsp?error=invalid");
            return;
        }

        String usuarioNormalizado = usuario.trim();
        String senhaNormalizada = senha.trim();
        boolean usuarioExiste = false;
        boolean senhaCorreta = false;

        try (Connection conn = ConnectionFactory.getConnection()) {
            ensureSchema(conn);
            try (PreparedStatement stmt = conn.prepareStatement("SELECT senha FROM usuarios WHERE usuario = ?")) {

            stmt.setString(1, usuarioNormalizado);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuarioExiste = true;
                    String senhaSalva = rs.getString("senha");
                    senhaCorreta = senhaSalva != null && senhaSalva.equals(senhaNormalizada);
                }
            }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=db");
            return;
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=server");
            return;
        }

        if (usuarioExiste && senhaCorreta) {

            // cria sessão
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogado", usuarioNormalizado);

            // redireciona pro dashboard
            response.sendRedirect("dashboard.jsp");

        } else {
            // volta pro login
            if (!usuarioExiste) {
                response.sendRedirect("login.jsp?error=user-not-found");
            } else {
                response.sendRedirect("login.jsp?error=wrong-password");
            }
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
