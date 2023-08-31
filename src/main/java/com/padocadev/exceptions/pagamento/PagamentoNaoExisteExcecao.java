package com.padocadev.exceptions.pagamento;

public class PagamentoNaoExisteExcecao extends RuntimeException {

    public PagamentoNaoExisteExcecao() {
        super("Pagamento com o pedidoId informado não existe!");
    }
}
