package com.padocadev.dominio.porta.pagamento;

import com.padocadev.dominio.entidade.pedido.Pedido;

import java.awt.image.BufferedImage;

public interface GeraCodigoQRCasoDeUsoPorta {
    BufferedImage gera(Pedido pedidoCriado);
}
