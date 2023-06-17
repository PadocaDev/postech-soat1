package com.padocadev.produto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.padocadev.TestContainerTesteDeIntegracao;
import com.padocadev.aplicacao.requisicao.produto.ProdutoRequisicao;
import com.padocadev.dominio.entidade.produto.Produto;
import com.padocadev.dominio.porta.produto.CriaProdutoCasoDeUsoPorta;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static com.padocadev.dominio.entidade.produto.Categoria.ACOMPANHAMENTO;
import static com.padocadev.dominio.entidade.produto.Categoria.SOBREMESA;
import static java.math.BigDecimal.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
class ProdutoControladorTeste extends TestContainerTesteDeIntegracao {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CriaProdutoCasoDeUsoPorta criaProdutoCasoDeUsoPorta;

    @Test
    @Transactional
    void deve_retornar_informacoes_corretas_de_produto_existente_apos_a_busca_por_id() throws Exception {
        ProdutoRequisicao produtoRequisicao = new ProdutoRequisicao("Sorvete", SOBREMESA, TEN);
        Produto produto = criaProdutoCasoDeUsoPorta.criar(produtoRequisicao.converteParaProduto());

        mockMvc.perform(get("/produtos/{produtoId}", produto.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.nome").value(produtoRequisicao.nome()))
                .andExpect(jsonPath("$.categoria").value(produtoRequisicao.categoria().toString()))
                .andExpect(jsonPath("$.preco").value(produtoRequisicao.preco()));
    }

    @Test
    @Transactional
    void deve_retornar_o_erro_produto_não_existe_quando_não_existir_um_produto_com_o_id_informado() throws Exception {
        ProdutoRequisicao produtoRequisicao = new ProdutoRequisicao("Sorvete", SOBREMESA, TEN);
        criaProdutoCasoDeUsoPorta.criar(produtoRequisicao.converteParaProduto());

        mockMvc.perform(get("/produtos/{produtoId}", 12345678911l))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.erro").value("Produto com o id informado não existe!"));
    }

    @Test
    @Transactional
    void deve_retornar_informacoes_corretas_do_produto_apos_a_criacao_do_mesmo() throws Exception {
        ProdutoRequisicao produtoRequisicao = new ProdutoRequisicao("Sorvete", SOBREMESA, TEN);

         mockMvc.perform(post("/produtos")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(objectMapper.writeValueAsString(produtoRequisicao)))
                 .andExpect(status().isCreated())
                 .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                 .andExpect(jsonPath("$.id").isNotEmpty())
                 .andExpect(jsonPath("$.nome").value(produtoRequisicao.nome()))
                 .andExpect(jsonPath("$.categoria").value(produtoRequisicao.categoria().toString()))
                 .andExpect(jsonPath("$.preco").value(produtoRequisicao.preco()));
    }

    @Test
    @Transactional
    void deve_retornar_o_erro_ja_existe_produto_quando_tentar_criar_um_produto_com_nome_ja_existente() throws Exception {
        ProdutoRequisicao produtoRequisicao = new ProdutoRequisicao("Sorvete", SOBREMESA, TEN);
        criaProdutoCasoDeUsoPorta.criar(produtoRequisicao.converteParaProduto());

        ProdutoRequisicao novoProdutoRequisicao = new ProdutoRequisicao("Sorvete", ACOMPANHAMENTO, valueOf(13.50));

        mockMvc.perform(post("/produtos")
             .contentType(MediaType.APPLICATION_JSON)
             .content(objectMapper.writeValueAsString(novoProdutoRequisicao)))
             .andExpect(status().isConflict())
             .andExpect(content().contentType(MediaType.APPLICATION_JSON))
             .andExpect(jsonPath("$.erro").value("Produto com o dados informados já existe!"));
    }

    @Test
    @Transactional
    void deve_retornar_informacoes_corretas_de_produto_existente_apos_a_edicao() throws Exception {
        ProdutoRequisicao produtoRequisicao = new ProdutoRequisicao("Sorvete", SOBREMESA, TEN);
        Produto produto = criaProdutoCasoDeUsoPorta.criar(produtoRequisicao.converteParaProduto());

        ProdutoRequisicao produtoParaEdicaoRequisicao = new ProdutoRequisicao("Pastel de nata", SOBREMESA, TEN);
        mockMvc.perform(post("/produtos/{produtoId}/edita", produto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produtoParaEdicaoRequisicao)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.nome").value(produtoParaEdicaoRequisicao.nome()))
                .andExpect(jsonPath("$.categoria").value(produtoParaEdicaoRequisicao.categoria().toString()))
                .andExpect(jsonPath("$.preco").value(produtoParaEdicaoRequisicao.preco()));
    }

    @Test
    @Transactional
    void deve_retornar_o_erro_ja_existe_produto_quando_tentar_editar_um_produto_com_nome_ja_existente() throws Exception {
        ProdutoRequisicao produtoRequisicao = new ProdutoRequisicao("Sorvete", SOBREMESA, TEN);
        criaProdutoCasoDeUsoPorta.criar(produtoRequisicao.converteParaProduto());

        ProdutoRequisicao produtoASerEditadoRequisicao = new ProdutoRequisicao("Pastel de nata", SOBREMESA, TEN);
        Produto produto = criaProdutoCasoDeUsoPorta.criar(produtoASerEditadoRequisicao.converteParaProduto());

        ProdutoRequisicao produtoParaEdicaoRequisicao = new ProdutoRequisicao("Sorvete", ACOMPANHAMENTO, TWO);
        mockMvc.perform(post("/produtos/{produtoId}/edita", produto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produtoParaEdicaoRequisicao)))
                .andExpect(status().isConflict())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.erro").value("Produto com o dados informados já existe!"));
    }
}
