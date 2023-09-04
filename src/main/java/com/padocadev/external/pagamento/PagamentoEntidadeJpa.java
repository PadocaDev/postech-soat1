package com.padocadev.external.pagamento;

import com.padocadev.entities.pagamento.Pagamento;
import com.padocadev.entities.pagamento.PagamentoStatus;
import com.padocadev.external.pedido.PedidoEntidadeJpa;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Pagamento")
public class PagamentoEntidadeJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "increment")
    private Long id;

    @OneToOne
    private PedidoEntidadeJpa pedido;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PagamentoStatus status;

    @Deprecated
    public PagamentoEntidadeJpa() {
    }

    public PagamentoEntidadeJpa(Pagamento pagamento) {
        this.pedido = new PedidoEntidadeJpa(pagamento.getPedidoId());
        this.status = pagamento.getStatus();
    }
}
