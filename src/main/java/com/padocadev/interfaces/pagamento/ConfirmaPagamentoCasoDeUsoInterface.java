package com.padocadev.interfaces.pagamento;

import com.padocadev.entities.pagamento.PagamentoStatus;

public interface ConfirmaPagamentoCasoDeUsoInterface {

    void confirmaPagamento(Long idPedido, PagamentoStatus pagamentoStatus, PagamentoGatewayInterface pagamentoGatewayInterface);
}
