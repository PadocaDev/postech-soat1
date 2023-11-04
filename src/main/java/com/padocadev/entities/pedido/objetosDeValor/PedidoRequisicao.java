package com.padocadev.entities.pedido.objetosDeValor;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public final class PedidoRequisicao {
    private final @NotNull List<ProdutosDoPedidoRequisicao> produtosPedidos;
    private String cpfCliente;

    public PedidoRequisicao(@NotNull List<ProdutosDoPedidoRequisicao> produtosPedidos, String cpfCliente) {
        this.produtosPedidos = produtosPedidos;
        this.cpfCliente = cpfCliente;
    }

    public @NotNull List<ProdutosDoPedidoRequisicao> produtosPedidos() {
        return produtosPedidos;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

}
