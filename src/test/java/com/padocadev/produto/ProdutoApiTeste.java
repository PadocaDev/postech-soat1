package com.padocadev.produto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.padocadev.TestContainerTesteDeIntegracao;
import com.padocadev.adapters.requisicao.produto.ProdutoRequisicaoAdaptador;
import com.padocadev.entities.produto.Produto;
import com.padocadev.interfaces.produto.CriaProdutoCasoDeUsoInterface;
import com.padocadev.interfaces.produto.ProdutoGatewayInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static com.padocadev.entities.produto.Categoria.ACOMPANHAMENTO;
import static com.padocadev.entities.produto.Categoria.SOBREMESA;
import static java.math.BigDecimal.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
class ProdutoApiTeste extends TestContainerTesteDeIntegracao {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CriaProdutoCasoDeUsoInterface criaProdutoCaso;

    @Autowired
    private ProdutoGatewayInterface produtoGateway;

    @Test
    @Transactional
    void deve_retornar_informacoes_corretas_de_produto_existente_apos_a_busca_por_id() throws Exception {
        ProdutoRequisicaoAdaptador produtoRequisicaoAdaptador = new ProdutoRequisicaoAdaptador("Sorvete", SOBREMESA, TEN);
        Produto produto = criaProdutoCaso.cria(produtoRequisicaoAdaptador.converteParaProduto(), produtoGateway);

        mockMvc.perform(get("/produtos/{produtoId}", produto.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.nome").value(produtoRequisicaoAdaptador.nome()))
                .andExpect(jsonPath("$.categoria").value(produtoRequisicaoAdaptador.categoria().toString()))
                .andExpect(jsonPath("$.preco").value(produtoRequisicaoAdaptador.preco()));
    }

    @Test
    @Transactional
    void deve_retornar_o_erro_produto_não_existe_quando_não_existir_um_produto_com_o_id_informado() throws Exception {
        ProdutoRequisicaoAdaptador produtoRequisicaoAdaptador = new ProdutoRequisicaoAdaptador("Sorvete", SOBREMESA, TEN);
        criaProdutoCaso.cria(produtoRequisicaoAdaptador.converteParaProduto(), produtoGateway);

        mockMvc.perform(get("/produtos/{produtoId}", 12345678911l))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.erro").value("Produto com o id informado não existe!"));
    }

    @Test
    @Transactional
    void deve_retornar_informacoes_corretas_do_produto_apos_a_criacao_do_mesmo() throws Exception {
        ProdutoRequisicaoAdaptador produtoRequisicaoAdaptador = new ProdutoRequisicaoAdaptador("Sorvete", SOBREMESA, TEN);

         mockMvc.perform(post("/produtos")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(objectMapper.writeValueAsString(produtoRequisicaoAdaptador)))
                 .andExpect(status().isCreated())
                 .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                 .andExpect(jsonPath("$.id").isNotEmpty())
                 .andExpect(jsonPath("$.nome").value(produtoRequisicaoAdaptador.nome()))
                 .andExpect(jsonPath("$.categoria").value(produtoRequisicaoAdaptador.categoria().toString()))
                 .andExpect(jsonPath("$.preco").value(produtoRequisicaoAdaptador.preco()));
    }

    @Test
    @Transactional
    void deve_retornar_o_erro_ja_existe_produto_quando_tentar_criar_um_produto_com_nome_ja_existente() throws Exception {
        ProdutoRequisicaoAdaptador produtoRequisicaoAdaptador = new ProdutoRequisicaoAdaptador("Sorvete", SOBREMESA, TEN);
        criaProdutoCaso.cria(produtoRequisicaoAdaptador.converteParaProduto(), produtoGateway);

        ProdutoRequisicaoAdaptador novoProdutoRequisicaoAdaptador = new ProdutoRequisicaoAdaptador("Sorvete", ACOMPANHAMENTO, valueOf(13.50));

        mockMvc.perform(post("/produtos")
             .contentType(MediaType.APPLICATION_JSON)
             .content(objectMapper.writeValueAsString(novoProdutoRequisicaoAdaptador)))
             .andExpect(status().isConflict())
             .andExpect(content().contentType(MediaType.APPLICATION_JSON))
             .andExpect(jsonPath("$.erro").value("Produto com o dados informados já existe!"));
    }

    @Test
    @Transactional
    void deve_retornar_informacoes_corretas_de_produto_existente_apos_a_edicao() throws Exception {
        ProdutoRequisicaoAdaptador produtoRequisicaoAdaptador = new ProdutoRequisicaoAdaptador("Sorvete", SOBREMESA, TEN);
        Produto produto = criaProdutoCaso.cria(produtoRequisicaoAdaptador.converteParaProduto(), produtoGateway);

        ProdutoRequisicaoAdaptador produtoParaEdicaoRequisicao = new ProdutoRequisicaoAdaptador("Pastel de nata", SOBREMESA, TEN);
        mockMvc.perform(put("/produtos/{produtoId}/edita", produto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produtoParaEdicaoRequisicao)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.nome").value(produtoParaEdicaoRequisicao.nome()))
                .andExpect(jsonPath("$.categoria").value(produtoParaEdicaoRequisicao.categoria().toString()))
                .andExpect(jsonPath("$.preco").value(produtoParaEdicaoRequisicao.preco()));
    }

    @Test
    @Transactional
    void deve_retornar_o_erro_ja_existe_produto_quando_tentar_editar_um_produto_com_nome_ja_existente() throws Exception {
        ProdutoRequisicaoAdaptador produtoRequisicaoAdaptador = new ProdutoRequisicaoAdaptador("Sorvete", SOBREMESA, TEN);
        criaProdutoCaso.cria(produtoRequisicaoAdaptador.converteParaProduto(), produtoGateway);

        ProdutoRequisicaoAdaptador produtoASerEditadoRequisicao = new ProdutoRequisicaoAdaptador("Pastel de nata", SOBREMESA, TEN);
        Produto produto = criaProdutoCaso.cria(produtoASerEditadoRequisicao.converteParaProduto(), produtoGateway);

        ProdutoRequisicaoAdaptador produtoParaEdicaoRequisicao = new ProdutoRequisicaoAdaptador("Sorvete", ACOMPANHAMENTO, TWO);
        mockMvc.perform(put("/produtos/{produtoId}/edita", produto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produtoParaEdicaoRequisicao)))
                .andExpect(status().isConflict())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.erro").value("Produto com o dados informados já existe!"));
    }

    @Test
    @Transactional
    void deve_retornar_o_erro_produto_não_existe_quando_tentar_remover_um_produto_com_id_inexistente() throws Exception {
        ProdutoRequisicaoAdaptador produtoRequisicaoAdaptador = new ProdutoRequisicaoAdaptador("Sorvete", SOBREMESA, TEN);
        criaProdutoCaso.cria(produtoRequisicaoAdaptador.converteParaProduto(), produtoGateway);

        mockMvc.perform(delete("/produtos/{produtoId}/remove", 12345678911l))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.erro").value("Produto com o id informado não existe!"));
    }

    @Test
    @Transactional
    void deve_excluir_um_produto_com_id_existente() throws Exception {
        ProdutoRequisicaoAdaptador produtoRequisicaoAdaptador = new ProdutoRequisicaoAdaptador("Sorvete", SOBREMESA, TEN);
        Produto produto = criaProdutoCaso.cria(produtoRequisicaoAdaptador.converteParaProduto(), produtoGateway);

        mockMvc.perform(delete("/produtos/{produtoId}/remove", produto.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    @Transactional
    void deve_retornar_lista_apos_a_busca_por_categoria() throws Exception {
        ProdutoRequisicaoAdaptador produtoRequisicaoAdaptador = new ProdutoRequisicaoAdaptador("Sorvete", SOBREMESA, TEN);
        criaProdutoCaso.cria(produtoRequisicaoAdaptador.converteParaProduto(), produtoGateway);

        mockMvc.perform(get("/produtos/categoria/{categoria}", SOBREMESA))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").isNotEmpty())
                .andExpect(jsonPath("$[0].nome").value(produtoRequisicaoAdaptador.nome()))
                .andExpect(jsonPath("$[0].categoria").value(produtoRequisicaoAdaptador.categoria().toString()))
                .andExpect(jsonPath("$[0].preco").value(produtoRequisicaoAdaptador.preco()));
    }


}
