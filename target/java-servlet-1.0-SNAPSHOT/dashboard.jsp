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
<html lang="pt-BR">
<head>
<meta charset="UTF-8">
<title>Dashboard</title>

<style>
:root {
    --primary: #4F46E5;
    --bg-start: #0f172a;
    --bg-end: #1e1b4b;
    --glass: rgba(255,255,255,0.05);
    --border: rgba(255,255,255,0.1);
    --text: #f8fafc;
    --muted: #94a3b8;
}

body {
    margin: 0;
    font-family: 'Inter', sans-serif;
    background: linear-gradient(135deg, var(--bg-start), var(--bg-end));
    color: var(--text);
}

.container {
    padding: 2rem;
}

.header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 2rem;
}

.card {
    background: var(--glass);
    border: 1px solid var(--border);
    padding: 1.5rem;
    border-radius: 1rem;
    backdrop-filter: blur(12px);
}

.grid {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
    gap: 1.5rem;
}

.stat {
    font-size: 1.8rem;
    font-weight: bold;
}

.logout {
    color: #c7d2fe;
    text-decoration: none;
}
</style>

</head>
<body>

<div class="container">

    <div class="header">
        <div>
            <h1>Dashboard</h1>
            <p>Bem-vindo, <b><%= usuario %></b></p>
        </div>
        <a class="logout" href="logout">Logout</a>
    </div>

    <div class="grid">
        <div class="card">
            <p class="muted">Vendas Hoje</p>
            <div class="stat">R$ 1.250</div>
        </div>

        <div class="card">
            <p class="muted">Usuários Ativos</p>
            <div class="stat">87</div>
        </div>

        <div class="card">
            <p class="muted">Pedidos</p>
            <div class="stat">32</div>
        </div>

        <div class="card">
            <p class="muted">Taxa de Conversão</p>
            <div class="stat">4.3%</div>
        </div>
    </div>

</div>

</body>
</html>