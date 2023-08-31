package com.padocadev.usecases.pagamento;

import com.padocadev.entities.pagamento.Pagamento;
import com.padocadev.entities.pagamento.PagamentoStatus;
import com.padocadev.exceptions.pagamento.PagamentoNaoExisteExcecao;
import com.padocadev.interfaces.pagamento.ConsultaStatusDoPagamentoDoPedidoCasoDeUsoInterface;
import com.padocadev.interfaces.pagamento.PagamentoGatewayInterface;

public class ConsultaStatusDoPagamentoDoPedidoCasoDeUso implements ConsultaStatusDoPagamentoDoPedidoCasoDeUsoInterface {

    @Override
    public PagamentoStatus consultaStatusDoPagamentoDoPedido(Long pedidoId, PagamentoGatewayInterface pagamentoGateway) {
        Pagamento pagamento = pagamentoGateway.buscaPorPedidoId(pedidoId).orElseThrow(PagamentoNaoExisteExcecao::new);
        return pagamento.getStatus();
    }
}
