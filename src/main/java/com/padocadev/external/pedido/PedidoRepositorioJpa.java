package com.padocadev.external.pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepositorioJpa extends JpaRepository<PedidoEntidadeJpa, Long> {

    @Query(value = """
        with
        PedidosProntos as (
        select *
        from Pedido p
        where p.status = 'PRONTO'
        order by dataPedido 
        ),
        PedidosEmPreparacao as (
        select *
        from Pedido p
        where status = 'EM_PREPARACAO'
        order by dataPedido
        ),
        PedidosRecebidos as (
        select *
        from Pedido 
        where status = 'RECEBIDO'
        order by dataPedido
        )
        select * from PedidosProntos
        union
        select * from PedidosEmPreparacao
        union
        select * from PedidosRecebidos;
""", nativeQuery = true)
    List<PedidoEntidadeJpa> buscarTodosPedidosNaoFinalizados();
}
