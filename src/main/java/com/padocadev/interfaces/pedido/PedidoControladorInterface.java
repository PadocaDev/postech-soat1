package com.padocadev.interfaces.pedido;

import com.padocadev.adapters.resposta.pedido.PedidoRespostaAdaptador;
import com.padocadev.entities.pedido.objetosDeValor.PedidoRequisicao;

import java.util.List;

public interface PedidoControladorInterface {
    List<PedidoRespostaAdaptador> buscaPedidos();
    PedidoRespostaAdaptador criaPedido(PedidoRequisicao pedidoRequisicao);
}
