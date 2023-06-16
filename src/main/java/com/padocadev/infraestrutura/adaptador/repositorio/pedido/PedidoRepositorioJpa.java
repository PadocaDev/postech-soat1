package com.padocadev.infraestrutura.adaptador.repositorio.pedido;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepositorioJpa extends JpaRepository<PedidoEntidadeJpa, Long> {
}
