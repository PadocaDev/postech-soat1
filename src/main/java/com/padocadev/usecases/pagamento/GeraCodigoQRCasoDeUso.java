package com.padocadev.usecases.pagamento;

import com.padocadev.exceptions.pagamento.GeracaoQrCodeExcecao;
import com.padocadev.interfaces.pagamento.GeraCodigoQRCasoDeUsoInterface;
import com.padocadev.entities.pedido.Pedido;
import com.padocadev.interfaces.pagamento.GeraCodigoQRGatewayInterface;

import java.awt.image.BufferedImage;

public class GeraCodigoQRCasoDeUso implements GeraCodigoQRCasoDeUsoInterface {

    @Override
    public BufferedImage gera(Pedido pedidoCriado, GeraCodigoQRGatewayInterface geraCodigoQRGateway) {
        try {
            return geraCodigoQRGateway.gera(pedidoCriado);
        } catch (Exception e) {
            throw new GeracaoQrCodeExcecao();
        }
    }
}
