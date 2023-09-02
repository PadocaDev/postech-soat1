package com.padocadev.pagamento;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.padocadev.TestContainerTesteDeIntegracao;
import com.padocadev.adapters.requisicao.pagamento.ConfirmacaoPagamentoAdaptador;
import com.padocadev.entities.pagamento.Pagamento;
import com.padocadev.entities.pedido.Pedido;
import com.padocadev.entities.pedido.objetosDeValor.PedidoRequisicao;
import com.padocadev.entities.pedido.objetosDeValor.ProdutosDoPedidoRequisicao;
import com.padocadev.entities.produto.Produto;
import com.padocadev.interfaces.cliente.ClienteGatewayInterface;
import com.padocadev.interfaces.pagamento.CriaPagamentoCasoDeUsoInterface;
import com.padocadev.interfaces.pagamento.PagamentoGatewayInterface;
import com.padocadev.interfaces.pedido.CriaPedidoCasoDeUsoInterface;
import com.padocadev.interfaces.pedido.PedidoGatewayInterface;
import com.padocadev.interfaces.produto.CriaProdutoCasoDeUsoInterface;
import com.padocadev.interfaces.produto.ProdutoGatewayInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.padocadev.entities.pagamento.PagamentoStatus.APROVADO;
import static com.padocadev.entities.pagamento.PagamentoStatus.PENDENTE;
import static com.padocadev.entities.pedido.Status.AGUARDANDO_PAGAMENTO;
import static com.padocadev.entities.pedido.Status.RECEBIDO;
import static com.padocadev.entities.produto.Categoria.ACOMPANHAMENTO;
import static java.math.BigDecimal.TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
public class PagamentoApiTeste extends TestContainerTesteDeIntegracao {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CriaProdutoCasoDeUsoInterface criaProdutoCasoDeUso;

    @Autowired
    private CriaPedidoCasoDeUsoInterface criaPedidoCasoDeUso;

    @Autowired
    private ProdutoGatewayInterface produtoGateway;

    @Autowired
    private PedidoGatewayInterface pedidoGateway;

    @Autowired
    private ClienteGatewayInterface clienteGateway;

    @Autowired
    private CriaPagamentoCasoDeUsoInterface criaPagamentoCasoDeUso;

    @Autowired
    private PagamentoGatewayInterface pagamentoGateway;

    @Test
    @Transactional
    void recebeConfirmacaoPagamento__deve_retornar_204_e_atualizar_o_status_do_pagamento_e_do_pedido() throws Exception {

        Produto primeiroProduto = new Produto("Batata Frita", ACOMPANHAMENTO, TWO);

        Produto primeiroProdutoSalvo = criaProdutoCasoDeUso.cria(primeiroProduto, produtoGateway);

        List<ProdutosDoPedidoRequisicao> produtosPedidos = new ArrayList<>();
        produtosPedidos.add(new ProdutosDoPedidoRequisicao(primeiroProdutoSalvo.getId(), 1));

        PedidoRequisicao pedidoRequisicao = new PedidoRequisicao(produtosPedidos, "12345678910");

        Pedido pedidoCriado = criaPedidoCasoDeUso.criar(pedidoRequisicao, pedidoGateway, produtoGateway, clienteGateway);

        Pagamento pagamentoCriado = criaPagamentoCasoDeUso.cria(pedidoCriado, pagamentoGateway);

        assertThat(pagamentoCriado.getStatus()).isEqualTo(PENDENTE);
        assertThat(pedidoCriado.getStatus()).isEqualTo(AGUARDANDO_PAGAMENTO);

        ConfirmacaoPagamentoAdaptador confirmacaoPagamentoAdaptador = new ConfirmacaoPagamentoAdaptador(pedidoCriado.getId(), "APROVADO");

        mockMvc.perform(post("/pagamentos/confirma-pagamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(confirmacaoPagamentoAdaptador)))
                .andExpect(status().isNoContent());

        Optional<Pagamento> pagamentoAposAtualizacao = pagamentoGateway.buscaPorPedidoId(pedidoCriado.getId());
        assertThat(pagamentoAposAtualizacao.get().getStatus()).isEqualTo(APROVADO);

        Optional<Pedido> pedidoAposAtualizacaoDoPagamento = pedidoGateway.buscarPedidoPorId(pedidoCriado.getId());
        assertThat(pedidoAposAtualizacaoDoPagamento.get().getStatus()).isEqualTo(RECEBIDO);
    }

    @Test
    @Transactional
    void recebeConfirmacaoPagamento__deve_retornar_o_erro_pagamento_nao_existe_quando_não_existir_pagamento_com_o_id_do_pedido_informado() throws Exception {
        Produto primeiroProduto = new Produto("Batata Frita", ACOMPANHAMENTO, TWO);

        Produto primeiroProdutoSalvo = criaProdutoCasoDeUso.cria(primeiroProduto, produtoGateway);

        List<ProdutosDoPedidoRequisicao> produtosPedidos = new ArrayList<>();
        produtosPedidos.add(new ProdutosDoPedidoRequisicao(primeiroProdutoSalvo.getId(), 1));

        PedidoRequisicao pedidoRequisicao = new PedidoRequisicao(produtosPedidos, "12345678910");

        Pedido pedidoCriado = criaPedidoCasoDeUso.criar(pedidoRequisicao, pedidoGateway, produtoGateway, clienteGateway);

        Pagamento pagamentoCriado = criaPagamentoCasoDeUso.cria(pedidoCriado, pagamentoGateway);

        assertThat(pagamentoCriado.getStatus()).isEqualTo(PENDENTE);

        Long idPedidoInexistente = pedidoCriado.getId() + 1;
        ConfirmacaoPagamentoAdaptador confirmacaoPagamentoAdaptador = new ConfirmacaoPagamentoAdaptador(idPedidoInexistente, "APROVADO");

        mockMvc.perform(post("/pagamentos/confirma-pagamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(confirmacaoPagamentoAdaptador)))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.erro").value("Pagamento com o pedidoId informado não existe!"));
    }

    @Test
    @Transactional
    void consultaStatusDoPagamentoDoPedido__deve_retornar_corretamente_o_status_do_pagamento_do_pedido() throws Exception{
        Produto primeiroProduto = new Produto("Batata Frita", ACOMPANHAMENTO, TWO);

        Produto primeiroProdutoSalvo = criaProdutoCasoDeUso.cria(primeiroProduto, produtoGateway);

        List<ProdutosDoPedidoRequisicao> produtosPedidos = new ArrayList<>();
        produtosPedidos.add(new ProdutosDoPedidoRequisicao(primeiroProdutoSalvo.getId(), 1));

        PedidoRequisicao pedidoRequisicao = new PedidoRequisicao(produtosPedidos, "12345678910");

        Pedido pedidoCriado = criaPedidoCasoDeUso.criar(pedidoRequisicao, pedidoGateway, produtoGateway, clienteGateway);

        Pagamento pagamentoCriado = criaPagamentoCasoDeUso.cria(pedidoCriado, pagamentoGateway);

        mockMvc.perform(get("/pagamentos/consulta-status-pagamento/pedido/{pedidoId}", pedidoCriado.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.pagamentoStatus").value("PENDENTE"));
    }

    @Test
    @Transactional
    void consultaStatusDoPagamentoDoPedido__deve_retornar_o_erro_pedido_nao_existe_quando_não_existir_pedido_com_o_id_informado() throws Exception {
        mockMvc.perform(get("/pagamentos/consulta-status-pagamento/pedido/{pedidoId}", 2299))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.erro").value("Pagamento com o pedidoId informado não existe!"));
    }

}
