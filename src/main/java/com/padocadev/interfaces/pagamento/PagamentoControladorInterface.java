package com.padocadev.interfaces.pagamento;

import com.padocadev.adapters.requisicao.pagamento.ConfirmacaoPagamentoAdaptador;

public interface PagamentoControladorInterface {

    void recebeConfirmacaoPagamento(ConfirmacaoPagamentoAdaptador confirmacaoPagamentoAdaptador);
    String consultaStatusDoPagamentoDoPedido(Long pedidoId);
}
