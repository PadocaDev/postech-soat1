package com.padocadev.dominio.porta.cliente;

import com.padocadev.dominio.entidade.cliente.Cliente;

import java.util.Optional;

public interface ClienteRepositorioAdaptadorPorta {
    Cliente criar(Cliente cliente);
    Optional<Cliente> buscaPorCpf(String cpf);
    boolean existeComCpf(String cpf);
    boolean existeComEmail(String email);
}
