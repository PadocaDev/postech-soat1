package com.padocadev.aplicacao.requisicao.pedido;

import jakarta.validation.constraints.NotBlank;

public class ProdutosDoPedidoRequisicao {

    @NotBlank
    private Long produtoId;

    @NotBlank
    private int quantidade;

    public ProdutosDoPedidoRequisicao(Long produtoId, int quantidade) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
