package com.padocadev.gateways.pagamento;

import com.padocadev.entities.pagamento.Pagamento;
import com.padocadev.interfaces.pagamento.PagamentoGatewayInterface;
import com.padocadev.interfaces.pagamento.PagamentoRepositorio;

public class PagamentoGateway implements PagamentoGatewayInterface {

    private final PagamentoRepositorio pagamentoRepositorio;

    public PagamentoGateway(PagamentoRepositorio pagamentoRepositorio) {
        this.pagamentoRepositorio = pagamentoRepositorio;
    }

    @Override
    public void cria(Pagamento pagamento) {
        pagamentoRepositorio.cria(pagamento);
    }
}
