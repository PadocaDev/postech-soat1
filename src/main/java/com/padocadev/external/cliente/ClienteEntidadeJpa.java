package com.padocadev.external.cliente;

import com.padocadev.entities.cliente.Cliente;
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

    public ClienteEntidadeJpa(Cliente cliente) {
        this.id = cliente.getId();
        this.dataCadastro = cliente.getDataCadastro();
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
        this.cpf = cliente.getCpf();
    }

    public Cliente converteParaCliente() {
        return new Cliente(id, dataCadastro, nome, email, cpf);
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

    public Cliente paraCliente() {
        return new Cliente(id, dataCadastro, nome, email, cpf);
    }
}
