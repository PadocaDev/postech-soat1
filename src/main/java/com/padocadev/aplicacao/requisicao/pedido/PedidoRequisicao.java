package com.padocadev.aplicacao.requisicao.pedido;

import com.padocadev.dominio.entidade.cliente.Cliente;
import com.padocadev.dominio.entidade.pedido.Pedido;
import com.padocadev.dominio.entidade.produto.Produto;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record PedidoRequisicao(
        @NotBlank List<Produto> produtos,
        @NotBlank Cliente cliente){

    public Pedido converteParaPedido() {
        return new Pedido(produtos, cliente.getId());
    }

    public Long getClienteId() {
        return cliente.getId();
    }
}
