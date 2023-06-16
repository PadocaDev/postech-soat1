package com.padocadev.infraestrutura.adaptador.repositorio.pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepositorioJpa extends JpaRepository<PedidoEntidadeJpa, Long> {
}
