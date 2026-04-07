# java-servlet

🔐 Sistema de Login com Sessão (Servlet + JSP)

Este projeto é um exemplo simples de autenticação utilizando Java Servlet + JSP, seguindo um estilo legado (sem frameworks modernos).

📌 Objetivo

Demonstrar como funciona:

HttpSession
Controle de acesso
Redirecionamento entre páginas
Autenticação básica
🧱 Tecnologias utilizadas
Java
Servlet API
JSP (JavaServer Pages)
Apache Tomcat
HTML
📁 Estrutura do projeto
/src
  ├── LoginServlet.java
  ├── LogoutServlet.java

/web
  ├── login.jsp
  ├── dashboard.jsp
  ├── erro.jsp
  └── WEB-INF
        └── web.xml
🔐 Funcionalidades
✔ Tela de Login
Formulário com usuário e senha
Envio via método POST
✔ Validação
Autenticação simples (hardcoded)
Usuário: admin
Senha: 123
✔ Sessão (HttpSession)
Criação de sessão ao logar
Armazenamento do usuário logado
✔ Página protegida (Dashboard)
Acesso permitido apenas se estiver logado
Redirecionamento automático caso não esteja autenticado
✔ Logout
Invalidação da sessão
Retorno para tela de login
🔁 Fluxo da aplicação
Usuário acessa login.jsp
Envia credenciais
LoginServlet valida:
✔ válido → cria sessão → redireciona para dashboard
❌ inválido → retorna para login com erro
dashboard.jsp verifica sessão:
✔ existe → mostra conteúdo
❌ não existe → redireciona para login
Logout destrói a sessão
🧠 Como funciona a sessão

Quando o usuário faz login:

HttpSession session = request.getSession();
session.setAttribute("usuarioLogado", usuario);

O servidor:

Cria um ID de sessão (JSESSIONID)
Envia via cookie para o navegador

Nas próximas requisições:

O navegador envia o cookie
O servidor recupera a sessão
Mantém o usuário autenticado
🔒 Controle de acesso

Exemplo na dashboard.jsp:

<%
String usuario = (String) session.getAttribute("usuarioLogado");

if (usuario == null) {
    response.sendRedirect("login.jsp");
}
%>
⚙️ Configuração do Servlet (web.xml)
<servlet>
    <servlet-name>LoginServlet</servlet-name>
    <servlet-class>LoginServlet</servlet-class>
</servlet>

<servlet-mapping>
    <servlet-name>LoginServlet</servlet-name>
    <url-pattern>/login</url-pattern>
</servlet-mapping>
▶️ Como executar
Instale o Apache Tomcat
Configure o servidor na IDE (ex: IntelliJ)
Faça o deploy do projeto
Acesse:
http://localhost:8080/seu-projeto/login.jsp
