package com.padocadev.pedido;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.padocadev.TestContainerTesteDeIntegracao;
import com.padocadev.aplicacao.requisicao.pedido.PedidoRequisicao;
import com.padocadev.aplicacao.requisicao.pedido.ProdutosDoPedidoRequisicao;
import com.padocadev.dominio.entidade.pedido.Pedido;
import com.padocadev.dominio.entidade.produto.Produto;
import com.padocadev.dominio.porta.pedido.CriaPedidoCasoDeUsoPorta;
import com.padocadev.dominio.porta.produto.CriaProdutoCasoDeUsoPorta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.padocadev.dominio.entidade.produto.Categoria.ACOMPANHAMENTO;
import static com.padocadev.dominio.entidade.produto.Categoria.SOBREMESA;
import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.TWO;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PedidoControladorTeste extends TestContainerTesteDeIntegracao {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CriaPedidoCasoDeUsoPorta criaPedidoCasoDeUso;

    @Test
    @Transactional
    void deve_retornar_informacoes_corretas_do_pedido_criado() throws Exception {
        Produto primeiroProduto = new Produto("Batata Frita", ACOMPANHAMENTO, TWO);
        Produto segundoProduto = new Produto("Sorvete", SOBREMESA, TEN);

        List<ProdutosDoPedidoRequisicao> produtosPedidos = new ArrayList<>();
        produtosPedidos.add(new ProdutosDoPedidoRequisicao(primeiroProduto.getId(), 1));
        produtosPedidos.add(new ProdutosDoPedidoRequisicao(segundoProduto.getId(), 2));

        PedidoRequisicao pedidoRequisicao = new PedidoRequisicao(produtosPedidos, "12345678910");

        Pedido pedidoCriado = criaPedidoCasoDeUso.criar(pedidoRequisicao);

        mockMvc.perform(post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pedidoRequisicao)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.numeroPedido").value(pedidoCriado.getNumeroPedido()))
                .andExpect(jsonPath("$.itensPedido[0].nomeProduto").value(pedidoCriado.getItensPedido().get(0).getProduto()))
                .andExpect(jsonPath("$.itensPedido[0].precoUnitario").value(pedidoCriado.getItensPedido().get(0).getPrecoUnitario()))
                .andExpect(jsonPath("$.itensPedido[0].quantidade").value(pedidoCriado.getItensPedido().get(0).getPrecoUnitario()))
                .andExpect(jsonPath("$.itensPedido[0].nomeProduto").value(pedidoCriado.getItensPedido().get(1).getProduto()))
                .andExpect(jsonPath("$.itensPedido[0].precoUnitario").value(pedidoCriado.getItensPedido().get(1).getPrecoUnitario()))
                .andExpect(jsonPath("$.itensPedido[0].quantidade").value(pedidoCriado.getItensPedido().get(1).getPrecoUnitario()))
                .andExpect(jsonPath("$.valorTotal").value(pedidoCriado.getValorTotal()))
                .andExpect(jsonPath("$.status").value(pedidoCriado.getStatus()))
                .andExpect(jsonPath("$.dataDeAtualizacao").value(pedidoCriado.getDataDeAtualizacao()));
    }
}
