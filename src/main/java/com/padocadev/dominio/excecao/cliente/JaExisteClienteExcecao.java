package com.padocadev.dominio.excecao.cliente;

public class JaExisteClienteExcecao extends RuntimeException {

    public JaExisteClienteExcecao() {
        super("Cliente com o dados informados já existe!");
    }
}
