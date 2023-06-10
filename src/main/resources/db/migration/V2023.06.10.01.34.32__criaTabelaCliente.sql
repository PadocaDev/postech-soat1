CREATE TABLE IF NOT EXISTS Cliente
(
    id                 BIGINT PRIMARY KEY AUTO_INCREMENT,
    dataCadastro       DATETIME NOT NULL DEFAULT NOW(),
    cpf                VARCHAR(11) NOT NULL,
    email              VARCHAR(255) NOT NULL,
    nome               VARCHAR(255) NOT NULL,
    UNIQUE (cpf),
    UNIQUE (email)
);