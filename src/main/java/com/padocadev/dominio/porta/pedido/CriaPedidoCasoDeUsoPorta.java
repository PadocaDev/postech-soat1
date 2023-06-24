package com.padocadev.dominio.porta.pedido;

import com.padocadev.aplicacao.requisicao.pedido.PedidoRequisicao;
import com.padocadev.dominio.entidade.pedido.Pedido;

public interface CriaPedidoCasoDeUsoPorta {

    Pedido criar(PedidoRequisicao pedido);
}
