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
    public Cliente criarCliente(Cliente cliente) {
        ClienteEntidadeJpa clienteEntidadeJpa = new ClienteEntidadeJpa(cliente.getDataCadastro(), cliente.getNome(), cliente.getEmail(), cliente.getCpf());
        clienteRepositorioJpa.save(clienteEntidadeJpa);

        cliente.setId(clienteEntidadeJpa.getId());
        return cliente;
    }

    @Override
    public Optional<Cliente> buscaClientePorCpf(String cpf) {
        return clienteRepositorioJpa.findByCpf(cpf)
                .flatMap(clienteEntidadeJpa -> Optional.of(new Cliente(clienteEntidadeJpa.getId(), clienteEntidadeJpa.getDataCadastro(),
                        clienteEntidadeJpa.getNome(), clienteEntidadeJpa.getEmail(), clienteEntidadeJpa.getCpf())));
    }

    @Override
    public boolean existeClienteComCpf(String cpf) {
        return clienteRepositorioJpa.existsByCpf(cpf);
    }

    @Override
    public boolean existeClienteComEmail(String email) {
        return clienteRepositorioJpa.existsByEmail(email);
    }
}
