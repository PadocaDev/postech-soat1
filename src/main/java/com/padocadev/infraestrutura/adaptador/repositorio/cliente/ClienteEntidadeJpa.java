package com.padocadev.infraestrutura.adaptador.repositorio.cliente;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Cliente")
public class ClienteEntidadeJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "increment")
    private Long id;

    @NotNull
    private LocalDateTime dataCadastro;

    @NotBlank
    private String nome;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String cpf;

    @Deprecated
    public ClienteEntidadeJpa() {
    }

    public ClienteEntidadeJpa(LocalDateTime dataCadastro, String nome, String email, String cpf) {
        this.dataCadastro = dataCadastro;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }
}
