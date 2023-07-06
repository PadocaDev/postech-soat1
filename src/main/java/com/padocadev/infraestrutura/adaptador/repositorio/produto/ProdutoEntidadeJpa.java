package com.padocadev.infraestrutura.adaptador.repositorio.produto;

import com.padocadev.dominio.entidade.produto.Categoria;
import com.padocadev.dominio.entidade.produto.Produto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Produto")
public class ProdutoEntidadeJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "increment")
    private Long id;

    @NotNull
    private LocalDateTime dataCadastro;

    @NotBlank
    private String nome;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    private BigDecimal preco;

    @Deprecated
    public ProdutoEntidadeJpa() {
    }


    public ProdutoEntidadeJpa(Produto produto) {
        this.id = produto.getId();
        this.dataCadastro = produto.getDataCadastro();
        this.nome = produto.getNome();
        this.categoria = produto.getCategoria();
        this.preco = produto.getPreco();
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDataCadastro() {
        return this.dataCadastro;
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Produto converteParaProduto() {
        return new Produto(id, dataCadastro, nome, categoria, preco);
    }

    public void atualiza(Produto produtoParaEditar) {
        this.nome = produtoParaEditar.getNome();
        this.preco = produtoParaEditar.getPreco();
        this.categoria = produtoParaEditar.getCategoria();
    }
}
