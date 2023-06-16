package com.padocadev.infraestrutura.adaptador.repositorio.produto;

import com.padocadev.infraestrutura.adaptador.repositorio.pedido.PedidoEntidadeJpa;
import jakarta.persistence.*;

@Entity
public class ProdutoEntidadeJpa {

    @Id
    private Long id;

    @ManyToOne
    private PedidoEntidadeJpa pedido;
}
