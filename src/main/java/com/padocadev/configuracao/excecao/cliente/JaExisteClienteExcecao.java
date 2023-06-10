package com.padocadev.configuracao.excecao.cliente;

public class JaExisteClienteExcecao extends RuntimeException {

    public JaExisteClienteExcecao() {
        super("Cliente com o dados informados já existe!");
    }
}
