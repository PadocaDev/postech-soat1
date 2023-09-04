package com.padocadev.controllers;

import com.padocadev.adapters.requisicao.cliente.ClienteRequisicaoAdaptador;
import com.padocadev.adapters.resposta.cliente.ClienteRespostaAdaptador;
import com.padocadev.entities.cliente.Cliente;
import com.padocadev.interfaces.cliente.*;

public class ClienteControlador implements ClienteControladorInterface {

    private final CriaClienteCasoDeUsoInterface criaClienteCasoDeUso;
    private final BuscaClientePorCpfCasoDeUsoInterface buscaClientePorCpfCasoDeUso;
    private final ClienteGatewayInterface clienteGateway;

    public ClienteControlador(CriaClienteCasoDeUsoInterface criaClienteCasoDeUso, BuscaClientePorCpfCasoDeUsoInterface buscaClientePorCpfCasoDeUso, ClienteGatewayInterface clienteGateway) {
        this.criaClienteCasoDeUso = criaClienteCasoDeUso;
        this.buscaClientePorCpfCasoDeUso = buscaClientePorCpfCasoDeUso;
        this.clienteGateway = clienteGateway;
    }

    @Override
    public ClienteRespostaAdaptador criaCliente(ClienteRequisicaoAdaptador clienteRequisicao) {
        Cliente clienteCriado = criaClienteCasoDeUso.criar(clienteRequisicao.converteParaCliente(), clienteGateway);
        return ClienteRespostaAdaptador.deCliente(clienteCriado);
    }

    @Override
    public ClienteRespostaAdaptador buscaClientePorCpf(String cpf) {
        Cliente cliente = buscaClientePorCpfCasoDeUso.buscaPorCpf(cpf, clienteGateway);
        return ClienteRespostaAdaptador.deCliente(cliente);
    }
}
