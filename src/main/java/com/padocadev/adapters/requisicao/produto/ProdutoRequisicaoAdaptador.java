package com.padocadev.adapters.requisicao.produto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.padocadev.entities.produto.Categoria;
import com.padocadev.entities.produto.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProdutoRequisicaoAdaptador(@NotBlank String nome, @NotNull Categoria categoria, @NotNull @JsonFormat(shape = JsonFormat.Shape.STRING) BigDecimal preco) {

    public Produto converteParaProduto() {
        return new Produto(nome, categoria, preco);
    }
}
