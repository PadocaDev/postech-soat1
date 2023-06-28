package com.padocadev.pedido;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.padocadev.TestContainerTesteDeIntegracao;
import com.padocadev.aplicacao.requisicao.pedido.PedidoRequisicao;
import com.padocadev.aplicacao.requisicao.pedido.ProdutosDoPedidoRequisicao;
import com.padocadev.aplicacao.resposta.pedido.PedidoResposta;
import com.padocadev.dominio.entidade.pedido.Pedido;
import com.padocadev.dominio.entidade.produto.Produto;
import com.padocadev.dominio.porta.pedido.BuscaTodosPedidosCasoDeUsoPorta;
import com.padocadev.dominio.porta.pedido.CriaPedidoCasoDeUsoPorta;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.padocadev.dominio.entidade.produto.Categoria.ACOMPANHAMENTO;
import static com.padocadev.dominio.entidade.produto.Categoria.SOBREMESA;
import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PedidoControladorTeste extends TestContainerTesteDeIntegracao {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CriaPedidoCasoDeUsoPorta criaPedidoCasoDeUso;

    @Autowired
    BuscaTodosPedidosCasoDeUsoPorta buscaTodosPedidosCasoDeUso;

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

    @Test
    void deve_retornar_codigo_bad_request_quando_informacoes_incorretas_do_pedido() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(post("/pedidos")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @Transactional
    void deve_retornar_informacoes_corretas_de_todos_pedidos_nao_finalizados() throws Exception {
        Produto primeiroProduto = new Produto("Batata Frita", ACOMPANHAMENTO, TWO);
        Produto segundoProduto = new Produto("Sorvete", SOBREMESA, TEN);

        List<ProdutosDoPedidoRequisicao> produtosPedidos = new ArrayList<>();
        produtosPedidos.add(new ProdutosDoPedidoRequisicao(primeiroProduto.getId(), 1));
        produtosPedidos.add(new ProdutosDoPedidoRequisicao(segundoProduto.getId(), 2));

        PedidoRequisicao pedidoRequisicao = new PedidoRequisicao(produtosPedidos, "12345678910");

        Pedido pedidoCriado = criaPedidoCasoDeUso.criar(pedidoRequisicao);

        List<Pedido> pedidos = buscaTodosPedidosCasoDeUso.buscarTodosPedidosNaoFinalizados();
        List<PedidoResposta> list = pedidos.stream().map(PedidoResposta::new).toList();

        mockMvc.perform(get("/clientes/todos"))
                .andExpect(status().isOk())
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
