package com.padocadev.adapters.requisicao.cliente;

import com.padocadev.entities.cliente.Cliente;
import jakarta.validation.constraints.*;

public record ClienteRequisicaoAdaptador(@NotBlank String nome, @NotBlank @Email String email, @NotBlank String cpf) {

    public Cliente converteParaCliente() {
        return new Cliente(nome, email, cpf);
    }
}
