package com.padocadev.usecases.cliente;

import com.padocadev.exceptions.cliente.JaExisteClienteExcecao;
import com.padocadev.entities.cliente.Cliente;
import com.padocadev.interfaces.cliente.ClienteGatewayInterface;
import com.padocadev.interfaces.cliente.CriaClienteCasoDeUsoInterface;

public class CriaClienteCasoDeUso implements CriaClienteCasoDeUsoInterface {

    @Override
    public Cliente criar(Cliente cliente, ClienteGatewayInterface clienteGatewayInterface) {
        if (clienteGatewayInterface.existeComCpf(cliente.getCpf()) || clienteGatewayInterface.existeComEmail(cliente.getEmail())) {
            throw new JaExisteClienteExcecao();
        }

        return clienteGatewayInterface.criar(cliente);
    }
}
