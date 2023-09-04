package com.padocadev.usecases.produto;

import com.padocadev.entities.produto.Produto;
import com.padocadev.exceptions.produto.ProdutoNaoExisteExcecao;
import com.padocadev.interfaces.produto.*;

public class BuscaProdutoPorIdCasoDeUso implements BuscaProdutoPorIdCasoDeUsoInterface {

    @Override
    public Produto buscaPorId(Long produtoId, ProdutoGatewayInterface produtoGatewayInterface) {
        return produtoGatewayInterface.buscaPorId(produtoId).orElseThrow(ProdutoNaoExisteExcecao::new);
    }

}
