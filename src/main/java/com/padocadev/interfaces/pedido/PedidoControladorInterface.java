package com.padocadev.interfaces.pedido;

import com.padocadev.adapters.requisicao.pedido.AtualizaStatusDoPedidoAdaptador;
import com.padocadev.adapters.resposta.pedido.PedidoRespostaAdaptador;
import com.padocadev.entities.pedido.objetosDeValor.PedidoRequisicao;

import java.awt.image.BufferedImage;
import java.util.List;

public interface PedidoControladorInterface {
    List<PedidoRespostaAdaptador> buscaPedidos();
    BufferedImage criaPedido(PedidoRequisicao pedidoRequisicao);
    void atualizaStatusDoPedido(AtualizaStatusDoPedidoAdaptador atualizaStatusDoPedidoAdaptador);
}
