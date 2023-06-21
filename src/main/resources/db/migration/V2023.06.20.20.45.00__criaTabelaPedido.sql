CREATE TABLE IF NOT EXISTS Pedido
(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    clienteId BIGINT,
    numeroPedido VARCHAR(255),
    dataPedido DATETIME NOT NULL DEFAULT NOW(),
    produto BIGINT NOT NULL,
    valorTotal DECIMAL(10, 2) NOT NULL,
    status ENUM('RECEBIDO', 'EM_PREPARACAO', 'PRONTO', 'FINALIZADO') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    dataDeAtualizacao DATETIME NOT NULL DEFAULT NOW(),
    UNIQUE(numeroPedido),
    CONSTRAINT fk_pedido_produto FOREIGN KEY (produto) REFERENCES Produto (id)
);