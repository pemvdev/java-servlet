<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>

<html>
<head>
    <title>Cadastro</title>
</head>
<body>

<h2>Cadastro de Usuário</h2>

<% if ("exists".equals(request.getParameter("error"))) { %>
<p style="color:#b91c1c;">Usuario ja cadastrado.</p>
<% } else if ("db".equals(request.getParameter("error"))) { %>
<p style="color:#b91c1c;">Erro ao acessar o banco PostgreSQL.</p>
<% } else if ("server".equals(request.getParameter("error"))) { %>
<p style="color:#b91c1c;">Erro interno no servidor.</p>
<% } %>

<form action="${pageContext.request.contextPath}/register" method="post">
<label>Usuario:</label><br>
<input type="text" name="usuario" required><br><br>

<label>Senha:</label><br>
<input type="password" name="senha" required><br><br>

<button type="submit">Cadastrar</button>
</form>

<br>

<a href="login.jsp">Já tem conta? Faça login</a>

</body>
</html>
