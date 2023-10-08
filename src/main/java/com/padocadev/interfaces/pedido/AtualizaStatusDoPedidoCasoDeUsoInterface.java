package com.padocadev.interfaces.pedido;

import com.padocadev.entities.pagamento.PagamentoStatus;
import com.padocadev.entities.pedido.Pedido;
import com.padocadev.entities.pedido.Status;

public interface AtualizaStatusDoPedidoCasoDeUsoInterface {

    Pedido atualizaStatusDoPedido(Long idPedido, Status status, PedidoGatewayInterface pedidoGateway);
    Pedido atualizaStatusDoPedido(Long idPedido, PagamentoStatus pagamentoStatus, PedidoGatewayInterface pedidoGateway);
}
