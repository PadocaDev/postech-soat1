package com.padocadev.dominio.entidade.pedido;

import com.padocadev.dominio.entidade.produto.Produto;

import java.math.BigDecimal;

public class ItemPedido {

    private Long id;
    private Pedido pedido;
    private Produto produto;
    private BigDecimal precoUnitario;
    private long quantidade;

    public ItemPedido(long quantidade, Produto produto) {
        this.quantidade = quantidade;
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

    public long getQuantidade() {
        return quantidade;
    }

    public String getIdDoProdutoComoString() {
        return this.produto.getIdComoString();
    }

    public String getNomeDoProduto() {
        return this.produto.getNome();
    }

    public String getNomeDaCategoria() {
        return this.produto.getNomeDaCategoria();
    }

    public BigDecimal getValorTotalDoItem() {
        return this.precoUnitario.multiply(new BigDecimal(this.quantidade));
    }
}
