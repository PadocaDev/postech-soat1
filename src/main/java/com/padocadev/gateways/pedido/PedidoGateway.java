package com.padocadev.gateways.pedido;

import com.padocadev.entities.pedido.Pedido;
import com.padocadev.interfaces.pedido.PedidoGatewayInterface;
import com.padocadev.interfaces.pedido.PedidoRepositorio;

import java.util.List;
import java.util.Optional;

public class PedidoGateway implements PedidoGatewayInterface {

    private final PedidoRepositorio pedidoRepositorio;

    public PedidoGateway(PedidoRepositorio pedidoRepositorio) {
        this.pedidoRepositorio = pedidoRepositorio;
    }

    @Override
    public Pedido criar(Pedido pedido) {
        return pedidoRepositorio.criar(pedido);
    }

    @Override
    public List<Pedido> buscarTodosPedidosNaoFinalizados() {
        return pedidoRepositorio.buscarTodosPedidosNaoFinalizados();
    }

    @Override
    public Optional<Pedido> buscarPedidoPorId(Long idPedido) {
        return pedidoRepositorio.buscarPedidoPorId(idPedido);
    }

    @Override
    public void salva(Pedido pedido) {
        pedidoRepositorio.salva(pedido);
    }
}
