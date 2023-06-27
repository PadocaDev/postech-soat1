package com.padocadev.dominio.casodeuso.produto;

import com.padocadev.dominio.entidade.produto.Produto;
import com.padocadev.dominio.excecao.produto.JaExisteProdutoExcecao;
import com.padocadev.dominio.porta.produto.CriaProdutoCasoDeUsoPorta;
import com.padocadev.dominio.porta.produto.ProdutoRepositorioAdaptadorPorta;

public class CriaProdutoCasoDeUso implements CriaProdutoCasoDeUsoPorta {

    private final ProdutoRepositorioAdaptadorPorta produtoRepositorioAdaptadorPorta;

    public CriaProdutoCasoDeUso(ProdutoRepositorioAdaptadorPorta produtoRepositorioAdaptadorPorta) {
        this.produtoRepositorioAdaptadorPorta = produtoRepositorioAdaptadorPorta;
    }

    @Override
    public Produto cria(Produto produto) {
        if (produtoRepositorioAdaptadorPorta.existeProdutoComNome(produto.getNome())) throw new JaExisteProdutoExcecao();
       return produtoRepositorioAdaptadorPorta.cria(produto);
    }
}
