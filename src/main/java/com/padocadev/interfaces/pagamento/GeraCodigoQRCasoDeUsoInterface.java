package com.padocadev.interfaces.pagamento;

import com.padocadev.entities.pedido.Pedido;

import java.awt.image.BufferedImage;

public interface GeraCodigoQRCasoDeUsoInterface {
    BufferedImage gera(Pedido pedidoCriado, GeraCodigoQRGatewayInterface geraCodigoQRGateway);
}
