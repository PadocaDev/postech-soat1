package com.padocadev.infraestrutura.adaptador.repositorio.pedido;

import com.padocadev.dominio.entidade.pedido.Status;
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
    private Long numeroPedido;

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

    public PedidoEntidadeJpa(@NotNull Long clienteId, @NotNull Long numeroPedido, List<ProdutoEntidadeJpa> produtos, @NotNull BigDecimal valorTotal, @NotNull Status status, @NotNull LocalDateTime dataDeAtualizacao) {
        this.clienteId = clienteId;
        this.numeroPedido = numeroPedido;
        this.produtos = produtos;
        this.valorTotal = valorTotal;
        this.status = status;
        this.dataDeAtualizacao = dataDeAtualizacao;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public Long getNumeroPedido() {
        return numeroPedido;
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
}
