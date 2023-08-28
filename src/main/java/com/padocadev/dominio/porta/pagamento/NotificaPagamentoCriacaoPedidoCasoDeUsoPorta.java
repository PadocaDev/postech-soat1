package com.padocadev.dominio.porta.pagamento;

import com.padocadev.dominio.entidade.pedido.Pedido;

public interface NotificaPagamentoCriacaoPedidoCasoDeUsoPorta {

    void notifica(Pedido pedido);
}
