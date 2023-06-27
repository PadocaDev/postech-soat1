package com.padocadev.dominio.porta.pedido;

import com.padocadev.dominio.entidade.pedido.Pedido;

import java.util.List;

public interface BuscaTodosPedidosCasoDeUsoPorta {

    List<Pedido> buscarTodosPedidosNaoFinalizados();
}
