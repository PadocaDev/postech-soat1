package com.padocadev.gateways.produto;

import com.padocadev.entities.produto.Categoria;
import com.padocadev.entities.produto.Produto;
import com.padocadev.interfaces.produto.ProdutoGatewayInterface;
import com.padocadev.interfaces.produto.ProdutoRepositorio;

import java.util.List;
import java.util.Optional;

public class ProdutoGateway implements ProdutoGatewayInterface {

    private final ProdutoRepositorio produtoRepositorio;

    public ProdutoGateway(ProdutoRepositorio produtoRepositorio) {
        this.produtoRepositorio = produtoRepositorio;
    }

    @Override
    public boolean existeProdutoComNome(String nome) {
        return produtoRepositorio.existeComNome(nome);
    }

    @Override
    public Produto cria(Produto produto) {
        return produtoRepositorio.cria(produto);
    }

    @Override
    public Optional<Produto> buscaPorId(Long produtoId) {
        return produtoRepositorio.buscaPorId(produtoId);
    }

    @Override
    public Optional<Produto> buscaPorNome(String nome) {
        return produtoRepositorio.buscaPorNome(nome);
    }

    @Override
    public void remover(Long produtoId) {
        produtoRepositorio.remover(produtoId);
    }

    @Override
    public List<Produto> buscarPorCategoria(Categoria categoria) {
        return produtoRepositorio.buscaTodosPorCategoria(categoria);
    }

    @Override
    public Produto edita(Long produtoId, Produto produtoParaEditar) {
        return produtoRepositorio.edita(produtoId, produtoParaEditar);
    }
}
