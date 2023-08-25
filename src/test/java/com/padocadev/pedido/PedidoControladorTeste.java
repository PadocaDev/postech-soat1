package com.padocadev.pedido;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.padocadev.TestContainerTesteDeIntegracao;
import com.padocadev.aplicacao.resposta.pedido.PedidoResposta;
import com.padocadev.dominio.casodeuso.pedido.objetosDeValor.PedidoRequisicao;
import com.padocadev.dominio.casodeuso.pedido.objetosDeValor.ProdutosDoPedidoRequisicao;
import com.padocadev.dominio.entidade.pedido.Pedido;
import com.padocadev.dominio.entidade.produto.Produto;
import com.padocadev.dominio.porta.pedido.BuscaTodosPedidosCasoDeUsoPorta;
import com.padocadev.dominio.porta.pedido.CriaPedidoCasoDeUsoPorta;
import com.padocadev.dominio.porta.produto.CriaProdutoCasoDeUsoPorta;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
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

@AutoConfigureMockMvc
public class PedidoControladorTeste extends TestContainerTesteDeIntegracao {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CriaPedidoCasoDeUsoPorta criaPedidoCasoDeUso;

    @Autowired
    BuscaTodosPedidosCasoDeUsoPorta buscaTodosPedidosCasoDeUso;

    @Autowired
    private CriaProdutoCasoDeUsoPorta criaProdutoCasoDeUso;

    @Test
    @Transactional
    void deve_retornar_informacoes_corretas_do_pedido_criado() throws Exception {
        Produto primeiroProduto = new Produto("Batata Frita", ACOMPANHAMENTO, TWO);
        Produto segundoProduto = new Produto("Sorvete", SOBREMESA, TEN);

        Produto primeiroProdutoSalvo = criaProdutoCasoDeUso.cria(primeiroProduto);
        Produto segundoProdutoSalvo = criaProdutoCasoDeUso.cria(segundoProduto);

        List<ProdutosDoPedidoRequisicao> produtosPedidos = new ArrayList<>();
        produtosPedidos.add(new ProdutosDoPedidoRequisicao(primeiroProdutoSalvo.getId(), 1));
        produtosPedidos.add(new ProdutosDoPedidoRequisicao(segundoProdutoSalvo.getId(), 2));

        PedidoRequisicao pedidoRequisicao = new PedidoRequisicao(produtosPedidos, "12345678910");

        Pedido pedidoCriado = criaPedidoCasoDeUso.criar(pedidoRequisicao);
        PedidoResposta pedidoResposta = new PedidoResposta(pedidoCriado);

        mockMvc.perform(post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pedidoRequisicao)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.numeroPedido").isNotEmpty())
                .andExpect(jsonPath("$.itensPedido[0].nomeProduto").value(pedidoResposta.itensPedido().get(0).nomeProduto()))
                .andExpect(jsonPath("$.itensPedido[0].precoUnitario").value(pedidoResposta.itensPedido().get(0).precoUnitario()))
                .andExpect(jsonPath("$.itensPedido[0].quantidade").value(pedidoResposta.itensPedido().get(0).quantidade()))
                .andExpect(jsonPath("$.itensPedido[1].nomeProduto").value(pedidoResposta.itensPedido().get(1).nomeProduto()))
                .andExpect(jsonPath("$.itensPedido[1].precoUnitario").value(pedidoResposta.itensPedido().get(1).precoUnitario()))
                .andExpect(jsonPath("$.itensPedido[1].quantidade").value(pedidoResposta.itensPedido().get(1).quantidade()))
                .andExpect(jsonPath("$.valorTotal").value(pedidoResposta.valorTotal()))
                .andExpect(jsonPath("$.status").value(pedidoResposta.status().toString()))
                .andExpect(jsonPath("$.dataDeAtualizacao").value(pedidoResposta.dataDeAtualizacao()));
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

        Produto primeiroProdutoSalvo = criaProdutoCasoDeUso.cria(primeiroProduto);
        Produto segundoProdutoSalvo = criaProdutoCasoDeUso.cria(segundoProduto);

        List<ProdutosDoPedidoRequisicao> produtosPedidos = new ArrayList<>();
        produtosPedidos.add(new ProdutosDoPedidoRequisicao(primeiroProdutoSalvo.getId(), 1));
        produtosPedidos.add(new ProdutosDoPedidoRequisicao(segundoProdutoSalvo.getId(), 2));

        PedidoRequisicao pedidoRequisicao = new PedidoRequisicao(produtosPedidos, "12345678910");

        Pedido pedidoCriado = criaPedidoCasoDeUso.criar(pedidoRequisicao);
        PedidoResposta pedidoResposta = new PedidoResposta(pedidoCriado);

        List<Pedido> pedidos = buscaTodosPedidosCasoDeUso.buscarTodosPedidosNaoFinalizados();
        List<PedidoResposta> todosPedidos = pedidos.stream().map(PedidoResposta::new).toList();

        mockMvc.perform(get("/pedidos/todos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].numeroPedido").value(todosPedidos.get(0).numeroPedido()))
                .andExpect(jsonPath("$[0].itensPedido.[0].nomeProduto").value(pedidoResposta.itensPedido().get(0).nomeProduto()))
                .andExpect(jsonPath("$[0].itensPedido.[0].precoUnitario").value(pedidoResposta.itensPedido().get(0).precoUnitario()))
                .andExpect(jsonPath("$[0].itensPedido.[0].quantidade").value(pedidoResposta.itensPedido().get(0).quantidade()))
                .andExpect(jsonPath("$[0].itensPedido.[1].nomeProduto").value(pedidoResposta.itensPedido().get(1).nomeProduto()))
                .andExpect(jsonPath("$[0].itensPedido.[1].precoUnitario").value(pedidoResposta.itensPedido().get(1).precoUnitario()))
                .andExpect(jsonPath("$[0].itensPedido.[1].quantidade").value(pedidoResposta.itensPedido().get(1).quantidade()))
                .andExpect(jsonPath("$[0].valorTotal").value(pedidoResposta.valorTotal()))
                .andExpect(jsonPath("$[0].status").value(pedidoResposta.status().toString()))
                .andExpect(jsonPath("$[0].dataDeAtualizacao").value(pedidoResposta.dataDeAtualizacao()));
    }
}
