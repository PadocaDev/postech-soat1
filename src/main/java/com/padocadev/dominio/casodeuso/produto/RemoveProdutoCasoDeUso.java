package com.padocadev.dominio.casodeuso.produto;

import com.padocadev.dominio.entidade.produto.Produto;
import com.padocadev.dominio.excecao.produto.ProdutoNaoExisteExcecao;
import com.padocadev.dominio.porta.produto.ProdutoRepositorioAdaptadorPorta;
import com.padocadev.dominio.porta.produto.RemoveProdutoCasoDeUsoPorta;

import java.util.Optional;

public class RemoveProdutoCasoDeUso implements RemoveProdutoCasoDeUsoPorta {

    private final ProdutoRepositorioAdaptadorPorta produtoRepositorioAdaptadorPorta;

    public RemoveProdutoCasoDeUso(ProdutoRepositorioAdaptadorPorta produtoRepositorioAdaptadorPorta) {
        this.produtoRepositorioAdaptadorPorta = produtoRepositorioAdaptadorPorta;
    }

    @Override
    public void remover(Long produtoId) {
        Optional<Produto> possivelProduto = produtoRepositorioAdaptadorPorta.buscaPorId(produtoId);
        if (possivelProduto.isEmpty()) {
            throw new ProdutoNaoExisteExcecao();
        }

        produtoRepositorioAdaptadorPorta.remover(produtoId);
    }
}