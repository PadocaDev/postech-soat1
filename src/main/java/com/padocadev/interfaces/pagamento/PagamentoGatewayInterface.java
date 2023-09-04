package com.padocadev.interfaces.pagamento;

import com.padocadev.entities.pagamento.Pagamento;

public interface PagamentoGatewayInterface {

    void cria(Pagamento pagamento);
}
