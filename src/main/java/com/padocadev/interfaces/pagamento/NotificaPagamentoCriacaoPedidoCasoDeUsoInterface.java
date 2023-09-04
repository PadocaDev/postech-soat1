package com.padocadev.interfaces.pagamento;


import com.padocadev.entities.pedido.Pedido;

public interface NotificaPagamentoCriacaoPedidoCasoDeUsoInterface {

    void notifica(Pedido pedido, NotificaPagamentoGatewayInterface notificaPagamentoGateway);
}
