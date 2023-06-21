package com.padocadev.dominio.porta.produto;

import com.padocadev.dominio.entidade.produto.Produto;

import java.util.Optional;

public interface ProdutoRepositorioAdaptadorPorta {

    boolean existeProdutoComNome(String nome);

    Produto criarProduto(Produto produto);

    Optional<Produto> buscaPorId(Long produtoId);

    Optional<Produto> buscaPorNome(String nome);

    Produto editarProduto(Long produtoId, Produto produtoParaEditar);
}