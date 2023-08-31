package com.padocadev.interfaces.pagamento;

import com.padocadev.entities.pedido.Pedido;

public interface CriaPagamentoCasoDeUsoInterface {

    void cria(Pedido pedidoCriado, PagamentoGatewayInterface pagamentoGatewayInterface);
}
