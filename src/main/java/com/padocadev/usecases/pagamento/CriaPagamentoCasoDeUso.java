package com.padocadev.usecases.pagamento;

import com.padocadev.entities.pagamento.Pagamento;
import com.padocadev.entities.pedido.Pedido;
import com.padocadev.interfaces.pagamento.CriaPagamentoCasoDeUsoInterface;
import com.padocadev.interfaces.pagamento.PagamentoGatewayInterface;

public class CriaPagamentoCasoDeUso implements CriaPagamentoCasoDeUsoInterface {

    @Override
    public void cria(Pedido pedidoCriado, PagamentoGatewayInterface pagamentoGatewayInterface) {
        Pagamento pagamento = new Pagamento(pedidoCriado);
        pagamentoGatewayInterface.cria(pagamento);
    }
}
