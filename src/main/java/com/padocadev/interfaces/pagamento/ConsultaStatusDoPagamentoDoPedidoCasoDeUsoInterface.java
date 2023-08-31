package com.padocadev.interfaces.pagamento;

import com.padocadev.entities.pagamento.PagamentoStatus;

public interface ConsultaStatusDoPagamentoDoPedidoCasoDeUsoInterface {
    PagamentoStatus consultaStatusDoPagamentoDoPedido(Long pedidoId, PagamentoGatewayInterface pagamentoGateway);
}
