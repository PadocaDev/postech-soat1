package com.padocadev.dominio.porta.produto;

import com.padocadev.dominio.entidade.produto.Categoria;
import com.padocadev.dominio.entidade.produto.Produto;

import java.util.List;

public interface BuscaProdutoPorCategoriaCasoDeUsoPorta {
    List<Produto> buscaPorCategoria(Categoria categoria);
}