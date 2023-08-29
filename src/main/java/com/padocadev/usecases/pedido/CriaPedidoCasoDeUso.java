package com.padocadev.usecases.pedido;

import com.padocadev.interfaces.pedido.*;
import com.padocadev.interfaces.produto.ProdutoGatewayInterface;
import com.padocadev.entities.pedido.objetosDeValor.PedidoRequisicao;
import com.padocadev.entities.cliente.Cliente;
import com.padocadev.entities.pedido.ItemPedido;
import com.padocadev.entities.pedido.Pedido;
import com.padocadev.entities.produto.Produto;
import com.padocadev.exceptions.produto.ProdutoNaoExisteExcecao;
import com.padocadev.interfaces.cliente.ClienteGatewayInterface;

public class CriaPedidoCasoDeUso implements CriaPedidoCasoDeUsoInterface {

    @Override
    public Pedido criar(PedidoRequisicao pedidoRequisicao, PedidoGatewayInterface pedidoGatewayInterface,
                        ProdutoGatewayInterface produtoGatewayInterface, ClienteGatewayInterface clienteGatewayInterface) {

        Cliente cliente = clienteGatewayInterface.buscaPorCpf(pedidoRequisicao.clienteCpf()).orElse(null);
        Pedido pedido = new Pedido(cliente);
        pedidoRequisicao.produtosPedidos().forEach(produtoPedido -> {
            Produto produto = produtoGatewayInterface.buscaPorId(produtoPedido.produtoId()).orElseThrow(ProdutoNaoExisteExcecao::new);
            pedido.adicionarItem(new ItemPedido(produtoPedido.quantidade(), produto));
        });

        return pedidoGatewayInterface.criar(pedido);
    }
}
