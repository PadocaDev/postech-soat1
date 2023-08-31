package com.padocadev.entities.pagamento;

import com.padocadev.entities.pedido.Pedido;
import com.padocadev.external.pedido.PedidoEntidadeJpa;

public class Pagamento {

    private Long id;
    private PagamentoStatus status = PagamentoStatus.PENDENTE;
    private Pedido pedido;

    public Pagamento(Long id, PagamentoStatus status, Pedido pedido) {
        this.id = id;
        this.status = status;
        this.pedido = pedido;
    }

    public Pagamento(Pedido pedido) {
        this.pedido = pedido;
    }

    public Long getId() {
        return id;
    }

    public PagamentoStatus getStatus() {
        return status;
    }

    public void setStatus(PagamentoStatus status) {
        this.status = status;
    }

    public Long getPedidoId() {
        return pedido.getId();
    }
}
