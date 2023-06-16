package com.padocadev.dominio.porta.pedido;

import com.padocadev.dominio.entidade.pedido.Pedido;

public interface CriaPedidoCasoDeUsoPorta {

    Pedido criar(Pedido pedido);
}
