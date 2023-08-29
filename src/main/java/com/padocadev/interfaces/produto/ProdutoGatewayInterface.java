package com.padocadev.interfaces.produto;

import com.padocadev.entities.produto.Categoria;
import com.padocadev.entities.produto.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoGatewayInterface {
    boolean existeProdutoComNome(String nome);

    Produto cria(Produto produto);

    Optional<Produto> buscaPorId(Long produtoId);

    Optional<Produto> buscaPorNome(String nome);

    void remover(Long produtoId);

    List<Produto> buscarPorCategoria(Categoria categoria);

    Produto edita(Long produtoId, Produto produtoParaEditar);
}
