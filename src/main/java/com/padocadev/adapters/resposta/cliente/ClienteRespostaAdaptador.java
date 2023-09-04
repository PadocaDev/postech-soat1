package com.padocadev.adapters.resposta.cliente;

import com.padocadev.entities.cliente.Cliente;

import java.time.LocalDateTime;

public record ClienteRespostaAdaptador(Long id, String nome, String email, String cpf, LocalDateTime dataCadastro) {

    public static ClienteRespostaAdaptador deCliente(Cliente cliente) {
        return new ClienteRespostaAdaptador(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getCpf(), cliente.getDataCadastro());
    }
}
