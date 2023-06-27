package com.padocadev.infraestrutura.adaptador.repositorio.produto;

import com.padocadev.dominio.entidade.produto.Produto;
import com.padocadev.dominio.excecao.produto.ProdutoNaoExisteExcecao;
import com.padocadev.dominio.porta.produto.ProdutoRepositorioAdaptadorPorta;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProdutoRepositorioAdaptadorJpa implements ProdutoRepositorioAdaptadorPorta {

    private final ProdutoRepositorioJpa produtoRepositorioJpa;

    public ProdutoRepositorioAdaptadorJpa(ProdutoRepositorioJpa produtoRepositorioJpa) {
        this.produtoRepositorioJpa = produtoRepositorioJpa;
    }

    @Override
    public boolean existeProdutoComNome(String nome) {
        return produtoRepositorioJpa.existsByNome(nome);
    }

    @Override
    public Produto cria(Produto produto) {
        ProdutoEntidadeJpa produtoEntidadeJpa = produtoRepositorioJpa.save(new ProdutoEntidadeJpa(produto));
        return produtoEntidadeJpa.converteParaProduto();
    }

    @Override
    public Optional<Produto> buscaPorId(Long produtoId) {
        Optional<ProdutoEntidadeJpa> possivelProduto = produtoRepositorioJpa.findById(produtoId);
        return possivelProduto.map(ProdutoEntidadeJpa::converteParaProduto);
    }

    @Override
    public Optional<Produto> buscaPorNome(String nome) {
        Optional<ProdutoEntidadeJpa> possivelProduto = produtoRepositorioJpa.findByNome(nome);
        return possivelProduto.map(ProdutoEntidadeJpa::converteParaProduto);
    }

    @Override
    public Produto edita(Long produtoId, Produto produtoParaEditar) {
        ProdutoEntidadeJpa produtoExistenteEntidadeJpa = produtoRepositorioJpa.findById(produtoId).orElseThrow(ProdutoNaoExisteExcecao::new);

        produtoExistenteEntidadeJpa.atualiza(produtoParaEditar);
        produtoRepositorioJpa.save(produtoExistenteEntidadeJpa);

        return produtoExistenteEntidadeJpa.converteParaProduto();
    }
}
