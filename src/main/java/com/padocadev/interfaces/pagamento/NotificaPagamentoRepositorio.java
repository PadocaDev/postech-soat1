package com.padocadev.interfaces.pagamento;

import com.padocadev.entities.pedido.Pedido;

public interface NotificaPagamentoRepositorio {

    void notifica(Pedido pedidoCriado);
}
