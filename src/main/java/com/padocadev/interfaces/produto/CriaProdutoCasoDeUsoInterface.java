package com.padocadev.interfaces.produto;

import com.padocadev.entities.produto.Produto;

public interface CriaProdutoCasoDeUsoInterface {
    Produto cria(Produto produto, ProdutoGatewayInterface produtoGatewayInterface);
}
