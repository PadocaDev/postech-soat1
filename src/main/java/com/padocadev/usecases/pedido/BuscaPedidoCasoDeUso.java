package com.padocadev.usecases.pedido;

import com.padocadev.entities.pedido.Pedido;
import com.padocadev.interfaces.pedido.*;

import java.util.List;

public class BuscaPedidoCasoDeUso implements BuscaPedidoCasoDeUsoInterface {

    @Override
    public List<Pedido> buscarTodosPedidosNaoFinalizados(PedidoGatewayInterface pedidoGateway) {
       return pedidoGateway.buscarTodosPedidosNaoFinalizados();
    }
}