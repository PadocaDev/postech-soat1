package com.padocadev.interfaces.pagamento;

import com.padocadev.entities.pedido.Pedido;

public interface NotificaPagamentoGatewayInterface {
    void notifica(Pedido pedidoCriado);
}
