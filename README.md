# 📦 Projeto Java Servlet + Tomcat

Este projeto é uma aplicação web simples utilizando **Java Servlets + JSP + PostgreSQL**, executada manualmente no **Apache Tomcat**.

---

# 🚀 Como rodar o projeto

## 1. Build do projeto

Sempre que alterar código Java (Servlets, classes, etc), gere o `.war`:

```bash
mvn clean package
```

---

## 2. Deploy no Tomcat

Copie o `.war` para a pasta `webapps` do Tomcat:

```bash
cp target/java-servlet-1.0-SNAPSHOT.war ~/apache-tomcat-9.0.82/webapps/
```

---

## 3. Iniciar o servidor (modo debug)

```bash
~/apache-tomcat-9.0.82/bin/catalina.sh run
```

👉 Esse modo mostra logs no terminal (recomendado para desenvolvimento)

---

## 🌐 Acessar a aplicação

```text
http://localhost:8080/java-servlet-1.0-SNAPSHOT/login.jsp
```
    
---

# 🛑 Parar o Tomcat

## Método 1 (recomendado)

```bash
~/apache-tomcat-9.0.82/bin/shutdown.sh
```

---

## Método 2 (forçado)

### 1. Encontrar processo:

```bash
ps aux | grep tomcat
```

### 2. Matar processo:

```bash
kill -9 PID
```

(Substitua `PID` pelo número do processo)

---

# 🔁 Recompilar após alterações

## 🔧 Alterações em Java (Servlets, ConnectionFactory, etc)

É necessário rebuild completo:

```bash
mvn clean package
cp target/java-servlet-1.0-SNAPSHOT.war ~/apache-tomcat-9.0.82/webapps/
```

Depois reinicie o Tomcat.

---

## 🎨 Alterações em JSP

### Opção 1 (segura - recomendada)

Rebuild completo:

```bash
mvn clean package
```

---

### Opção 2 (rápida - desenvolvimento)

Copiar diretamente os arquivos JSP:

```bash
cp src/web/*.jsp ~/apache-tomcat-9.0.82/webapps/java-servlet-1.0-SNAPSHOT/
```

👉 Atualize a página no navegador (F5)

---

# 🧠 Estrutura do projeto

```text
src/
 ├── web/                # JSPs (login, register, dashboard)
 ├── java/               # Servlets e classes Java

target/
 ├── *.war               # Arquivo gerado pelo Maven
```

---

# 🗄️ Banco de dados

## Configuração padrão

```java
jdbc:postgresql://localhost:5432/login_java_db
```

## Usuário padrão:

```text
user: postgres
password: postgres
```

---

# ⚠️ Problemas comuns

## ❌ Erro: `Address already in use`

Significa que o Tomcat já está rodando.

➡️ Solução:

```bash
ps aux | grep tomcat
kill -9 PID
```

---

## ❌ Erro: `relation "usuarios" does not exist`

➡️ A tabela não existe no banco atual

Crie:

```sql
CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    usuario VARCHAR(50) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL
);
```

---

## ❌ Aplicação não atualiza após mudanças

➡️ Provável causa:

* WAR antigo
* não rodou `mvn clean package`

---

# 📌 Boas práticas

* Sempre usar `catalina.sh run` durante desenvolvimento
* Não misturar com `startup.sh`
* Evitar múltiplas versões do projeto em `webapps`
* Sempre rebuildar após alterações em Java

---

# 📈 Próximos passos

* Implementar hash de senha (BCrypt)
* Migrar estrutura para `src/main/webapp`
* Separar camadas (DAO, Service, Controller)
* Integrar com frontend moderno

---

# 👨‍💻 Autor

Projeto desenvolvido para estudo de Servlets, JSP e integração com PostgreSQL.
