package com.padocadev.adapters.resposta.produto;

import com.padocadev.entities.produto.Categoria;
import com.padocadev.entities.produto.Produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ProdutoRespostaAdaptador(Long id, String nome, Categoria categoria, BigDecimal preco, LocalDateTime dataCadastro) {

    public static ProdutoRespostaAdaptador deProduto(Produto produto) {
        return new ProdutoRespostaAdaptador(produto.getId(), produto.getNome(), produto.getCategoria(), produto.getPreco(), produto.getDataCadastro());
    }
    public static List<ProdutoRespostaAdaptador> deProduto(List<Produto> produtos) {
        return produtos.stream().map(ProdutoRespostaAdaptador::deProduto).toList();
    }
}
