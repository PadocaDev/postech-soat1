package com.padocadev.interfaces.produto;

import com.padocadev.entities.produto.Produto;

public interface BuscaProdutoPorIdCasoDeUsoInterface {
    Produto buscaPorId(Long produtoId, ProdutoGatewayInterface produtoGatewayInterface);
}
