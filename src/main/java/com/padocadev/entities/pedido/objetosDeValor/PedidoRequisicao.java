package com.padocadev.entities.pedido.objetosDeValor;

import java.util.List;

public record PedidoRequisicao(List<ProdutosDoPedidoRequisicao> produtosPedidos, String clienteCpf){
}
