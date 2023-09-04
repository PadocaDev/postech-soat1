package com.padocadev.exceptions.pedido;

public class PedidoNaoExisteExcecao extends RuntimeException {

    public PedidoNaoExisteExcecao() {
        super("Pedido com o pedidoId informado não existe!");
    }
}
