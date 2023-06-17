package com.padocadev.dominio.casodeuso.produto;

import com.padocadev.dominio.entidade.produto.Produto;
import com.padocadev.dominio.excecao.produto.JaExisteProdutoExcecao;
import com.padocadev.dominio.excecao.produto.ProdutoNaoExisteExcecao;
import com.padocadev.dominio.porta.produto.*;

import java.util.Optional;

public class EditaProdutoCasoDeUso implements EditaProdutoCasoDeUsoPorta {

    private final ProdutoRepositorioAdaptadorPorta produtoRepositorioAdaptadorPorta;

    public EditaProdutoCasoDeUso(ProdutoRepositorioAdaptadorPorta produtoRepositorioAdaptadorPorta) {
        this.produtoRepositorioAdaptadorPorta = produtoRepositorioAdaptadorPorta;
    }

    @Override
    public Produto edita(Long produtoId, Produto produtoParaEditar) {
        Optional<Produto> possivelProduto = produtoRepositorioAdaptadorPorta.buscaPorId(produtoId);
        if (possivelProduto.isEmpty()) throw new ProdutoNaoExisteExcecao();

        Produto produto = possivelProduto.get();
        Optional<Produto> produtoPorNome = produtoRepositorioAdaptadorPorta.buscaPorNome(produtoParaEditar.getNome());
        if (produtoPorNome.isPresent() && !produtoPorNome.get().equals(produto)) throw new JaExisteProdutoExcecao();

        return produtoRepositorioAdaptadorPorta.editarProduto(produtoId, produtoParaEditar);
    }
}
