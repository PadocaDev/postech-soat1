package com.padocadev.usecases.produto;

import com.padocadev.entities.produto.Produto;
import com.padocadev.exceptions.produto.ProdutoNaoExisteExcecao;
import com.padocadev.interfaces.produto.*;

import java.util.Optional;

public class RemoveProdutoCasoDeUso implements RemoveProdutoCasoDeUsoInterface {

    @Override
    public void remover(Long produtoId, ProdutoGatewayInterface produtoGateway) {
        Optional<Produto> possivelProduto = produtoGateway.buscaPorId(produtoId);
        if (possivelProduto.isEmpty()) {
            throw new ProdutoNaoExisteExcecao();
        }

        produtoGateway.remover(produtoId);
    }
}