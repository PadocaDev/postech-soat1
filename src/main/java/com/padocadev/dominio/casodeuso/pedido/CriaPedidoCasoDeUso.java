package com.padocadev.dominio.casodeuso.pedido;

import com.padocadev.aplicacao.requisicao.pedido.PedidoRequisicao;
import com.padocadev.dominio.entidade.cliente.Cliente;
import com.padocadev.dominio.entidade.pedido.ItemPedido;
import com.padocadev.dominio.entidade.pedido.Pedido;
import com.padocadev.dominio.entidade.produto.Produto;
import com.padocadev.dominio.excecao.produto.ProdutoNaoExisteExcecao;
import com.padocadev.dominio.porta.cliente.ClienteRepositorioAdaptadorPorta;
import com.padocadev.dominio.porta.pedido.CriaPedidoCasoDeUsoPorta;
import com.padocadev.dominio.porta.pedido.PedidoRepositorioAdaptadorPorta;
import com.padocadev.infraestrutura.adaptador.repositorio.produto.ProdutoRepositorioAdaptadorJpa;

public class CriaPedidoCasoDeUso implements CriaPedidoCasoDeUsoPorta {

    private final PedidoRepositorioAdaptadorPorta pedidoRepositorioAdaptadorPorta;
    private final ProdutoRepositorioAdaptadorJpa produtoRepositorioAdaptadorJpa;
    private final ClienteRepositorioAdaptadorPorta clienteRepositorioAdaptadorPorta;

    public CriaPedidoCasoDeUso(PedidoRepositorioAdaptadorPorta pedidoRepositorioAdaptadorPorta, ProdutoRepositorioAdaptadorJpa produtoRepositorioAdaptadorJpa, ClienteRepositorioAdaptadorPorta clienteRepositorioAdaptadorPorta) {
        this.pedidoRepositorioAdaptadorPorta = pedidoRepositorioAdaptadorPorta;
        this.produtoRepositorioAdaptadorJpa = produtoRepositorioAdaptadorJpa;
        this.clienteRepositorioAdaptadorPorta = clienteRepositorioAdaptadorPorta;
    }

    @Override
    public Pedido criar(PedidoRequisicao pedidoRequisicao) {
        Cliente cliente = clienteRepositorioAdaptadorPorta.buscaClientePorCpf(pedidoRequisicao.clienteCpf()).orElse(null);
        Pedido pedido = new Pedido(cliente);
        pedidoRequisicao.produtosPedidos().forEach(produtoPedido -> {
            Produto produto = produtoRepositorioAdaptadorJpa.buscaPorId(produtoPedido.getProdutoId()).orElseThrow(ProdutoNaoExisteExcecao::new);
            pedido.adicionarItem(new ItemPedido(produtoPedido.getQuantidade(), pedido, produto), produtoPedido.getQuantidade());
        });

        return pedidoRepositorioAdaptadorPorta.criar(pedido);
    }
}
