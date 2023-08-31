package com.padocadev.interfaces.pagamento;

import com.padocadev.entities.pagamento.Pagamento;

import java.util.Optional;

public interface PagamentoRepositorio {
    Pagamento cria(Pagamento pagamento);
    Optional<Pagamento> buscaPorPedidoId(Long id);
    void salva(Pagamento pagamento);
}
