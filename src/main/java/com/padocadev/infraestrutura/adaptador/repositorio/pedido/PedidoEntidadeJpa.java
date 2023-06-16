package com.padocadev.infraestrutura.adaptador.repositorio.pedido;

import com.padocadev.dominio.entidade.pedido.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Pedido")
public class PedidoEntidadeJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "increment")
    private Long id;

    @NotNull
    private Long clienteId;

    @NotNull
    private Long numeroPedido;

    @ManyToOne
    private List<ProdutoEntidadeJpa> produtos;

    @NotNull
    private BigDecimal valorTotal;

    @NotNull
    private Status status;

    @NotNull
    private LocalDateTime dataDeAtualizacao;


}
