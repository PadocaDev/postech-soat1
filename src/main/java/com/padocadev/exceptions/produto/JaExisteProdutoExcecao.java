package com.padocadev.exceptions.produto;

public class JaExisteProdutoExcecao extends RuntimeException {

    public JaExisteProdutoExcecao() {
        super("Produto com o dados informados já existe!");
    }
}
