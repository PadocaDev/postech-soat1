package com.padocadev.usecases.pagamento;

import com.padocadev.entities.pagamento.Pagamento;
import com.padocadev.entities.pedido.Pedido;
import com.padocadev.interfaces.pagamento.CriaPagamentoCasoDeUsoInterface;
import com.padocadev.interfaces.pagamento.PagamentoGatewayInterface;

public class CriaPagamentoCasoDeUso implements CriaPagamentoCasoDeUsoInterface {

    @Override
    public Pagamento cria(Pedido pedidoCriado, PagamentoGatewayInterface pagamentoGatewayInterface) {
        Pagamento pagamento = new Pagamento(pedidoCriado);
        return pagamentoGatewayInterface.cria(pagamento);
    }
}
