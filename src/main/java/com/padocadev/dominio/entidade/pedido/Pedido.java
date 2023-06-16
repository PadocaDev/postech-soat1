package com.padocadev.dominio.entidade.pedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Pedido {
    private Long id;
    private Long clienteId;
    private Long numeroPedido;
    private LocalDateTime dataPedido;
    private List<Produto> produtos;
    private BigDecimal valorTotal;
    private Status status;
    private LocalDateTime dataDeAtualizacao;

    public Pedido (Long id, Long clienteId, Long numeroPedido, LocalDateTime dataPedido, List<Produto> produtos, BigDecimal valorTotal, Status status, LocalDateTime dataDeAtualização){
        this.id = id;
        this.clienteId = clienteId;
        this.numeroPedido = numeroPedido;
        this.dataPedido = dataPedido;
        this.produtos = produtos;
        this.valorTotal = valorTotal;
        this.status = status;
        this.dataDeAtualizacao = dataDeAtualização;
    }

}
