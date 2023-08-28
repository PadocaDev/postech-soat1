package com.padocadev.aplicacao.resposta.pedido;

import com.padocadev.dominio.entidade.pedido.ItemPedido;

import java.math.BigDecimal;

public record ItensDoPedidoResposta(String nomeProduto, BigDecimal precoUnitario, long quantidade) {

    public ItensDoPedidoResposta(ItemPedido itemPedido) {
        this(itemPedido.getProduto().getNome(), itemPedido.getPrecoUnitario(), itemPedido.getQuantidade());
    }
}
