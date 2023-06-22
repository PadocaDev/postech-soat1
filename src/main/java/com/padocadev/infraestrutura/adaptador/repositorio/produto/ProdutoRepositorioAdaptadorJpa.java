package com.padocadev.infraestrutura.adaptador.repositorio.produto;

import com.padocadev.dominio.entidade.produto.Categoria;
import com.padocadev.dominio.entidade.produto.Produto;
import com.padocadev.dominio.excecao.produto.ProdutoNaoExisteExcecao;
import com.padocadev.dominio.porta.produto.ProdutoRepositorioAdaptadorPorta;
import org.springframework.stereotype.Component;

import java.util.List;
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
    public Produto criarProduto(Produto produto) {
        ProdutoEntidadeJpa produtoEntidadeJpa = new ProdutoEntidadeJpa(produto.getDataCadastro(), produto.getNome(), produto.getCategoria(), produto.getPreco());
        produtoRepositorioJpa.save(produtoEntidadeJpa);

        produto.setId(produtoEntidadeJpa.getId());
        return produto;
    }

    @Override
    public Optional<Produto> buscaPorId(Long produtoId) {
        return produtoRepositorioJpa.findById(produtoId)
                .flatMap(produtoEntidadeJpa -> Optional.of(
                            new Produto(
                                    produtoEntidadeJpa.getId(),
                                    produtoEntidadeJpa.getDataCadastro(),
                                    produtoEntidadeJpa.getNome(),
                                    produtoEntidadeJpa.getCategoria(),
                                    produtoEntidadeJpa.getPreco()
                            )
                )
                );
    }

    @Override
    public Optional<Produto> buscaPorNome(String nome) {
        return produtoRepositorioJpa.findByNome(nome)
                .flatMap(produtoEntidadeJpa -> Optional.of(
                                new Produto(
                                        produtoEntidadeJpa.getId(),
                                        produtoEntidadeJpa.getDataCadastro(),
                                        produtoEntidadeJpa.getNome(),
                                        produtoEntidadeJpa.getCategoria(),
                                        produtoEntidadeJpa.getPreco()
                                )
                        )
                );
    }

    @Override
    public Produto editarProduto(Long produtoId, Produto produtoParaEditar) {
        ProdutoEntidadeJpa produtoExistenteEntidadeJpa = produtoRepositorioJpa.findById(produtoId).orElseThrow(ProdutoNaoExisteExcecao::new);
        produtoParaEditar.atualiza(produtoExistenteEntidadeJpa);
        produtoRepositorioJpa.save(produtoExistenteEntidadeJpa);

        produtoParaEditar.setId(produtoExistenteEntidadeJpa.getId());
        return produtoParaEditar;
    }

    @Override
    public void removerProduto(Long produtoId) {
        ProdutoEntidadeJpa produtoEntidadeJpa = produtoRepositorioJpa.findById(produtoId).orElseThrow(ProdutoNaoExisteExcecao::new);
        produtoRepositorioJpa.delete(produtoEntidadeJpa);
    }

    @Override
    public List<Produto> buscarPorCategoria(Categoria categoria) {
        return produtoRepositorioJpa.findAllByCategoria(categoria).stream().flatMap(produtoEntidadeJpa -> Optional.of(
                        new Produto(
                                produtoEntidadeJpa.getId(),
                                produtoEntidadeJpa.getDataCadastro(),
                                produtoEntidadeJpa.getNome(),
                                produtoEntidadeJpa.getCategoria(),
                                produtoEntidadeJpa.getPreco()
                        )
                ).stream()
        ).toList();
    }
}
