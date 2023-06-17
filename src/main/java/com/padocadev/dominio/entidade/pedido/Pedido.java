package com.padocadev.dominio.entidade.pedido;

import com.padocadev.dominio.entidade.produto.Produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Pedido {
    private Long id;
    private Long clienteId;
    private String numeroPedido = UUID.randomUUID().toString().substring(0,6);
    private LocalDateTime dataPedido = LocalDateTime.now();
    private List<Produto> produtos = new ArrayList<>();
    private BigDecimal valorTotal;
    private Status status;
    private LocalDateTime dataDeAtualizacao;

    public Pedido(Long id, Long clienteId, LocalDateTime dataPedido, List<Produto> produtos, BigDecimal valorTotal, Status status, LocalDateTime dataDeAtualizacao) {
        this.id = id;
        this.clienteId = clienteId;
        this.dataPedido = dataPedido;
        this.produtos = produtos;
        this.valorTotal = valorTotal;
        this.status = status;
        this.dataDeAtualizacao = dataDeAtualizacao;
    }

    //TODO remover depois
    public Pedido(Long id, Long clienteId, LocalDateTime dataPedido, BigDecimal valorTotal, Status status, LocalDateTime dataDeAtualizacao) {
        this.id = id;
        this.clienteId = clienteId;
        this.dataPedido = dataPedido;
        this.valorTotal = valorTotal;
        this.status = status;
        this.dataDeAtualizacao = dataDeAtualizacao;
    }

    public Pedido(List<Produto> produtos, Long clienteId) {
        this.produtos = produtos;
        this.clienteId = clienteId;
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

    public List<Produto> getProdutos() {
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
