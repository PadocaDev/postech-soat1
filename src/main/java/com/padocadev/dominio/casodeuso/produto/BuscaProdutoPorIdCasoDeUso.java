package com.padocadev.dominio.casodeuso.produto;

import com.padocadev.dominio.entidade.produto.Produto;
import com.padocadev.dominio.excecao.produto.ProdutoNaoExisteExcecao;
import com.padocadev.dominio.porta.produto.BuscaProdutoPorIdCasoDeUsoPorta;
import com.padocadev.dominio.porta.produto.ProdutoRepositorioAdaptadorPorta;

public class BuscaProdutoPorIdCasoDeUso implements BuscaProdutoPorIdCasoDeUsoPorta {

    private final ProdutoRepositorioAdaptadorPorta produtoRepositorioAdaptadorPorta;

    public BuscaProdutoPorIdCasoDeUso(ProdutoRepositorioAdaptadorPorta produtoRepositorioAdaptadorPorta) {
        this.produtoRepositorioAdaptadorPorta = produtoRepositorioAdaptadorPorta;
    }

    @Override
    public Produto buscaPorId(Long produtoId) {
        return produtoRepositorioAdaptadorPorta.buscaPorId(produtoId).orElseThrow(ProdutoNaoExisteExcecao::new);
    }

}
