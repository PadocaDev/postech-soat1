package com.padocadev.external.pagamento;

import com.padocadev.entities.pagamento.Pagamento;
import com.padocadev.interfaces.pagamento.PagamentoRepositorio;
import org.springframework.stereotype.Component;

@Component
public class PagamentoRepositorioCustom implements PagamentoRepositorio {

    private final PagamentoRepositorioJpa pagamentoRepositorioJpa;

    public PagamentoRepositorioCustom(PagamentoRepositorioJpa pagamentoRepositorioJpa) {
        this.pagamentoRepositorioJpa = pagamentoRepositorioJpa;
    }

    @Override
    public void cria(Pagamento pagamento) {
        pagamentoRepositorioJpa.save(new PagamentoEntidadeJpa(pagamento));
    }
}
