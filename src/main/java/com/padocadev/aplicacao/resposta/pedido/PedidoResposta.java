package com.padocadev.aplicacao.resposta.pedido;

import com.padocadev.dominio.entidade.pedido.*;
import com.padocadev.dominio.entidade.produto.Produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoResposta(LocalDateTime dataPedido, List<Produto> produtos, BigDecimal valorTotal, Status status, LocalDateTime dataDeAtualizacao) {

    public static PedidoResposta dePedido(Pedido pedido) {
        return new PedidoResposta(pedido.getDataPedido(), pedido.getProdutos(), pedido.getValorTotal(), pedido.getStatus(), pedido.getDataDeAtualizacao());
    }

    public static List<PedidoResposta> listarPedidos(List<Pedido> pedidos) {
        return pedidos.stream().map(PedidoResposta::dePedido).toList();
    }
}
