package com.padocadev.usecases.pedido;

import com.padocadev.entities.pedido.Pedido;
import com.padocadev.entities.pedido.Status;
import com.padocadev.exceptions.pedido.PedidoNaoExisteExcecao;
import com.padocadev.interfaces.pedido.AtualizaStatusDoPedidoCasoDeUsoInterface;
import com.padocadev.interfaces.pedido.PedidoGatewayInterface;

public class AtualizaStatusDoPedidoCasoDeUso implements AtualizaStatusDoPedidoCasoDeUsoInterface {

    @Override
    public Pedido atualizaStatusDoPedido(Long idPedido, Status status, PedidoGatewayInterface pedidoGateway) {
        Pedido pedido = pedidoGateway.buscarPedidoPorId(idPedido).orElseThrow(PedidoNaoExisteExcecao::new);
        pedido.setStatus(status);
        pedidoGateway.salva(pedido);
        return pedido;
    }
}
