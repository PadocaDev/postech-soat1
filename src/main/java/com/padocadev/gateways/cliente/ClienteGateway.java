package com.padocadev.gateways.cliente;

import com.padocadev.entities.cliente.Cliente;
import com.padocadev.interfaces.cliente.ClienteGatewayInterface;
import com.padocadev.interfaces.cliente.ClienteRepositorio;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class ClienteGateway implements ClienteGatewayInterface {

    private final ClienteRepositorio clienteRepositorio;

    public ClienteGateway(ClienteRepositorio clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
    }

    @Override
    @Transactional
    public Cliente criar(Cliente cliente) {
        return clienteRepositorio.criar(cliente);
    }

    @Override
    public Optional<Cliente> buscaPorCpf(String cpf) {
        return clienteRepositorio.buscaPorCpf(cpf);
    }

    @Override
    public boolean existeComCpf(String cpf) {
        return clienteRepositorio.existeComCpf(cpf);
    }

    @Override
    public boolean existeComEmail(String email) {
        return clienteRepositorio.existeComEmail(email);
    }
}
