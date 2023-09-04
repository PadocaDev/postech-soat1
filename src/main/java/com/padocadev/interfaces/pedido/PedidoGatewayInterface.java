package com.padocadev.interfaces.pedido;

import com.padocadev.entities.pedido.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoGatewayInterface {
    Pedido criar(Pedido pedido);
    List<Pedido> buscarTodosPedidosNaoFinalizados();
    Optional<Pedido> buscarPedidoPorId(Long idPedido);
    void salva(Pedido pedido);
}
