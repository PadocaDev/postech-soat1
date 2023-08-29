package com.padocadev.adapters.resposta.pedido;

import com.padocadev.entities.pedido.Pedido;
import com.padocadev.entities.pedido.Status;

import java.math.BigDecimal;
import java.util.List;

import static java.time.format.DateTimeFormatter.ofPattern;

public record PedidoRespostaAdaptador(
        String numeroPedido,
        List<ItensDoPedidoResposta> itensPedido,
        BigDecimal valorTotal,
        Status status,
        String dataDeAtualizacao
) {
    public PedidoRespostaAdaptador(Pedido pedido) {
        this(   pedido.getNumeroPedido(),
                pedido.getItensPedido().stream().map(ItensDoPedidoResposta::new).toList(),
                pedido.getValorTotal(),
                pedido.getStatus(),
                pedido.getDataDeAtualizacao().format(ofPattern("dd/MM/yyyy HH:mm:ss"))
        );
    }
}