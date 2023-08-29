package com.padocadev.entities.pedido;

import com.padocadev.entities.cliente.Cliente;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static com.padocadev.entities.pedido.Status.RECEBIDO;
import static java.time.LocalDateTime.now;

public class Pedido {
    private Long id;
    private Cliente  cliente;
    private String numeroPedido = UUID.randomUUID().toString().substring(0,6);
    private LocalDateTime dataPedido = now();
    private List<ItemPedido> itensPedido = new ArrayList<>();
    private BigDecimal valorTotal = BigDecimal.ZERO;
    private Status status = RECEBIDO;
    private LocalDateTime dataDeAtualizacao = now();

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pedido(Cliente cliente, String numeroPedido, LocalDateTime dataPedido, List<ItemPedido> itens, BigDecimal valorTotal, Status status, LocalDateTime dataDeAtualizacao) {
        this.cliente = cliente;
        this.numeroPedido = numeroPedido;
        this.dataPedido = dataPedido;
        this.itensPedido = itens;
        this.valorTotal = valorTotal;
        this.status = status;
        this.dataDeAtualizacao = dataDeAtualizacao;
    }

    public void adicionarItem(ItemPedido item) {
        item.setPedido(this);
        this.getItensPedido().add(item);
        this.valorTotal = this.valorTotal.add(item.getPrecoUnitario().multiply(new BigDecimal(item.getQuantidade())));
    }

    public Long getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getNumeroPedido() {
        return numeroPedido;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public List<ItemPedido> getItensPedido() {
        return itensPedido;
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
