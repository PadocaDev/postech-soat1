package com.padocadev.external.pedido;

import com.padocadev.entities.pedido.Pedido;
import com.padocadev.interfaces.pedido.PedidoRepositorio;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PedidoRepositorioCustom implements PedidoRepositorio {

    private final PedidoRepositorioJpa pedidoRepositorioJpa;

    public PedidoRepositorioCustom(PedidoRepositorioJpa pedidoRepositorioJpa) {
        this.pedidoRepositorioJpa = pedidoRepositorioJpa;
    }

    @Override
    public Pedido criar(Pedido pedido) {
        PedidoEntidadeJpa novoPedido = new PedidoEntidadeJpa(pedido);
        PedidoEntidadeJpa pedidoCriado = pedidoRepositorioJpa.saveAndFlush(novoPedido);

        return pedidoCriado.paraPedido();
    }

    @Override
    public List<Pedido> buscarTodosPedidosNaoFinalizados() {
        return pedidoRepositorioJpa.buscarTodosPedidosNaoFinalizados().stream().map(PedidoEntidadeJpa::paraPedido).toList();
    }

    @Override
    public Optional<Pedido> buscarPedidoPorId(Long idPedido) {
        return pedidoRepositorioJpa.findById(idPedido).map(PedidoEntidadeJpa::paraPedido);
    }

    @Override
    public void salva(Pedido pedido) {
        PedidoEntidadeJpa pedidoEntidadeJpa = new PedidoEntidadeJpa(pedido);
        pedidoRepositorioJpa.save(pedidoEntidadeJpa);
    }
}
