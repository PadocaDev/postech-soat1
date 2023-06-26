package com.padocadev.dominio.porta.produto;

import com.padocadev.dominio.entidade.produto.Produto;

import java.util.Optional;

public interface ProdutoRepositorioAdaptadorPorta {

    boolean existeProdutoComNome(String nome);

    Produto cria(Produto produto);

    Optional<Produto> buscaPorId(Long produtoId);

    Optional<Produto> buscaPorNome(String nome);

    Produto edita(Long produtoId, Produto produtoParaEditar);
}
