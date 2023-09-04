package com.padocadev.interfaces.pagamento;

import com.padocadev.entities.pedido.Pedido;

public interface GeraCodigoQRRepositorio {

    String geraCodigoQR(Pedido pedidoCriado) throws Exception;
}
