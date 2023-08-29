package com.padocadev.controllers;

import com.padocadev.adapters.resposta.pedido.PedidoRespostaAdaptador;
import com.padocadev.interfaces.cliente.ClienteGatewayInterface;
import com.padocadev.interfaces.pagamento.GeraCodigoQRCasoDeUsoInterface;
import com.padocadev.interfaces.pagamento.NotificaPagamentoCriacaoPedidoCasoDeUsoInterface;
import com.padocadev.interfaces.pedido.*;
import com.padocadev.interfaces.produto.ProdutoGatewayInterface;
import com.padocadev.entities.pedido.objetosDeValor.PedidoRequisicao;
import com.padocadev.entities.pedido.Pedido;

import java.awt.image.BufferedImage;
import java.util.List;

public class PedidoControlador implements PedidoControladorInterface {

    private final CriaPedidoCasoDeUsoInterface criaPedidoCasoDeUso;
    private final BuscaPedidoCasoDeUsoInterface buscaPedidoCasoDeUso;
    private final NotificaPagamentoCriacaoPedidoCasoDeUsoInterface notificaPagamentoCriacaoPedidoCasoDeUso;
    private final GeraCodigoQRCasoDeUsoInterface geraCodigoQRCasoDeUso;
    private final PedidoGatewayInterface pedidoGateway;
    private final ProdutoGatewayInterface produtoGateway;
    private final ClienteGatewayInterface clienteGateway;

    public PedidoControlador(CriaPedidoCasoDeUsoInterface criaPedidoCasoDeUso,
                             BuscaPedidoCasoDeUsoInterface buscaPedidoCasoDeUso,
                             NotificaPagamentoCriacaoPedidoCasoDeUsoInterface notificaPagamentoCriacaoPedidoCasoDeUso,
                             GeraCodigoQRCasoDeUsoInterface geraCodigoQRCasoDeUso,
                             PedidoGatewayInterface pedidoGateway,
                             ProdutoGatewayInterface produtoGateway,
                             ClienteGatewayInterface clienteGateway) {
        this.criaPedidoCasoDeUso = criaPedidoCasoDeUso;
        this.buscaPedidoCasoDeUso = buscaPedidoCasoDeUso;
        this.notificaPagamentoCriacaoPedidoCasoDeUso = notificaPagamentoCriacaoPedidoCasoDeUso;
        this.geraCodigoQRCasoDeUso = geraCodigoQRCasoDeUso;
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
    public BufferedImage criaPedido(PedidoRequisicao pedidoRequisicao) {
        Pedido pedidoCriado = criaPedidoCasoDeUso.criar(pedidoRequisicao, pedidoGateway, produtoGateway, clienteGateway);
        notificaPagamentoCriacaoPedidoCasoDeUso.notifica(pedidoCriado);
        return geraCodigoQRCasoDeUso.gera(pedidoCriado);
    }
}
