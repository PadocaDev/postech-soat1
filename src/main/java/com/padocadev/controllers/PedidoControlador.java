package com.padocadev.controllers;

import com.padocadev.adapters.resposta.pedido.PedidoRespostaAdaptador;
import com.padocadev.interfaces.cliente.ClienteGatewayInterface;
import com.padocadev.interfaces.pedido.*;
import com.padocadev.interfaces.produto.ProdutoGatewayInterface;
import com.padocadev.entities.pedido.objetosDeValor.PedidoRequisicao;
import com.padocadev.entities.pedido.Pedido;

import java.util.List;

public class PedidoControlador implements PedidoControladorInterface {

    private final CriaPedidoCasoDeUsoInterface criaPedidoCasoDeUso;
    private final BuscaPedidoCasoDeUsoInterface buscaPedidoCasoDeUso;
    private final PedidoGatewayInterface pedidoGateway;
    private final ProdutoGatewayInterface produtoGateway;
    private final ClienteGatewayInterface clienteGateway;

    public PedidoControlador(CriaPedidoCasoDeUsoInterface criaPedidoCasoDeUso, BuscaPedidoCasoDeUsoInterface buscaPedidoCasoDeUso, PedidoGatewayInterface pedidoGateway, ProdutoGatewayInterface produtoGateway, ClienteGatewayInterface clienteGateway) {
        this.criaPedidoCasoDeUso = criaPedidoCasoDeUso;
        this.buscaPedidoCasoDeUso = buscaPedidoCasoDeUso;
        this.pedidoGateway = pedidoGateway;
        this.produtoGateway = produtoGateway;
        this.clienteGateway = clienteGateway;
    }

    @Override
    public List<PedidoRespostaAdaptador> buscaPedidos() {
        List<Pedido> pedidos = buscaPedidoCasoDeUso.buscarTodosPedidosNaoFinalizados(pedidoGateway);
        return pedidos.stream().map(PedidoRespostaAdaptador::new).toList();
    }

    @Override
    public PedidoRespostaAdaptador criaPedido(PedidoRequisicao pedidoRequisicao) {
        Pedido pedidoCriado = criaPedidoCasoDeUso.criar(pedidoRequisicao, pedidoGateway, produtoGateway, clienteGateway);
        return new PedidoRespostaAdaptador(pedidoCriado);
    }
}
