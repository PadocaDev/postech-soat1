package com.padocadev.interfaces.cliente;

import com.padocadev.entities.cliente.Cliente;

import java.util.Optional;

public interface ClienteRepositorio {
    Cliente criar(Cliente cliente);
    Optional<Cliente> buscaPorCpf(String cpf);
    Optional<Cliente> buscaPorEmail(String email);
    boolean existeComCpf(String cpf);
    boolean existeComEmail(String email);
}
