package com.padocadev.usecases.produto;

import com.padocadev.entities.produto.Produto;
import com.padocadev.exceptions.produto.JaExisteProdutoExcecao;
import com.padocadev.exceptions.produto.ProdutoNaoExisteExcecao;
import com.padocadev.interfaces.produto.*;

import java.util.Optional;

public class EditaProdutoCasoDeUso implements EditaProdutoCasoDeUsoInterface {

    @Override
    public Produto edita(Long produtoId, Produto produtoParaEditar, ProdutoGatewayInterface produtoGateway) {
        Optional<Produto> possivelProduto = produtoGateway.buscaPorId(produtoId);
        if (possivelProduto.isEmpty()) throw new ProdutoNaoExisteExcecao();

        Produto produto = possivelProduto.get();
        Optional<Produto> produtoPorNome = produtoGateway.buscaPorNome(produtoParaEditar.getNome());
        if (produtoPorNome.isPresent() && !produtoPorNome.get().equals(produto)) throw new JaExisteProdutoExcecao();

        return produtoGateway.edita(produtoId, produtoParaEditar);
    }
}
