package com.padocadev.interfaces.pedido;

import com.padocadev.entities.pedido.Pedido;
import com.padocadev.interfaces.cliente.ClienteGatewayInterface;
import com.padocadev.interfaces.produto.ProdutoGatewayInterface;
import com.padocadev.entities.pedido.objetosDeValor.PedidoRequisicao;

public interface CriaPedidoCasoDeUsoInterface {
    Pedido criar(PedidoRequisicao pedido, PedidoGatewayInterface pedidoGatewayInterface,
                 ProdutoGatewayInterface produtoGatewayInterface, ClienteGatewayInterface clienteGatewayInterface);
}
