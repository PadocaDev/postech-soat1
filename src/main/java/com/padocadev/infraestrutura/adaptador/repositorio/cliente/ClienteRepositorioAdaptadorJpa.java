package com.padocadev.infraestrutura.adaptador.repositorio.cliente;

import com.padocadev.dominio.entidade.cliente.Cliente;
import com.padocadev.dominio.porta.cliente.ClienteRepositorioAdaptadorPorta;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class ClienteRepositorioAdaptadorJpa implements ClienteRepositorioAdaptadorPorta {

    private final ClienteRepositorioJpa clienteRepositorioJpa;

    public ClienteRepositorioAdaptadorJpa(ClienteRepositorioJpa clienteRepositorioJpa) {
        this.clienteRepositorioJpa = clienteRepositorioJpa;
    }

    @Override
    @Transactional
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
    public boolean existeComCpf(String cpf) {
        return clienteRepositorioJpa.existsByCpf(cpf);
    }

    @Override
    public boolean existeComEmail(String email) {
        return clienteRepositorioJpa.existsByEmail(email);
    }
}
