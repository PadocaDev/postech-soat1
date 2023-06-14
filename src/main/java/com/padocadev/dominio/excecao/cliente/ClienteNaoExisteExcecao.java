package com.padocadev.dominio.excecao.cliente;

public class ClienteNaoExisteExcecao extends RuntimeException {

    public ClienteNaoExisteExcecao() {
        super("Cliente com o CPF informado não existe!");
    }
}
