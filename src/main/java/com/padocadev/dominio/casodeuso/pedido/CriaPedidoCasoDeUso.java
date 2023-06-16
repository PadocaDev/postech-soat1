package com.padocadev.dominio.casodeuso.pedido;

import com.padocadev.dominio.entidade.pedido.Pedido;
import com.padocadev.dominio.porta.pedido.CriaPedidoCasoDeUsoPorta;
import com.padocadev.dominio.porta.pedido.PedidoRepositorioAdaptadorPorta;

public class CriaPedidoCasoDeUso implements CriaPedidoCasoDeUsoPorta {

    private final PedidoRepositorioAdaptadorPorta pedidoRepositorioAdaptadorPorta;

    public CriaPedidoCasoDeUso(PedidoRepositorioAdaptadorPorta pedidoRepositorioAdaptadorPorta) {
        this.pedidoRepositorioAdaptadorPorta = pedidoRepositorioAdaptadorPorta;
    }

    @Override
    public Pedido criar(Pedido pedido) {
        return pedidoRepositorioAdaptadorPorta.criar(pedido);
    }
}
