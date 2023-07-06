CREATE TABLE IF NOT EXISTS ItemPedido
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pedido_id BIGINT NOT NULL,
    produto_id BIGINT NOT NULL,
    quantidade INT NOT NULL,
    precoUnitario DECIMAL(10, 2) NOT NULL,
    CONSTRAINT fk_pedido_itemPedido FOREIGN KEY (pedido_id) REFERENCES Pedido(id),
    CONSTRAINT fk_produto_itemPedido FOREIGN KEY (produto_id) REFERENCES Produto(id)
);