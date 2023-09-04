package com.padocadev.usecases.produto;

import com.padocadev.entities.produto.Categoria;
import com.padocadev.entities.produto.Produto;
import com.padocadev.interfaces.produto.*;

import java.util.List;

public class BuscaProdutoPorCategoriaCasoDeUso implements BuscaProdutoPorCategoriaCasoDeUsoInterface {

    @Override
    public List<Produto> buscaPorCategoria(Categoria categoria, ProdutoGatewayInterface produtoGateway) {
        return produtoGateway.buscarPorCategoria(categoria);
    }
}
