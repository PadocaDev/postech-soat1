package com.padocadev.interfaces.produto;

import com.padocadev.entities.produto.Produto;

public interface EditaProdutoCasoDeUsoInterface {
    Produto edita(Long produtoId, Produto produtoParaEditar, ProdutoGatewayInterface produtoGatewayInterface);
}
