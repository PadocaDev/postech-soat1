package com.padocadev.interfaces.pedido;

import com.padocadev.entities.pedido.Status;

public interface AtualizaStatusDoPedidoCasoDeUsoInterface {

    void atualizaStatusDoPedido(Long idPedido, Status status, PedidoGatewayInterface pedidoGateway);
}
