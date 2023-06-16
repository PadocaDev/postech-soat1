package com.padocadev.dominio.porta.pedido;

import com.padocadev.dominio.entidade.pedido.Pedido;

import java.util.List;

public interface PedidoRepositorioAdaptadorPorta {

    Pedido criar(Pedido pedido);
    List<Pedido> buscarTodosPedidos();
}
