package com.padocadev.dominio.entidade.pedido;

import com.padocadev.dominio.entidade.produto.Produto;

import java.math.BigDecimal;

public class ItemPedido {

    private Long id;
    private Pedido pedido;
    private Produto produto;
    private BigDecimal precoUnitario;
    private int quantidade;


    public ItemPedido(int quantidade, Pedido pedido, Produto produto) {
        this.quantidade = quantidade;
        this.pedido = pedido;
        this.produto = produto;
        this.precoUnitario = produto.getPreco();
    }

    public Pedido getPedido() {
        return pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public BigDecimal getPrecoUnitario() {
        return produto.getPreco();
    }

    public int getQuantidade() {
        return quantidade;
    }
}
