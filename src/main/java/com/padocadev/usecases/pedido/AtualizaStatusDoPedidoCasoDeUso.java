package com.padocadev.usecases.pedido;

import com.padocadev.entities.pagamento.PagamentoStatus;
import com.padocadev.entities.pedido.Pedido;
import com.padocadev.entities.pedido.Status;
import com.padocadev.exceptions.pedido.PedidoNaoExisteExcecao;
import com.padocadev.interfaces.pedido.AtualizaStatusDoPedidoCasoDeUsoInterface;
import com.padocadev.interfaces.pedido.PedidoGatewayInterface;

import static com.padocadev.entities.pedido.Status.CANCELADO;
import static com.padocadev.entities.pedido.Status.RECEBIDO;

public class AtualizaStatusDoPedidoCasoDeUso implements AtualizaStatusDoPedidoCasoDeUsoInterface {

    @Override
    public Pedido atualizaStatusDoPedido(Long idPedido, Status status, PedidoGatewayInterface pedidoGateway) {
        Pedido pedido = pedidoGateway.buscarPedidoPorId(idPedido).orElseThrow(PedidoNaoExisteExcecao::new);
        pedido.setStatus(status);
        pedidoGateway.salva(pedido);
        return pedido;
    }

    @Override
    public Pedido atualizaStatusDoPedido(Long idPedido, PagamentoStatus pagamentoStatus, PedidoGatewayInterface pedidoGateway) {
        Status pedidoStatus = PagamentoStatus.APROVADO.equals(pagamentoStatus) ? RECEBIDO : CANCELADO;
        return atualizaStatusDoPedido(idPedido, pedidoStatus, pedidoGateway);
    }
}
