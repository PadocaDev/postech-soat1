package com.padocadev.adaptador.cliente.requisicao;

import com.padocadev.dominio.entidade.Cliente;
import jakarta.validation.constraints.*;

public record ClienteRequisicao(@NotBlank String nome, @NotBlank @Email String email, @NotBlank String cpf) {

    public Cliente converteParaCliente() {
        return new Cliente(nome, email, cpf);
    }
}
