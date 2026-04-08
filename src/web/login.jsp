<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="card">
        <h2>Entrar</h2>
        <form action="login" method="post">
            <label>Usuário</label>
            <input type="text" name="usuario" placeholder="admin">

            <label>Senha</label>
            <input type="password" name="senha" placeholder="••••••">

            <input type="submit" value="Entrar" class="btn">
        </form>
    </div>
</body>
</html>