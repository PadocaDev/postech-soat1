package com.padocadev.dominio.excecao.produto;

public class ProdutoNaoExisteExcecao extends RuntimeException {

    public ProdutoNaoExisteExcecao() {
        super("Produto com o nome informado não existe!");
    }
}
