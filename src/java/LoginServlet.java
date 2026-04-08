import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usuario = request.getParameter("usuario");
        String senha = request.getParameter("senha");

        if ("admin".equals(usuario) && "123".equals(senha)) {

            // cria sessão
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogado", usuario);

            // redireciona pro dashboard
            response.sendRedirect("dashboard.jsp");

        } else {
            // volta pro login
            response.sendRedirect("login.jsp");
        }
    }
}