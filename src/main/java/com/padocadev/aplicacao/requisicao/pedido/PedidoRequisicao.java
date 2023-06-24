package com.padocadev.aplicacao.requisicao.pedido;

import java.util.List;

public record PedidoRequisicao(
        List<ProdutosPedidoDTO> produtosPedidos,
        String clienteCpf ){
}
