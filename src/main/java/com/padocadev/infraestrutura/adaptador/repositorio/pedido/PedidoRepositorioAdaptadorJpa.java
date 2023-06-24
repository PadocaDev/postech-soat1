package com.padocadev.infraestrutura.adaptador.repositorio.pedido;

import com.padocadev.dominio.entidade.pedido.Pedido;
import com.padocadev.dominio.porta.pedido.PedidoRepositorioAdaptadorPorta;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoRepositorioAdaptadorJpa implements PedidoRepositorioAdaptadorPorta {

    private final PedidoRepositorioJpa pedidoRepositorioJpa;


    public PedidoRepositorioAdaptadorJpa(PedidoRepositorioJpa pedidoRepositorioJpa) {
        this.pedidoRepositorioJpa = pedidoRepositorioJpa;
    }

    @Override
    public Pedido criar(Pedido pedido) {

        PedidoEntidadeJpa novoPedido = new PedidoEntidadeJpa(pedido);
        PedidoEntidadeJpa pedidoCriado = pedidoRepositorioJpa.save(novoPedido);

        return pedidoCriado.paraPedido();
    }

    @Override
    public List<Pedido> buscarTodosPedidosNaoFinalizados() {
        return pedidoRepositorioJpa.buscarTodosPedidosNaoFinalizados().stream().map(PedidoEntidadeJpa::paraPedido).toList();
    }
}
