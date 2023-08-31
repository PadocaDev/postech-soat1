package com.padocadev.usecases.pagamento;

import com.padocadev.interfaces.pagamento.NotificaPagamentoCriacaoPedidoCasoDeUsoInterface;
import com.padocadev.interfaces.pagamento.NotificaPagamentoGatewayInterface;
import com.padocadev.entities.pedido.Pedido;

public class NotificaPagamentoCriacaoPedidoCasoDeUso implements NotificaPagamentoCriacaoPedidoCasoDeUsoInterface {

    @Override
    public void notifica(Pedido pedidoCriado, NotificaPagamentoGatewayInterface notificaPagamentoGateway) {
        notificaPagamentoGateway.notifica(pedidoCriado);
    }
}
