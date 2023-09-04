package com.padocadev.pedido;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.padocadev.TestContainerTesteDeIntegracao;
import com.padocadev.adapters.resposta.pedido.PedidoRespostaAdaptador;
import com.padocadev.interfaces.cliente.ClienteGatewayInterface;
import com.padocadev.interfaces.pedido.*;
import com.padocadev.interfaces.produto.CriaProdutoCasoDeUsoInterface;
import com.padocadev.interfaces.produto.ProdutoGatewayInterface;
import com.padocadev.entities.pedido.objetosDeValor.PedidoRequisicao;
import com.padocadev.entities.pedido.objetosDeValor.ProdutosDoPedidoRequisicao;
import com.padocadev.entities.pedido.Pedido;
import com.padocadev.entities.produto.Produto;
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

import static com.padocadev.entities.produto.Categoria.ACOMPANHAMENTO;
import static com.padocadev.entities.produto.Categoria.SOBREMESA;
import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
public class PedidoApiTeste extends TestContainerTesteDeIntegracao {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CriaProdutoCasoDeUsoInterface criaProdutoCasoDeUso;

    @Autowired
    private BuscaPedidoCasoDeUsoInterface buscaPedidoCasoDeUso;

    @Autowired
    private CriaPedidoCasoDeUsoInterface criaPedidoCasoDeUso;

    @Autowired
    private ProdutoGatewayInterface produtoGateway;

    @Autowired
    private PedidoGatewayInterface pedidoGateway;

    @Autowired
    private ClienteGatewayInterface clienteGateway;


    @Test
    @Transactional
    void deve_retornar_informacoes_corretas_do_pedido_criado() throws Exception {
        Produto primeiroProduto = new Produto("Batata Frita", ACOMPANHAMENTO, TWO);
        Produto segundoProduto = new Produto("Sorvete", SOBREMESA, TEN);

        Produto primeiroProdutoSalvo = criaProdutoCasoDeUso.cria(primeiroProduto, produtoGateway);
        Produto segundoProdutoSalvo = criaProdutoCasoDeUso.cria(segundoProduto, produtoGateway);

        List<ProdutosDoPedidoRequisicao> produtosPedidos = new ArrayList<>();
        produtosPedidos.add(new ProdutosDoPedidoRequisicao(primeiroProdutoSalvo.getId(), 1));
        produtosPedidos.add(new ProdutosDoPedidoRequisicao(segundoProdutoSalvo.getId(), 2));

        PedidoRequisicao pedidoRequisicao = new PedidoRequisicao(produtosPedidos, "12345678910");

        Pedido pedidoCriado = criaPedidoCasoDeUso.criar(pedidoRequisicao, pedidoGateway, produtoGateway, clienteGateway);
        PedidoRespostaAdaptador pedidoRespostaAdaptador = new PedidoRespostaAdaptador(pedidoCriado);

        mockMvc.perform(post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pedidoRequisicao)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.IMAGE_PNG_VALUE));
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

        Produto primeiroProdutoSalvo = criaProdutoCasoDeUso.cria(primeiroProduto, produtoGateway);
        Produto segundoProdutoSalvo = criaProdutoCasoDeUso.cria(segundoProduto, produtoGateway);

        List<ProdutosDoPedidoRequisicao> produtosPedidos = new ArrayList<>();
        produtosPedidos.add(new ProdutosDoPedidoRequisicao(primeiroProdutoSalvo.getId(), 1));
        produtosPedidos.add(new ProdutosDoPedidoRequisicao(segundoProdutoSalvo.getId(), 2));

        PedidoRequisicao pedidoRequisicao = new PedidoRequisicao(produtosPedidos, "12345678910");

        Pedido pedidoCriado = criaPedidoCasoDeUso.criar(pedidoRequisicao, pedidoGateway, produtoGateway, clienteGateway);
        PedidoRespostaAdaptador pedidoRespostaAdaptador = new PedidoRespostaAdaptador(pedidoCriado);

        List<Pedido> pedidos = buscaPedidoCasoDeUso.buscarTodosPedidosNaoFinalizados(pedidoGateway);
        List<PedidoRespostaAdaptador> todosPedidos = pedidos.stream().map(PedidoRespostaAdaptador::new).toList();

        mockMvc.perform(get("/pedidos/todos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].numeroPedido").value(todosPedidos.get(0).numeroPedido()))
                .andExpect(jsonPath("$[0].itensPedido.[0].nomeProduto").value(pedidoRespostaAdaptador.itensPedido().get(0).nomeProduto()))
                .andExpect(jsonPath("$[0].itensPedido.[0].precoUnitario").value(pedidoRespostaAdaptador.itensPedido().get(0).precoUnitario()))
                .andExpect(jsonPath("$[0].itensPedido.[0].quantidade").value(pedidoRespostaAdaptador.itensPedido().get(0).quantidade()))
                .andExpect(jsonPath("$[0].itensPedido.[1].nomeProduto").value(pedidoRespostaAdaptador.itensPedido().get(1).nomeProduto()))
                .andExpect(jsonPath("$[0].itensPedido.[1].precoUnitario").value(pedidoRespostaAdaptador.itensPedido().get(1).precoUnitario()))
                .andExpect(jsonPath("$[0].itensPedido.[1].quantidade").value(pedidoRespostaAdaptador.itensPedido().get(1).quantidade()))
                .andExpect(jsonPath("$[0].valorTotal").value(pedidoRespostaAdaptador.valorTotal()))
                .andExpect(jsonPath("$[0].status").value(pedidoRespostaAdaptador.status().toString()))
                .andExpect(jsonPath("$[0].dataDeAtualizacao").value(pedidoRespostaAdaptador.dataDeAtualizacao()));
    }
}
