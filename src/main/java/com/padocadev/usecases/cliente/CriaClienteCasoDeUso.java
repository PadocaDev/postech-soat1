package com.padocadev.usecases.cliente;

import com.padocadev.exceptions.cliente.JaExisteClienteExcecao;
import com.padocadev.entities.cliente.Cliente;
import com.padocadev.interfaces.cliente.ClienteGatewayInterface;
import com.padocadev.interfaces.cliente.CriaClienteCasoDeUsoInterface;

public class CriaClienteCasoDeUso implements CriaClienteCasoDeUsoInterface {

    @Override
    public Cliente criar(Cliente cliente, ClienteGatewayInterface clienteGateway) {
        if (clienteGateway.existeComCpf(cliente.getCpf()) || clienteGateway.existeComEmail(cliente.getEmail())) {
            throw new JaExisteClienteExcecao();
        }

        return clienteGateway.criar(cliente);
    }
}
