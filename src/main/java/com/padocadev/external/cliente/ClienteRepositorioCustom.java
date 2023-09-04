package com.padocadev.external.cliente;

import com.padocadev.entities.cliente.Cliente;
import com.padocadev.interfaces.cliente.ClienteRepositorio;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClienteRepositorioCustom implements ClienteRepositorio {

    private final ClienteRepositorioJpa clienteRepositorioJpa;

    public ClienteRepositorioCustom(ClienteRepositorioJpa clienteRepositorioJpa) {
        this.clienteRepositorioJpa = clienteRepositorioJpa;
    }

    @Override
    public Cliente criar(Cliente cliente) {
        ClienteEntidadeJpa clienteEntidadeJpa = clienteRepositorioJpa.save(new ClienteEntidadeJpa(cliente));
        return clienteEntidadeJpa.converteParaCliente();
    }

    @Override
    public Optional<Cliente> buscaPorCpf(String cpf) {
        Optional<ClienteEntidadeJpa> clienteEntidadeJpa = clienteRepositorioJpa.findByCpf(cpf);
        return clienteEntidadeJpa.map(ClienteEntidadeJpa::converteParaCliente);
    }

    @Override
    public Optional<Cliente> buscaPorEmail(String email) {
        Optional<ClienteEntidadeJpa> clienteEntidadeJpa = clienteRepositorioJpa.findByEmail(email);
        return clienteEntidadeJpa.map(ClienteEntidadeJpa::converteParaCliente);
    }

    @Override
    public boolean existeComCpf(String cpf) {
        return clienteRepositorioJpa.existsByCpf(cpf);
    }

    @Override
    public boolean existeComEmail(String email) {
        return clienteRepositorioJpa.existsByEmail(email);
    }
}
