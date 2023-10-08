package com.padocadev.controllers;

import com.padocadev.adapters.requisicao.pagamento.ConfirmacaoPagamentoAdaptador;
import com.padocadev.entities.pagamento.PagamentoStatus;
import com.padocadev.interfaces.pagamento.*;
import com.padocadev.interfaces.pedido.AtualizaStatusDoPedidoCasoDeUsoInterface;
import com.padocadev.interfaces.pedido.PedidoGatewayInterface;

public class PagamentoControlador implements PagamentoControladorInterface {

    private final ConfirmaPagamentoCasoDeUsoInterface confirmaPagamentoCasoDeUso;
    private final PagamentoGatewayInterface pagamentoGateway;
    private final AtualizaStatusDoPedidoCasoDeUsoInterface atualizaStatusDoPedidoCasoDeUso;
    private final PedidoGatewayInterface pedidoGateway;
    private final ConsultaStatusDoPagamentoDoPedidoCasoDeUsoInterface consultaStatusDoPagamentoDoPedidoCasoDeUso;

    public PagamentoControlador(ConfirmaPagamentoCasoDeUsoInterface confirmaPagamentoCasoDeUso, PagamentoGatewayInterface pagamentoGateway, AtualizaStatusDoPedidoCasoDeUsoInterface atualizaStatusDoPedidoCasoDeUso, PedidoGatewayInterface pedidoGateway, ConsultaStatusDoPagamentoDoPedidoCasoDeUsoInterface consultaStatusDoPagamentoDoPedidoCasoDeUso) {
        this.confirmaPagamentoCasoDeUso = confirmaPagamentoCasoDeUso;
        this.pagamentoGateway = pagamentoGateway;
        this.atualizaStatusDoPedidoCasoDeUso = atualizaStatusDoPedidoCasoDeUso;
        this.pedidoGateway = pedidoGateway;
        this.consultaStatusDoPagamentoDoPedidoCasoDeUso = consultaStatusDoPagamentoDoPedidoCasoDeUso;
    }

    @Override
    public void recebeConfirmacaoPagamento(ConfirmacaoPagamentoAdaptador confirmacaoPagamentoAdaptador) {
        confirmaPagamentoCasoDeUso.confirmaPagamento(
            confirmacaoPagamentoAdaptador.pedidoId(),
            PagamentoStatus.valueOf(confirmacaoPagamentoAdaptador.pagamentoStatus()),
            pagamentoGateway
        );

        atualizaStatusDoPedidoCasoDeUso.atualizaStatusDoPedido(
            confirmacaoPagamentoAdaptador.pedidoId(),
            PagamentoStatus.valueOf(confirmacaoPagamentoAdaptador.pagamentoStatus()),
            pedidoGateway
        );
    }

    @Override
    public String consultaStatusDoPagamentoDoPedido(Long pedidoId) {
        PagamentoStatus pagamentoStatus = consultaStatusDoPagamentoDoPedidoCasoDeUso.consultaStatusDoPagamentoDoPedido(pedidoId, pagamentoGateway);
        return pagamentoStatus.name();
    }
}
