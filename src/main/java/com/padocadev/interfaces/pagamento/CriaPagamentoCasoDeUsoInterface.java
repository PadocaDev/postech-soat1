package com.padocadev.interfaces.pagamento;

import com.padocadev.entities.pagamento.Pagamento;
import com.padocadev.entities.pedido.Pedido;

public interface CriaPagamentoCasoDeUsoInterface {

    Pagamento cria(Pedido pedidoCriado, PagamentoGatewayInterface pagamentoGatewayInterface);
}
