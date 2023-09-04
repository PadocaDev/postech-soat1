package com.padocadev.usecases.cliente;

import com.padocadev.exceptions.cliente.ClienteNaoExisteExcecao;
import com.padocadev.entities.cliente.Cliente;
import com.padocadev.interfaces.cliente.BuscaClientePorCpfCasoDeUsoInterface;
import com.padocadev.interfaces.cliente.ClienteGatewayInterface;

public class BuscaClientePorCpfCasoDeUso implements BuscaClientePorCpfCasoDeUsoInterface {

    @Override
    public Cliente buscaPorCpf(String cpf, ClienteGatewayInterface clienteGateway) {
        return clienteGateway.buscaPorCpf(cpf).orElseThrow(ClienteNaoExisteExcecao::new);
    }
}
