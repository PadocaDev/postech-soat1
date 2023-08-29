package com.padocadev.external.produto;

import com.padocadev.entities.produto.Categoria;
import com.padocadev.entities.produto.Produto;
import com.padocadev.exceptions.produto.ProdutoNaoExisteExcecao;
import com.padocadev.interfaces.produto.ProdutoRepositorio;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProdutoRepositorioCustom implements ProdutoRepositorio {

    private final ProdutoRepositorioJpa produtoRepositorioJpa;

    public ProdutoRepositorioCustom(ProdutoRepositorioJpa produtoRepositorioJpa) {
        this.produtoRepositorioJpa = produtoRepositorioJpa;
    }

    @Override
    public boolean existeComNome(String nome) {
        return produtoRepositorioJpa.existsByNome(nome);
    }

    @Override
    public Optional<Produto> buscaPorNome(String nome) {
        Optional<ProdutoEntidadeJpa> possivelProduto = produtoRepositorioJpa.findByNome(nome);
        return possivelProduto.map(ProdutoEntidadeJpa::converteParaProduto);
    }

    @Override
    public void remover(Long produtoId) {
        produtoRepositorioJpa.deleteById(produtoId);
    }

    @Override
    public Produto edita(Long produtoId, Produto produtoParaEditar) {
        ProdutoEntidadeJpa produtoExistenteEntidadeJpa = produtoRepositorioJpa.findById(produtoId).orElseThrow(ProdutoNaoExisteExcecao::new);

        produtoExistenteEntidadeJpa.atualiza(produtoParaEditar);
        produtoRepositorioJpa.saveAndFlush(produtoExistenteEntidadeJpa);

        return produtoExistenteEntidadeJpa.converteParaProduto();
    }

    @Override
    public List<Produto> buscaTodosPorCategoria(Categoria categoria) {
        return produtoRepositorioJpa.findAllByCategoria(categoria)
                .stream()
                .map(ProdutoEntidadeJpa::converteParaProduto)
                .toList();
    }

    @Override
    public Produto cria(Produto produto) {
        return produtoRepositorioJpa.saveAndFlush(new ProdutoEntidadeJpa(produto)).converteParaProduto();
    }

    @Override
    public Optional<Produto> buscaPorId(Long produtoId) {
        return produtoRepositorioJpa.findById(produtoId).map(ProdutoEntidadeJpa::converteParaProduto);
    }
}
