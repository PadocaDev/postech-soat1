package com.padocadev.infraestrutura.adaptador.repositorio.pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepositorioJpa extends JpaRepository<PedidoEntidadeJpa, Long> {

    @Query(value = "SELECT * FROM Pedido p WHERE p.status <> 'FINALIZADO'", nativeQuery = true)
    List<PedidoEntidadeJpa> buscarTodosPedidosNaoConcluidos();
}
