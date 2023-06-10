package com.padocadev.cliente;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.padocadev.TestContainerTesteDeIntegracao;
import com.padocadev.adaptador.cliente.requisicao.ClienteRequisicao;
import com.padocadev.dominio.porta.cliente.CriaClienteCasoDeUsoPorta;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
class ClienteControladorTeste extends TestContainerTesteDeIntegracao {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CriaClienteCasoDeUsoPorta criaClienteCasoDeUsoPorta;

    @Test
    @Transactional
    void deve_Retornar_Informacoes_Corretas_Do_Cliente_Apos_A_Criacao() throws Exception {
        ClienteRequisicao clienteRequisicao = new ClienteRequisicao("Murillo", "murillo@gmail.com", "12345678910");

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
    void deve_Retornar_Erro_Ja_Existe_Cliente_Quando_Tentar_Criar_Um_Cliente_Com_Cpf_Ja_Existente() throws Exception {
        ClienteRequisicao clienteRequisicao = new ClienteRequisicao("Murillo", "murillo@gmail.com", "12345678910");
        criaClienteCasoDeUsoPorta.criar(clienteRequisicao.converteParaCliente());

        ClienteRequisicao clienteRequisicaoComCpfIgual = new ClienteRequisicao("Murillo2", "murillo2@gmail.com", "12345678910");

        mockMvc.perform(post("/clientes")
             .contentType(MediaType.APPLICATION_JSON)
             .content(objectMapper.writeValueAsString(clienteRequisicaoComCpfIgual)))
             .andExpect(status().isConflict())
             .andExpect(content().contentType(MediaType.APPLICATION_JSON))
             .andExpect(jsonPath("$.erro").value("Cliente com o dados informados já existe!"));
    }

    @Test
    @Transactional
    void deve_Retornar_Erro_Ja_Existe_Cliente_Quando_Tentar_Criar_Um_Cliente_Com_Email_Ja_Existente() throws Exception {
        ClienteRequisicao clienteRequisicao = new ClienteRequisicao("Murillo", "murillo@gmail.com", "12345678910");
        criaClienteCasoDeUsoPorta.criar(clienteRequisicao.converteParaCliente());

        ClienteRequisicao clienteRequisicaoComEmailIgual = new ClienteRequisicao("Murillo2", "murillo@gmail.com", "12345678911");

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteRequisicao)))
                .andExpect(status().isConflict())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.erro").value("Cliente com o dados informados já existe!"));

    }

    @Test
    @Transactional
    void deveRetornarInformacoesCorretasDoClienteAposABuscaPorCpf() throws Exception {
        ClienteRequisicao clienteRequisicao = new ClienteRequisicao("Murillo", "murillo@gmail.com", "12345678911");
        criaClienteCasoDeUsoPorta.criar(clienteRequisicao.converteParaCliente());

        mockMvc.perform(get("/clientes/{cpf}", clienteRequisicao.cpf()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.nome").value(clienteRequisicao.nome()))
                .andExpect(jsonPath("$.email").value(clienteRequisicao.email()))
                .andExpect(jsonPath("$.cpf").value(clienteRequisicao.cpf()));
    }

    @Test
    @Transactional
    void deve_Retornar_Erro_Cliente_Não_Existe_Quando_Não_Existir_Cliente_Com_O_Cpf_Informado() throws Exception {
        ClienteRequisicao clienteRequisicao = new ClienteRequisicao("Murillo", "murillo@gmail.com", "12345678910");
        criaClienteCasoDeUsoPorta.criar(clienteRequisicao.converteParaCliente());

        mockMvc.perform(get("/clientes/{cpf}", "12345678911"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.erro").value("Cliente com o CPF informado não existe!"));
    }
}
