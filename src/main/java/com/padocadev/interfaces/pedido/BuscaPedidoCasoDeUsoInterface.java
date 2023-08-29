package com.padocadev.interfaces.pedido;

import com.padocadev.entities.pedido.Pedido;

import java.util.List;

public interface BuscaPedidoCasoDeUsoInterface {
    List<Pedido> buscarTodosPedidosNaoFinalizados(PedidoGatewayInterface pedidoGatewayInterface);
}
