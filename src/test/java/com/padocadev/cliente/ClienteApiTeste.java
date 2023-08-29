package com.padocadev.cliente;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.padocadev.TestContainerTesteDeIntegracao;
import com.padocadev.adapters.requisicao.cliente.ClienteRequisicaoAdaptador;
import com.padocadev.interfaces.cliente.ClienteGatewayInterface;
import com.padocadev.interfaces.cliente.CriaClienteCasoDeUsoInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
class ClienteApiTeste extends TestContainerTesteDeIntegracao {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CriaClienteCasoDeUsoInterface criaClienteCasoDeUso;

    @Autowired
    private ClienteGatewayInterface clienteGateway;

    @Test
    @Transactional
    void deve_retornar_informacoes_corretas_do_cliente_apos_a_criacao_do_mesmo() throws Exception {
        ClienteRequisicaoAdaptador clienteRequisicao = new ClienteRequisicaoAdaptador("Murillo", "murillo@gmail.com", "12345678910");

         mockMvc.perform(post("/clientes")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(objectMapper.writeValueAsString(clienteRequisicao)))
                 .andExpect(status().isCreated())
                 .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                 .andExpect(jsonPath("$.id").isNotEmpty())
                 .andExpect(jsonPath("$.nome").value(clienteRequisicao.nome()))
                 .andExpect(jsonPath("$.email").value(clienteRequisicao.email()))
                 .andExpect(jsonPath("$.cpf").value(clienteRequisicao.cpf()));
    }

    @Test
    @Transactional
    void deve_retornar_o_erro_ja_existe_cliente_quando_tentar_criar_um_cliente_com_cpf_ja_existente() throws Exception {
        ClienteRequisicaoAdaptador clienteRequisicao = new ClienteRequisicaoAdaptador("Murillo", "murillo@gmail.com", "12345678910");
        criaClienteCasoDeUso.criar(clienteRequisicao.converteParaCliente(), clienteGateway);

        ClienteRequisicaoAdaptador clienteRequisicaoComCpfIgual = new ClienteRequisicaoAdaptador("Murillo2", "murillo2@gmail.com", "12345678910");

        mockMvc.perform(post("/clientes")
             .contentType(MediaType.APPLICATION_JSON)
             .content(objectMapper.writeValueAsString(clienteRequisicaoComCpfIgual)))
             .andExpect(status().isConflict())
             .andExpect(content().contentType(MediaType.APPLICATION_JSON))
             .andExpect(jsonPath("$.erro").value("Cliente com o dados informados já existe!"));
    }

    @Test
    @Transactional
    void deve_retornar_o_erro_ja_existe_cliente_quando_tentar_criar_um_cliente_com_email_ja_existente() throws Exception {
        ClienteRequisicaoAdaptador clienteRequisicao = new ClienteRequisicaoAdaptador("Murillo", "murillo@gmail.com", "12345678910");
        criaClienteCasoDeUso.criar(clienteRequisicao.converteParaCliente(), clienteGateway);

        ClienteRequisicaoAdaptador clienteRequisicaoComEmailIgual = new ClienteRequisicaoAdaptador("Murillo2", "murillo@gmail.com", "12345678911");

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteRequisicao)))
                .andExpect(status().isConflict())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.erro").value("Cliente com o dados informados já existe!"));

    }

    @Test
    @Transactional
    void deve_retornar_informacoes_corretas_de_cliente_existente_apos_a_busca_por_cpf() throws Exception {
        ClienteRequisicaoAdaptador clienteRequisicaoAdaptador = new ClienteRequisicaoAdaptador("Murillo", "murillo@gmail.com", "12345678911");
        criaClienteCasoDeUso.criar(clienteRequisicaoAdaptador.converteParaCliente(), clienteGateway);

        mockMvc.perform(get("/clientes/{cpf}", clienteRequisicaoAdaptador.cpf()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.nome").value(clienteRequisicaoAdaptador.nome()))
                .andExpect(jsonPath("$.email").value(clienteRequisicaoAdaptador.email()))
                .andExpect(jsonPath("$.cpf").value(clienteRequisicaoAdaptador.cpf()));
    }

    @Test
    @Transactional
    void deve_retornar_o_erro_cliente_não_existe_quando_não_existir_um_cliente_com_o_cpf_informado() throws Exception {
        ClienteRequisicaoAdaptador clienteRequisicaoAdaptador = new ClienteRequisicaoAdaptador("Murillo", "murillo@gmail.com", "12345678910");
        criaClienteCasoDeUso.criar(clienteRequisicaoAdaptador.converteParaCliente(), clienteGateway);

        mockMvc.perform(get("/clientes/{cpf}", "12345678911"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.erro").value("Cliente com o CPF informado não existe!"));
    }
}