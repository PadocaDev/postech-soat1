package com.padocadev.interfaces.cliente;

import com.padocadev.entities.cliente.Cliente;

import java.util.Optional;

public interface ClienteGatewayInterface {
    Cliente criar(Cliente cliente);
    Optional<Cliente> buscaPorCpf(String cpf);
    boolean existeComCpf(String cpf);
    boolean existeComEmail(String email);
}
