package com.padocadev.infraestrutura.adaptador.repositorio.pedido;

import com.padocadev.dominio.entidade.pedido.ItemPedido;
import com.padocadev.infraestrutura.adaptador.repositorio.produto.ProdutoEntidadeJpa;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name = "ItemPedido")
public class ItemPedidoEntidadeJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "increment")
    private Long id;

    @ManyToOne
    private PedidoEntidadeJpa pedido;

    @ManyToOne
    private ProdutoEntidadeJpa produto;

    @NotNull
    private int quantidade;

    @NotNull
    private BigDecimal precoUnitario;

    @Deprecated
    public ItemPedidoEntidadeJpa() {
    }

    public ItemPedidoEntidadeJpa(ItemPedido itemPedido) {
        this.quantidade = itemPedido.getQuantidade();
        this.pedido = new PedidoEntidadeJpa(itemPedido.getPedido());
        this.produto = new ProdutoEntidadeJpa(itemPedido.getProduto());
        this.precoUnitario = itemPedido.getPrecoUnitario();
    }

    public void setPedido(PedidoEntidadeJpa pedido) {
        this.pedido = pedido;
    }

    public Long getId() {
        return id;
    }

    public PedidoEntidadeJpa getPedido() {
        return pedido;
    }

    public ProdutoEntidadeJpa getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return produto.getPreco();
    }

    public ItemPedido paraItemPedido() {
        return new ItemPedido(quantidade, pedido.paraPedido(), produto.paraProduto());
    }
}
