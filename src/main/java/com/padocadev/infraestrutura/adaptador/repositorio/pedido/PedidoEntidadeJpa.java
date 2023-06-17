package com.padocadev.infraestrutura.adaptador.repositorio.pedido;

import com.padocadev.dominio.entidade.pedido.*;
import com.padocadev.infraestrutura.adaptador.repositorio.produto.ProdutoEntidadeJpa;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Pedido")
public class PedidoEntidadeJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "increment")
    private Long id;

    @NotNull
    private Long clienteId;

    @NotNull
    private String numeroPedido;

    @NotNull
    private LocalDateTime dataPedido;

    @OneToMany
    private List<ProdutoEntidadeJpa> produtos;

    @NotNull
    private BigDecimal valorTotal;

    @NotNull
    private Status status;

    @NotNull
    private LocalDateTime dataDeAtualizacao;

    @Deprecated
    public PedidoEntidadeJpa() {
    }

    public PedidoEntidadeJpa(Pedido pedido) {
        this.clienteId = pedido.getClienteId();
        this.numeroPedido = pedido.getNumeroPedido();
        this.dataPedido = pedido.getDataPedido();
//        this.produtos = produtos;
        this.valorTotal = pedido.getValorTotal();
        this.status = pedido.getStatus();
        this.dataDeAtualizacao = pedido.getDataDeAtualizacao();
    }

    public Long getId() {
        return id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public String getNumeroPedido() {
        return numeroPedido;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public List<ProdutoEntidadeJpa> getProdutos() {
        return produtos;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getDataDeAtualizacao() {
        return dataDeAtualizacao;
    }

    //TODO trocar para o construtor correto depois
    public static Pedido paraPedido(PedidoEntidadeJpa pedidoEntidadeJpa) {
        return new Pedido(pedidoEntidadeJpa.getId(), pedidoEntidadeJpa.getClienteId(), pedidoEntidadeJpa.getDataPedido(), pedidoEntidadeJpa.getValorTotal(), pedidoEntidadeJpa.getStatus(), pedidoEntidadeJpa.getDataDeAtualizacao());
    }
}
