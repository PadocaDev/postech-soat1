package com.padocadev.interfaces.pagamento;

import com.padocadev.entities.pagamento.Pagamento;

import java.util.Optional;

public interface PagamentoGatewayInterface {

    Pagamento cria(Pagamento pagamento);
    Optional<Pagamento> buscaPorPedidoId(Long pedidoId);
    void salva(Pagamento pagamento);
}
