package com.padocadev.interfaces.pagamento;

import com.padocadev.entities.pedido.Pedido;

import java.awt.image.BufferedImage;

public interface GeraCodigoQRGatewayInterface {

    public BufferedImage gera(Pedido pedidoCriado) throws Exception;
}
