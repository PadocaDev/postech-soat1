package com.padocadev.aplicacao.requisicao.pedido;

public class ProdutosPedidoDTO {

    private Long produtoId;
    private int quantidade;

    public ProdutosPedidoDTO(Long produtoId, int quantidade) {
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
