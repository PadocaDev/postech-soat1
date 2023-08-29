package com.padocadev.adapters.resposta.pedido;

import com.padocadev.entities.pedido.ItemPedido;

import java.math.BigDecimal;

public record ItensDoPedidoResposta(String nomeProduto, BigDecimal precoUnitario, int quantidade) {

    public ItensDoPedidoResposta(ItemPedido itemPedido) {
        this(itemPedido.getProduto().getNome(), itemPedido.getPrecoUnitario(), itemPedido.getQuantidade());
    }
}
