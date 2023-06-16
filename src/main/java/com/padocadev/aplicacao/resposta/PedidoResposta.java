package com.padocadev.aplicacao.resposta;

import com.padocadev.dominio.entidade.cliente.Cliente;
import com.padocadev.dominio.entidade.pedido.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoResposta(LocalDateTime dataPedido, List<Produto> produtos, BigDecimal valorTotal, Status status, LocalDateTime dataDeAtualizacao) {

    public PedidoResposta(Pedido pedido) {
        this(pedido.getDataPedido(), pedido.getProdutos(), pedido.getValorTotal(), pedido.getStatus(), pedido.getDataDeAtualizacao());
    }
}
