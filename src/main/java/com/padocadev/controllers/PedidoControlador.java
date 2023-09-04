package com.padocadev.controllers;

import com.padocadev.adapters.requisicao.pedido.AtualizaStatusDoPedidoAdaptador;
import com.padocadev.adapters.resposta.pedido.PedidoRespostaAdaptador;
import com.padocadev.entities.pedido.Status;
import com.padocadev.interfaces.cliente.ClienteGatewayInterface;
import com.padocadev.interfaces.pagamento.*;
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
    private final NotificaPagamentoGatewayInterface notificaPagamentoGateway;
    private final GeraCodigoQRGatewayInterface geraCodigoQRGateway;
    private final CriaPagamentoCasoDeUsoInterface criaPagamentoCasoDeUso;
    private final PagamentoGatewayInterface pagamentoGateway;
    private final AtualizaStatusDoPedidoCasoDeUsoInterface atualizaStatusDoPedidoCasoDeUso;

    public PedidoControlador(CriaPedidoCasoDeUsoInterface criaPedidoCasoDeUso,
                             BuscaPedidoCasoDeUsoInterface buscaPedidoCasoDeUso,
                             NotificaPagamentoCriacaoPedidoCasoDeUsoInterface notificaPagamentoCriacaoPedidoCasoDeUso,
                             GeraCodigoQRCasoDeUsoInterface geraCodigoQRCasoDeUso,
                             PedidoGatewayInterface pedidoGateway,
                             ProdutoGatewayInterface produtoGateway,
                             ClienteGatewayInterface clienteGateway, NotificaPagamentoGatewayInterface notificaPagamentoGateway,
                             GeraCodigoQRGatewayInterface geraCodigoQRGateway, CriaPagamentoCasoDeUsoInterface criaPagamentoCasoDeUso, PagamentoGatewayInterface pagamentoGateway, AtualizaStatusDoPedidoCasoDeUsoInterface atualizaStatusDoPedidoCasoDeUso) {
        this.criaPedidoCasoDeUso = criaPedidoCasoDeUso;
        this.buscaPedidoCasoDeUso = buscaPedidoCasoDeUso;
        this.notificaPagamentoCriacaoPedidoCasoDeUso = notificaPagamentoCriacaoPedidoCasoDeUso;
        this.geraCodigoQRCasoDeUso = geraCodigoQRCasoDeUso;
        this.pedidoGateway = pedidoGateway;
        this.produtoGateway = produtoGateway;
        this.clienteGateway = clienteGateway;
        this.notificaPagamentoGateway = notificaPagamentoGateway;
        this.geraCodigoQRGateway = geraCodigoQRGateway;
        this.criaPagamentoCasoDeUso = criaPagamentoCasoDeUso;
        this.pagamentoGateway = pagamentoGateway;
        this.atualizaStatusDoPedidoCasoDeUso = atualizaStatusDoPedidoCasoDeUso;
    }

    @Override
    public List<PedidoRespostaAdaptador> buscaPedidos() {
        List<Pedido> pedidos = buscaPedidoCasoDeUso.buscarTodosPedidosNaoFinalizados(pedidoGateway);
        return pedidos.stream().map(PedidoRespostaAdaptador::new).toList();
    }

    @Override
    public BufferedImage criaPedido(PedidoRequisicao pedidoRequisicao) {
        Pedido pedidoCriado = criaPedidoCasoDeUso.criar(pedidoRequisicao, pedidoGateway, produtoGateway, clienteGateway);
        criaPagamentoCasoDeUso.cria(pedidoCriado, pagamentoGateway);
        notificaPagamentoCriacaoPedidoCasoDeUso.notifica(pedidoCriado, notificaPagamentoGateway);
        return geraCodigoQRCasoDeUso.gera(pedidoCriado, geraCodigoQRGateway);
    }

    @Override
    public void atualizaStatusDoPedido(AtualizaStatusDoPedidoAdaptador atualizaStatusDoPedidoAdaptador) {
        atualizaStatusDoPedidoCasoDeUso.atualizaStatusDoPedido(atualizaStatusDoPedidoAdaptador.idPedido(),
                Status.valueOf(atualizaStatusDoPedidoAdaptador.statusDoPedido()), pedidoGateway);
    }
}
