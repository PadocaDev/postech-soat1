package com.padocadev.interfaces.produto;

import com.padocadev.entities.produto.Categoria;
import com.padocadev.entities.produto.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepositorio {
    boolean existeComNome(String nome);
    Optional<Produto> buscaPorNome(String nome);
    List<Produto> buscaTodosPorCategoria(Categoria categoria);
    Produto cria(Produto produto);
    Optional<Produto> buscaPorId(Long produtoId);
    void remover(Long produtoId);
    Produto edita(Long produtoId, Produto produtoParaEditar);
}
