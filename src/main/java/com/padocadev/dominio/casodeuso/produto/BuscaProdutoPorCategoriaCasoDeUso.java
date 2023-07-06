package com.padocadev.dominio.casodeuso.produto;

import com.padocadev.dominio.entidade.produto.Categoria;
import com.padocadev.dominio.entidade.produto.Produto;
import com.padocadev.dominio.porta.produto.BuscaProdutoPorCategoriaCasoDeUsoPorta;
import com.padocadev.dominio.porta.produto.ProdutoRepositorioAdaptadorPorta;

import java.util.List;

public class BuscaProdutoPorCategoriaCasoDeUso implements BuscaProdutoPorCategoriaCasoDeUsoPorta {
    private final ProdutoRepositorioAdaptadorPorta produtoRepositorioAdaptadorPorta;
    public BuscaProdutoPorCategoriaCasoDeUso(ProdutoRepositorioAdaptadorPorta produtoRepositorioAdaptadorPorta) {
        this.produtoRepositorioAdaptadorPorta = produtoRepositorioAdaptadorPorta;
    }

    @Override
    public List<Produto> buscaPorCategoria(Categoria categoria) {
        return produtoRepositorioAdaptadorPorta.buscarPorCategoria(categoria);
    }
}
