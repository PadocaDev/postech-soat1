package com.padocadev.usecases.produto;

import com.padocadev.entities.produto.Produto;
import com.padocadev.exceptions.produto.JaExisteProdutoExcecao;
import com.padocadev.interfaces.produto.CriaProdutoCasoDeUsoInterface;
import com.padocadev.interfaces.produto.ProdutoGatewayInterface;

public class CriaProdutoCasoDeUso implements CriaProdutoCasoDeUsoInterface {

    @Override
    public Produto cria(Produto produto, ProdutoGatewayInterface produtoGatewayInterface) {
        if (produtoGatewayInterface.existeProdutoComNome(produto.getNome())) throw new JaExisteProdutoExcecao();
        return produtoGatewayInterface.cria(produto);
    }
}
