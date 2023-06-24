package com.padocadev.infraestrutura.adaptador.repositorio.pedido;

import com.padocadev.dominio.entidade.pedido.*;
import com.padocadev.infraestrutura.adaptador.repositorio.cliente.ClienteEntidadeJpa;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "Pedido")
public class PedidoEntidadeJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "increment")
    private Long id;


    @ManyToOne
    private ClienteEntidadeJpa cliente;

    @NotNull
    private String numeroPedido;

    @NotNull
    private LocalDateTime dataPedido;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedidoEntidadeJpa> itens = new ArrayList<>();

    @NotNull
    private BigDecimal valorTotal;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    private LocalDateTime dataDeAtualizacao;

    @Deprecated
    public PedidoEntidadeJpa() {
    }

    public PedidoEntidadeJpa(Pedido pedido) {
        this.cliente =  Optional.ofNullable(pedido.getCliente()).map(ClienteEntidadeJpa::new).orElse(null);
        this.numeroPedido = pedido.getNumeroPedido();
        this.dataPedido = pedido.getDataPedido();
        this.valorTotal = pedido.getValorTotal();
        this.status = pedido.getStatus();
        this.dataDeAtualizacao = pedido.getDataDeAtualizacao();
        this.itens = getItens(pedido.getItensPedido());
    }

    private List<ItemPedidoEntidadeJpa> getItens(List<ItemPedido> itemPedidoList) {
        itemPedidoList.stream().forEach(itemPedido -> {
        ItemPedidoEntidadeJpa itemPedidoEntidadeJpa = new ItemPedidoEntidadeJpa(itemPedido);
        itemPedidoEntidadeJpa.setPedido(this);
        this.itens.add(itemPedidoEntidadeJpa);
        });
        return this.itens;
    }

    public Long getId() {
        return id;
    }

    public ClienteEntidadeJpa getCliente() {
        return cliente;
    }

    public String getNumeroPedido() {
        return numeroPedido;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public List<ItemPedidoEntidadeJpa> getItens() {
        return itens;
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

    public void setItens(List<ItemPedidoEntidadeJpa> itens) {
        this.itens = itens;
    }

    public Pedido paraPedido() {
        return new Pedido(
                Optional.ofNullable(this.cliente).map(ClienteEntidadeJpa::paraCliente).orElse(null),
                this.numeroPedido,
                this.dataPedido,
                this.itens.stream().map(ItemPedidoEntidadeJpa::paraItemPedido).toList(),
                this.valorTotal,
                this.status,
                this.dataDeAtualizacao
        );
    }
}
