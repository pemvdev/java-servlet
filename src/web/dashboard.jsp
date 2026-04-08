<%@ page import="javax.servlet.http.HttpSession" %>
<%
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
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="dashboard">
        <div class="bento">
            <h1>Dashboard</h1>
            <p>Bem-vindo, <b><%= usuario %></b>!</p>
            <a href="logout" class="btn secondary">Logout</a>
        </div>
    </div>
</body>
</html>