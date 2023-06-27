package com.padocadev.aplicacao.requisicao.produto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.padocadev.dominio.entidade.produto.Categoria;
import com.padocadev.dominio.entidade.produto.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProdutoRequisicao(@NotBlank String nome, @NotNull Categoria categoria, @NotNull @JsonFormat(shape = JsonFormat.Shape.STRING) BigDecimal preco) {

    public Produto converteParaProduto() {
        return new Produto(nome, categoria, preco);
    }
}
