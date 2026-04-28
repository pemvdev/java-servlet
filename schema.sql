CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    usuario VARCHAR(50) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL
);

-- Inserindo um usuário padrão para teste
INSERT INTO usuarios (usuario, senha) VALUES ('admin', '123');
