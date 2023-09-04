package com.padocadev.entities.produto;

import com.padocadev.external.produto.ProdutoEntidadeJpa;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Produto {
    private Long id;
    private LocalDateTime dataCadastro = LocalDateTime.now();
    private String nome;
    private Categoria categoria;
    private BigDecimal preco;

    public Produto(Long id, LocalDateTime dataCadastro, String nome, Categoria categoria, BigDecimal preco) {
        this.id = id;
        this.dataCadastro = dataCadastro;
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
    }

    public Produto(String nome, Categoria categoria, BigDecimal preco) {
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public String getNome() {
        return this.nome;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public BigDecimal getPreco() {
        return this.preco;
    }

    public String getIdComoString() {
        return this.id.toString();
    }

    public void atualiza(ProdutoEntidadeJpa produtoExistenteEntidadeJpa) {
        produtoExistenteEntidadeJpa.setNome(this.nome);
        produtoExistenteEntidadeJpa.setCategoria(this.categoria);
        produtoExistenteEntidadeJpa.setPreco(this.preco);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return id.equals(produto.id) &&
                dataCadastro.equals(produto.dataCadastro) &&
                nome.equals(produto.nome) &&
                categoria == produto.categoria &&
                preco.equals(produto.preco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataCadastro, nome, categoria, preco);
    }

    public String getNomeDaCategoria() {
        return this.categoria.name();
    }
}
