package com.padocadev.external.pagamento;

import com.padocadev.entities.pagamento.Pagamento;
import com.padocadev.interfaces.pagamento.PagamentoRepositorio;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PagamentoRepositorioCustom implements PagamentoRepositorio {

    private final PagamentoRepositorioJpa pagamentoRepositorioJpa;

    public PagamentoRepositorioCustom(PagamentoRepositorioJpa pagamentoRepositorioJpa) {
        this.pagamentoRepositorioJpa = pagamentoRepositorioJpa;
    }

    @Override
    public Pagamento cria(Pagamento pagamento) {
        PagamentoEntidadeJpa pagamentoEntidadeJpa = pagamentoRepositorioJpa.save(new PagamentoEntidadeJpa(pagamento));
        return pagamentoEntidadeJpa.paraPagamento();
    }

    @Override
    public Optional<Pagamento> buscaPorPedidoId(Long id) {
        return pagamentoRepositorioJpa.findByPedidoId(id).map(PagamentoEntidadeJpa::paraPagamento);
    }

    @Override
    public void salva(Pagamento pagamento) {
        pagamentoRepositorioJpa.save(new PagamentoEntidadeJpa(pagamento));
    }
}
