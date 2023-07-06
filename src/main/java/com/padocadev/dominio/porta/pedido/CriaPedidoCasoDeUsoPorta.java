package com.padocadev.dominio.porta.pedido;

import com.padocadev.dominio.casodeuso.pedido.objetosDeValor.PedidoRequisicao;
import com.padocadev.dominio.entidade.pedido.Pedido;

public interface CriaPedidoCasoDeUsoPorta {

    Pedido criar(PedidoRequisicao pedido);
}
