package com.padocadev.exceptions.pagamento;

public class GeracaoQrCodeException extends RuntimeException {
    public GeracaoQrCodeException(String message) {
        super(message);
    }
}
