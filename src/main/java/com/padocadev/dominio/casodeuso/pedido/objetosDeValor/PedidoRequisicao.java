package com.padocadev.dominio.casodeuso.pedido.objetosDeValor;

import java.util.List;

public record PedidoRequisicao(List<ProdutosDoPedidoRequisicao> produtosPedidos, String clienteCpf){
}
