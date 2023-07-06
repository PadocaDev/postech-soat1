package com.padocadev.dominio.casodeuso.pedido.objetosDeValor;

import jakarta.validation.constraints.NotNull;

public record ProdutosDoPedidoRequisicao(@NotNull Long produtoId, @NotNull int quantidade) {

    public ProdutosDoPedidoRequisicao(Long produtoId, int quantidade) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
    }
}
