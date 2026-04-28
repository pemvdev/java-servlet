import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usuario = request.getParameter("usuario");
        String senha = request.getParameter("senha");
        
        boolean isValido = false;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT id FROM usuarios WHERE usuario = ? AND senha = ?")) {
             
            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    isValido = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (isValido) {

            // cria sessão
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogado", usuario);

            // redireciona pro dashboard
            response.sendRedirect("dashboard.jsp");

        } else {
            // volta pro login
            response.sendRedirect("login.jsp?error=invalid");
        }
    }
}