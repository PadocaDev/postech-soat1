package com.padocadev.interfaces.pedido;

import com.padocadev.entities.pedido.Pedido;

import java.util.List;

public interface PedidoRepositorio {

    Pedido criar(Pedido pedido);
    List<Pedido> buscarTodosPedidosNaoFinalizados();
}
