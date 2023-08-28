package com.padocadev.dominio.excecao.pagamento;

public class GeracaoQrCodeException extends RuntimeException {
    public GeracaoQrCodeException(String message) {
        super(message);
    }
}
