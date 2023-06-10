package com.padocadev.adaptador.cliente.resposta;

import com.padocadev.dominio.entidade.Cliente;

import java.time.LocalDateTime;

public record ClienteResposta(Long id, String nome, String email, String cpf, LocalDateTime dataCadastro) {

    public static ClienteResposta deCliente(Cliente cliente) {
        return new ClienteResposta(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getCpf(), cliente.getDataCadastro());
    }
}
