package com.padocadev.gateways.pagamento;

import com.padocadev.entities.pedido.Pedido;
import com.padocadev.interfaces.pagamento.NotificaPagamentoGatewayInterface;
import com.padocadev.interfaces.pagamento.NotificaPagamentoRepositorio;

public class NotificaPagamentoGateway implements NotificaPagamentoGatewayInterface {

    private final NotificaPagamentoRepositorio notificaPagamentoRepositorio;

    public NotificaPagamentoGateway(NotificaPagamentoRepositorio notificaPagamentoRepositorio) {
        this.notificaPagamentoRepositorio = notificaPagamentoRepositorio;
    }

    @Override
    public void notifica(Pedido pedidoCriado) {
        notificaPagamentoRepositorio.notifica(pedidoCriado);
    }
}
