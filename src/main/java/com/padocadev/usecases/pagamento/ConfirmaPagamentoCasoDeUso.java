package com.padocadev.usecases.pagamento;

import com.padocadev.entities.pagamento.Pagamento;
import com.padocadev.entities.pagamento.PagamentoStatus;
import com.padocadev.exceptions.pagamento.PagamentoNaoExisteExcecao;
import com.padocadev.interfaces.pagamento.ConfirmaPagamentoCasoDeUsoInterface;
import com.padocadev.interfaces.pagamento.PagamentoGatewayInterface;

public class ConfirmaPagamentoCasoDeUso implements ConfirmaPagamentoCasoDeUsoInterface {

    @Override
    public void confirmaPagamento(Long idPedido, PagamentoStatus pagamentoStatus, PagamentoGatewayInterface pagamentoGateway) {
        Pagamento pagamento = pagamentoGateway.buscaPorPedidoId(idPedido).orElseThrow(PagamentoNaoExisteExcecao::new);
        pagamento.setStatus(pagamentoStatus);
        pagamentoGateway.salva(pagamento);
    }
}
