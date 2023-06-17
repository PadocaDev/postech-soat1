CREATE TABLE IF NOT EXISTS Produto
(
    id                 BIGINT PRIMARY KEY AUTO_INCREMENT,
    dataCadastro       DATETIME NOT NULL DEFAULT NOW(),
    categoria          ENUM('BEBIDA','LANCHE', 'ACOMPANHAMENTO', 'SOBREMESA') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    nome               VARCHAR(255) NOT NULL,
    preco              DECIMAL(10, 2) DEFAULT NULL,
    UNIQUE (nome)
);