package com.padocadev.interfaces.produto;

import com.padocadev.entities.produto.Categoria;
import com.padocadev.entities.produto.Produto;

import java.util.List;

public interface BuscaProdutoPorCategoriaCasoDeUsoInterface {
    List<Produto> buscaPorCategoria(Categoria categoria, ProdutoGatewayInterface produtoGatewayInterface);
}
