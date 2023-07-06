package com.padocadev.aplicacao.resposta.pedido;

import com.padocadev.dominio.entidade.pedido.*;

import java.math.BigDecimal;
import java.util.List;

import static java.time.format.DateTimeFormatter.ofPattern;

public record PedidoResposta(
        String numeroPedido,
        List<ItensDoPedidoResposta> itensPedido,
        BigDecimal valorTotal,
        Status status,
        String dataDeAtualizacao
) {
    public PedidoResposta(Pedido pedido) {
        this(   pedido.getNumeroPedido(),
                pedido.getItensPedido().stream().map(ItensDoPedidoResposta::new).toList(),
                pedido.getValorTotal(),
                pedido.getStatus(),
                pedido.getDataDeAtualizacao().format(ofPattern("dd/MM/yyyy HH:mm:ss"))
        );
    }
}