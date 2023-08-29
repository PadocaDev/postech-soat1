package com.padocadev.exceptions.produto;

public class ProdutoNaoExisteExcecao extends RuntimeException {

    public ProdutoNaoExisteExcecao() {
        super("Produto com o id informado não existe!");
    }
}
