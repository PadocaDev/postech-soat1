package com.padocadev.aplicacao.resposta.pedido;

import java.awt.image.BufferedImage;

public class DadosDoPedidoResposta {
    private String numeroPedido;
    private BufferedImage codigoQr;

    public DadosDoPedidoResposta(String numeroPedido, BufferedImage codigoQr) {
        this.numeroPedido = numeroPedido;
        this.codigoQr = codigoQr;
    }
}
