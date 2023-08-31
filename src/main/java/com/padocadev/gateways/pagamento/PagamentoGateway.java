package com.padocadev.gateways.pagamento;

import com.padocadev.entities.pagamento.Pagamento;
import com.padocadev.interfaces.pagamento.PagamentoGatewayInterface;
import com.padocadev.interfaces.pagamento.PagamentoRepositorio;

import java.util.Optional;

public class PagamentoGateway implements PagamentoGatewayInterface {

    private final PagamentoRepositorio pagamentoRepositorio;

    public PagamentoGateway(PagamentoRepositorio pagamentoRepositorio) {
        this.pagamentoRepositorio = pagamentoRepositorio;
    }

    @Override
    public Pagamento cria(Pagamento pagamento) {
        return pagamentoRepositorio.cria(pagamento);
    }

    @Override
    public Optional<Pagamento> buscaPorPedidoId(Long pedidoId) {
       return pagamentoRepositorio.buscaPorPedidoId(pedidoId);
    }

    @Override
    public void salva(Pagamento pagamento) {
        pagamentoRepositorio.salva(pagamento);
    }
}
