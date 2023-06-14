package com.padocadev.dominio.entidade.cliente;

import java.time.LocalDateTime;

public class Cliente {
    private Long id;
    private LocalDateTime dataCadastro = LocalDateTime.now();
    private String nome;
    private String email;
    private String cpf;

    public Cliente(Long id, LocalDateTime dataCadastro, String nome, String email, String cpf) {
        this.id = id;
        this.dataCadastro = dataCadastro;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
    }

    public Cliente(String nome, String email, String cpf) {
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

    public void setId(Long id) {
        this.id = id;
    }
}
