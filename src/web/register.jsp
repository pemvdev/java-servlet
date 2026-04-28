<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="UTF-8">
<title>Cadastro</title>

<style>
:root {
    --primary: #4F46E5;
    --bg-start: #0f172a;
    --bg-end: #1e1b4b;
    --glass: rgba(255,255,255,0.05);
    --border: rgba(255,255,255,0.1);
    --text: #f8fafc;
    --muted: #94a3b8;
    --error: #ef4444;
}

body {
    margin: 0;
    font-family: 'Inter', sans-serif;
    background: linear-gradient(135deg, var(--bg-start), var(--bg-end));
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    color: var(--text);
}

.container {
    background: var(--glass);
    padding: 2rem;
    border-radius: 1rem;
    border: 1px solid var(--border);
    backdrop-filter: blur(12px);
    width: 100%;
    max-width: 400px;
}

input {
    width: 100%;
    padding: 0.8rem;
    margin-top: 0.5rem;
    margin-bottom: 1rem;
    background: rgba(0,0,0,0.2);
    border: 1px solid var(--border);
    border-radius: 0.5rem;
    color: white;
}

button {
    width: 100%;
    padding: 0.8rem;
    background: var(--primary);
    border: none;
    color: white;
    border-radius: 0.5rem;
    cursor: pointer;
}

.error {
    color: var(--error);
    margin-bottom: 1rem;
}
</style>

</head>
<body>

<div class="container">

<h2>Criar Conta</h2>

<% if ("exists".equals(request.getParameter("error"))) { %>
<p class="error">Usuário já cadastrado.</p>
<% } else if ("db".equals(request.getParameter("error"))) { %>
<p class="error">Erro no banco de dados.</p>
<% } else if ("server".equals(request.getParameter("error"))) { %>
<p class="error">Erro interno.</p>
<% } %>

<form action="${pageContext.request.contextPath}/register" method="post">

<label>Usuário</label>
<input type="text" name="usuario" required>

<label>Senha</label>
<input type="password" name="senha" required>

<button type="submit">Cadastrar</button>

</form>

<br>

<a href="login.jsp" style="color:#c7d2fe;">Já tem conta? Faça login</a>

</div>

</body>
</html>