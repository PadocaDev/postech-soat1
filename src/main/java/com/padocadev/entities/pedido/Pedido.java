package com.padocadev.entities.pedido;

import com.padocadev.entities.cliente.Cliente;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static com.padocadev.entities.pedido.Status.AGUARDANDO_PAGAMENTO;
import static java.math.BigDecimal.ZERO;
import static java.time.LocalDateTime.now;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

public class Pedido {
    private Long id;
    private Cliente cliente;
    private String numeroPedido = randomNumeric(6);
    private LocalDateTime dataPedido = now();
    private List<ItemPedido> itensPedido = new ArrayList<>();
    private BigDecimal valorTotal = ZERO;
    private Status status = AGUARDANDO_PAGAMENTO;
    private LocalDateTime dataDeAtualizacao = now();

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pedido(Long id,
                  Cliente cliente,
                  String numeroPedido,
                  LocalDateTime dataPedido,
                  List<ItemPedido> itens,
                  BigDecimal valorTotal,
                  Status status,
                  LocalDateTime dataDeAtualizacao) {
        this.id = id;
        this.cliente = cliente;
        this.numeroPedido = numeroPedido;
        this.dataPedido = dataPedido;
        this.itensPedido = itens;
        this.valorTotal = valorTotal;
        this.status = status;
        this.dataDeAtualizacao = dataDeAtualizacao;
    }

    public Pedido(Long id) {
        this.id = id;
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

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getDataDeAtualizacao() {
        return dataDeAtualizacao;
    }

}
