package com.padocadev.aplicacao.requisicao;

import com.padocadev.dominio.entidade.cliente.Cliente;
import jakarta.validation.constraints.*;

public record ClienteRequisicao(@NotBlank String nome, @NotBlank @Email String email, @NotBlank String cpf) {

    public Cliente converteParaCliente() {
        return new Cliente(nome, email, cpf);
    }
}
