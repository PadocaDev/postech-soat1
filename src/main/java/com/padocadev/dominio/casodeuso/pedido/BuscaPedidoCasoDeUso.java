package com.padocadev.dominio.casodeuso.pedido;

import com.padocadev.dominio.entidade.pedido.Pedido;
import com.padocadev.dominio.porta.pedido.BuscaTodosPedidosCasoDeUso;
import com.padocadev.dominio.porta.pedido.PedidoRepositorioAdaptadorPorta;

import java.util.List;

public class BuscaPedidoCasoDeUso implements BuscaTodosPedidosCasoDeUso {

    private final PedidoRepositorioAdaptadorPorta pedidoRepositorioAdaptadorPorta;


    public BuscaPedidoCasoDeUso(PedidoRepositorioAdaptadorPorta pedidoRepositorioAdaptadorPorta) {
        this.pedidoRepositorioAdaptadorPorta = pedidoRepositorioAdaptadorPorta;
    }

    @Override
    public List<Pedido> buscarTodosPedidos() {
       return pedidoRepositorioAdaptadorPorta.buscarTodosPedidos();
    }
}
