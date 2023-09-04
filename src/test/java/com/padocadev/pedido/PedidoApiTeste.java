package com.padocadev.pedido;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.padocadev.TestContainerTesteDeIntegracao;
import com.padocadev.adapters.requisicao.pedido.AtualizaStatusDoPedidoAdaptador;
import com.padocadev.adapters.resposta.pedido.PedidoRespostaAdaptador;
import com.padocadev.interfaces.cliente.ClienteGatewayInterface;
import com.padocadev.interfaces.pedido.*;
import com.padocadev.interfaces.produto.CriaProdutoCasoDeUsoInterface;
import com.padocadev.interfaces.produto.ProdutoGatewayInterface;
import com.padocadev.entities.pedido.objetosDeValor.PedidoRequisicao;
import com.padocadev.entities.pedido.objetosDeValor.ProdutosDoPedidoRequisicao;
import com.padocadev.entities.pedido.Pedido;
import com.padocadev.entities.produto.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.padocadev.entities.pedido.Status.*;
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

    public static final String CLIENTE_CPF = "12345678910";
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
    private AtualizaStatusDoPedidoCasoDeUsoInterface atualizaStatusDoPedidoCasoDeUso;

    @Autowired
    private ProdutoGatewayInterface produtoGateway;

    @Autowired
    private PedidoGatewayInterface pedidoGateway;

    @Autowired
    private ClienteGatewayInterface clienteGateway;


    @Test
    @Transactional
    void criaPedido__deve_retornar_informacoes_corretas_do_pedido_criado() throws Exception {
        Produto primeiroProduto = new Produto("Batata Frita", ACOMPANHAMENTO, TWO);
        Produto segundoProduto = new Produto("Sorvete", SOBREMESA, TEN);

        Produto primeiroProdutoSalvo = criaProdutoCasoDeUso.cria(primeiroProduto, produtoGateway);
         Produto segundoProdutoSalvo = criaProdutoCasoDeUso.cria(segundoProduto, produtoGateway);

        List<ProdutosDoPedidoRequisicao> produtosPedidos = new ArrayList<>();
        produtosPedidos.add(new ProdutosDoPedidoRequisicao(primeiroProdutoSalvo.getId(), 1));
        produtosPedidos.add(new ProdutosDoPedidoRequisicao(segundoProdutoSalvo.getId(), 2));

        PedidoRequisicao pedidoRequisicao = new PedidoRequisicao(produtosPedidos, CLIENTE_CPF);

        criaPedidoCasoDeUso.criar(pedidoRequisicao, pedidoGateway, produtoGateway, clienteGateway);

        mockMvc.perform(post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pedidoRequisicao)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.IMAGE_PNG_VALUE));
    }

    @Test
    void criaPedido__deve_retornar_codigo_bad_request_quando_enviar_informacoes_incorretas_do_pedido() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(post("/pedidos")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @Transactional
    void buscaPedidos__deve_retornar_informacoes_corretas_de_todos_pedidos_nao_finalizados() throws Exception {
        Produto primeiroProduto = new Produto("Batata Frita", ACOMPANHAMENTO, TWO);
        Produto segundoProduto = new Produto("Sorvete", SOBREMESA, TEN);

        Produto primeiroProdutoSalvo = criaProdutoCasoDeUso.cria(primeiroProduto, produtoGateway);
        Produto segundoProdutoSalvo = criaProdutoCasoDeUso.cria(segundoProduto, produtoGateway);

        PedidoRespostaAdaptador pedidoRecebidoRespostaAdaptador = getPedidoRecebidoRespostaAdaptador(primeiroProdutoSalvo, segundoProdutoSalvo);
        PedidoRespostaAdaptador segundoPedidoRecebidoRespostaAdaptador = getPedidoRecebidoRespostaAdaptador(primeiroProdutoSalvo, segundoProdutoSalvo);
        PedidoRespostaAdaptador pedidoProntoRespostaAdaptador = getPedidoProntoRespostaAdaptador(primeiroProdutoSalvo);
        PedidoRespostaAdaptador pedidoEmPreparacaoRespostaAdaptador = getPedidoEmPreparacaoRespostaAdaptador(segundoProdutoSalvo);
        geraPedidoAguardandoPagamentoRespostaAdaptador(primeiroProdutoSalvo, segundoProdutoSalvo);


        List<Pedido> pedidos = buscaPedidoCasoDeUso.buscarTodosPedidosNaoFinalizados(pedidoGateway);
        List<PedidoRespostaAdaptador> todosPedidos = pedidos.stream().map(PedidoRespostaAdaptador::new).toList();

        mockMvc.perform(get("/pedidos/todos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].numeroPedido").value(todosPedidos.get(0).numeroPedido()))
                .andExpect(jsonPath("$[0].itensPedido.[0].nomeProduto").value(pedidoProntoRespostaAdaptador.itensPedido().get(0).nomeProduto()))
                .andExpect(jsonPath("$[0].itensPedido.[0].precoUnitario").value(pedidoProntoRespostaAdaptador.itensPedido().get(0).precoUnitario()))
                .andExpect(jsonPath("$[0].itensPedido.[0].quantidade").value(pedidoProntoRespostaAdaptador.itensPedido().get(0).quantidade()))
                .andExpect(jsonPath("$[0].valorTotal").value(pedidoProntoRespostaAdaptador.valorTotal()))
                .andExpect(jsonPath("$[0].status").value(pedidoProntoRespostaAdaptador.status().toString()))
                .andExpect(jsonPath("$[0].dataDeAtualizacao").value(pedidoProntoRespostaAdaptador.dataDeAtualizacao()))
                .andExpect(jsonPath("$[1].itensPedido.[0].nomeProduto").value(pedidoEmPreparacaoRespostaAdaptador.itensPedido().get(0).nomeProduto()))
                .andExpect(jsonPath("$[1].itensPedido.[0].precoUnitario").value(pedidoEmPreparacaoRespostaAdaptador.itensPedido().get(0).precoUnitario()))
                .andExpect(jsonPath("$[1].itensPedido.[0].quantidade").value(pedidoEmPreparacaoRespostaAdaptador.itensPedido().get(0).quantidade()))
                .andExpect(jsonPath("$[1].valorTotal").value(pedidoEmPreparacaoRespostaAdaptador.valorTotal()))
                .andExpect(jsonPath("$[1].status").value(pedidoEmPreparacaoRespostaAdaptador.status().toString()))
                .andExpect(jsonPath("$[1].dataDeAtualizacao").value(pedidoEmPreparacaoRespostaAdaptador.dataDeAtualizacao()))
                .andExpect(jsonPath("$[2].itensPedido.[0].nomeProduto").value(pedidoRecebidoRespostaAdaptador.itensPedido().get(0).nomeProduto()))
                .andExpect(jsonPath("$[2].itensPedido.[0].precoUnitario").value(pedidoRecebidoRespostaAdaptador.itensPedido().get(0).precoUnitario()))
                .andExpect(jsonPath("$[2].itensPedido.[0].quantidade").value(pedidoRecebidoRespostaAdaptador.itensPedido().get(0).quantidade()))
                .andExpect(jsonPath("$[2].itensPedido.[1].nomeProduto").value(pedidoRecebidoRespostaAdaptador.itensPedido().get(1).nomeProduto()))
                .andExpect(jsonPath("$[2].itensPedido.[1].precoUnitario").value(pedidoRecebidoRespostaAdaptador.itensPedido().get(1).precoUnitario()))
                .andExpect(jsonPath("$[2].itensPedido.[1].quantidade").value(pedidoRecebidoRespostaAdaptador.itensPedido().get(1).quantidade()))
                .andExpect(jsonPath("$[2].valorTotal").value(pedidoRecebidoRespostaAdaptador.valorTotal()))
                .andExpect(jsonPath("$[2].status").value(pedidoRecebidoRespostaAdaptador.status().toString()))
                .andExpect(jsonPath("$[2].dataDeAtualizacao").value(pedidoRecebidoRespostaAdaptador.dataDeAtualizacao()))
                .andExpect(jsonPath("$[3].itensPedido.[0].nomeProduto").value(segundoPedidoRecebidoRespostaAdaptador.itensPedido().get(0).nomeProduto()))
                .andExpect(jsonPath("$[3].itensPedido.[0].precoUnitario").value(segundoPedidoRecebidoRespostaAdaptador.itensPedido().get(0).precoUnitario()))
                .andExpect(jsonPath("$[3].itensPedido.[0].quantidade").value(segundoPedidoRecebidoRespostaAdaptador.itensPedido().get(0).quantidade()))
                .andExpect(jsonPath("$[3].itensPedido.[1].nomeProduto").value(segundoPedidoRecebidoRespostaAdaptador.itensPedido().get(1).nomeProduto()))
                .andExpect(jsonPath("$[3].itensPedido.[1].precoUnitario").value(segundoPedidoRecebidoRespostaAdaptador.itensPedido().get(1).precoUnitario()))
                .andExpect(jsonPath("$[3].itensPedido.[1].quantidade").value(segundoPedidoRecebidoRespostaAdaptador.itensPedido().get(1).quantidade()))
                .andExpect(jsonPath("$[3].valorTotal").value(segundoPedidoRecebidoRespostaAdaptador.valorTotal()))
                .andExpect(jsonPath("$[3].status").value(segundoPedidoRecebidoRespostaAdaptador.status().toString()))
                .andExpect(jsonPath("$[3].dataDeAtualizacao").value(segundoPedidoRecebidoRespostaAdaptador.dataDeAtualizacao()));
    }

    private void geraPedidoAguardandoPagamentoRespostaAdaptador(Produto primeiroProdutoSalvo, Produto segundoProdutoSalvo) {
        List<ProdutosDoPedidoRequisicao> produtosPedidos = new ArrayList<>();
        produtosPedidos.add(new ProdutosDoPedidoRequisicao(primeiroProdutoSalvo.getId(), 1));
        produtosPedidos.add(new ProdutosDoPedidoRequisicao(segundoProdutoSalvo.getId(), 1));

        PedidoRequisicao pedidoRequisicao = new PedidoRequisicao(produtosPedidos, CLIENTE_CPF);
        criaPedidoCasoDeUso.criar(pedidoRequisicao, pedidoGateway, produtoGateway, clienteGateway);
    }

    private PedidoRespostaAdaptador getPedidoRecebidoRespostaAdaptador(Produto primeiroProdutoSalvo, Produto segundoProdutoSalvo) {
        List<ProdutosDoPedidoRequisicao> produtosPedidos = new ArrayList<>();
        produtosPedidos.add(new ProdutosDoPedidoRequisicao(primeiroProdutoSalvo.getId(), 1));
        produtosPedidos.add(new ProdutosDoPedidoRequisicao(segundoProdutoSalvo.getId(), 2));

        PedidoRequisicao pedidoRequisicao = new PedidoRequisicao(produtosPedidos, CLIENTE_CPF);

        Pedido pedidoCriado = criaPedidoCasoDeUso.criar(pedidoRequisicao, pedidoGateway, produtoGateway, clienteGateway);
        pedidoCriado = atualizaStatusDoPedidoCasoDeUso.atualizaStatusDoPedido(pedidoCriado.getId(), RECEBIDO, pedidoGateway);
        return new PedidoRespostaAdaptador(pedidoCriado);
    }

    private PedidoRespostaAdaptador getPedidoEmPreparacaoRespostaAdaptador(Produto segundoProdutoSalvo) {
        List<ProdutosDoPedidoRequisicao> produtosPedidos = new ArrayList<>();
        produtosPedidos.add(new ProdutosDoPedidoRequisicao(segundoProdutoSalvo.getId(), 1));

        PedidoRequisicao pedidoRequisicao = new PedidoRequisicao(produtosPedidos, CLIENTE_CPF);

        Pedido pedidoCriado = criaPedidoCasoDeUso.criar(pedidoRequisicao, pedidoGateway, produtoGateway, clienteGateway);
        pedidoCriado = atualizaStatusDoPedidoCasoDeUso.atualizaStatusDoPedido(pedidoCriado.getId(), EM_PREPARACAO, pedidoGateway);
        return new PedidoRespostaAdaptador(pedidoCriado);
    }

    private PedidoRespostaAdaptador getPedidoProntoRespostaAdaptador(Produto primeiroProdutoSalvo) {
        List<ProdutosDoPedidoRequisicao> produtosPedidos = new ArrayList<>();
        produtosPedidos.add(new ProdutosDoPedidoRequisicao(primeiroProdutoSalvo.getId(), 1));

        PedidoRequisicao pedidoRequisicao = new PedidoRequisicao(produtosPedidos, CLIENTE_CPF);

        Pedido pedidoCriado = criaPedidoCasoDeUso.criar(pedidoRequisicao, pedidoGateway, produtoGateway, clienteGateway);
        pedidoCriado = atualizaStatusDoPedidoCasoDeUso.atualizaStatusDoPedido(pedidoCriado.getId(), PRONTO, pedidoGateway);
        return new PedidoRespostaAdaptador(pedidoCriado);
    }

    @Test
    @Transactional
    void atualizaStatus__deve_atualizar_corretamente_o_status_de_um_pedido_existente() throws Exception{
        Produto primeiroProduto = new Produto("Batata Frita", ACOMPANHAMENTO, TWO);
        Produto segundoProduto = new Produto("Sorvete", SOBREMESA, TEN);

        Produto primeiroProdutoSalvo = criaProdutoCasoDeUso.cria(primeiroProduto, produtoGateway);
        Produto segundoProdutoSalvo = criaProdutoCasoDeUso.cria(segundoProduto, produtoGateway);

        List<ProdutosDoPedidoRequisicao> produtosPedidos = new ArrayList<>();
        produtosPedidos.add(new ProdutosDoPedidoRequisicao(primeiroProdutoSalvo.getId(), 1));
        produtosPedidos.add(new ProdutosDoPedidoRequisicao(segundoProdutoSalvo.getId(), 2));

        PedidoRequisicao pedidoRequisicao = new PedidoRequisicao(produtosPedidos, CLIENTE_CPF);

        Pedido pedidoCriado = criaPedidoCasoDeUso.criar(pedidoRequisicao, pedidoGateway, produtoGateway, clienteGateway);

        assertThat(pedidoCriado.getStatus()).isEqualTo(AGUARDANDO_PAGAMENTO);

        AtualizaStatusDoPedidoAdaptador atualizaStatusDoPedidoAdaptador = new AtualizaStatusDoPedidoAdaptador(pedidoCriado.getId(), "RECEBIDO");

        mockMvc.perform(post("/pedidos/atualiza-status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(atualizaStatusDoPedidoAdaptador)))
                .andExpect(status().isNoContent());

        Optional<Pedido> pedidoAtualizado = pedidoGateway.buscarPedidoPorId(pedidoCriado.getId());
        assertThat(pedidoAtualizado.get().getStatus()).isEqualTo(RECEBIDO);

    }

    @Test
    @Transactional
    void atualizaStatus__deve_retornar_o_erro_pedido_nao_existe_excecao_quando_o_pedido_nao_existir() throws Exception {
        AtualizaStatusDoPedidoAdaptador atualizaStatusDoPedidoAdaptador = new AtualizaStatusDoPedidoAdaptador(130L, "RECEBIDO");

        mockMvc.perform(post("/pedidos/atualiza-status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(atualizaStatusDoPedidoAdaptador)))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.erro").value("Pedido com o pedidoId informado não existe!"));
    }
}
