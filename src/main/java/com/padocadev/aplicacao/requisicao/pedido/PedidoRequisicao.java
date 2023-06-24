package com.padocadev.aplicacao.requisicao.pedido;

import java.util.Map;

public record PedidoRequisicao(
        Map<Long, Integer> produtoIdQuantidade,
        String clienteCpf ){
}
