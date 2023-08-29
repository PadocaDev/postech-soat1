package com.padocadev.interfaces.cliente;

import com.padocadev.entities.cliente.Cliente;

public interface BuscaClientePorCpfCasoDeUsoInterface {
    Cliente buscaPorCpf(String cpf, ClienteGatewayInterface clienteGatewayInterface);
}
