<%@ page import="javax.servlet.http.HttpSession" %>
<%
    // verifica sessão
    HttpSession sessionObj = request.getSession(false);

    if (sessionObj == null || sessionObj.getAttribute("usuarioLogado") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String usuario = (String) sessionObj.getAttribute("usuarioLogado");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
</head>
<body>

    <h1>Dashboard</h1>

    <p>Bem-vindo, <b><%= usuario %></b>!</p>

    <hr>

    <a href="logout">Logout</a>

</body>
</html>