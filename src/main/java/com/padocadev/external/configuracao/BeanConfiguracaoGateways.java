package com.padocadev.external.configuracao;

import com.padocadev.external.cliente.ClienteRepositorioCustom;
import com.padocadev.external.pagamento.GeraCodigoQRMercadoPago;
import com.padocadev.external.pagamento.NotificaPagamentoMercadoPago;
import com.padocadev.external.pedido.PedidoRepositorioCustom;
import com.padocadev.external.produto.ProdutoRepositorioCustom;
import com.padocadev.gateways.cliente.ClienteGateway;
import com.padocadev.gateways.pagamento.GeraCodigoQRGateway;
import com.padocadev.gateways.pagamento.NotificaPagamentoGateway;
import com.padocadev.gateways.pedido.PedidoGateway;
import com.padocadev.gateways.produto.ProdutoGateway;
import com.padocadev.interfaces.cliente.ClienteGatewayInterface;
import com.padocadev.interfaces.pagamento.GeraCodigoQRGatewayInterface;
import com.padocadev.interfaces.pagamento.NotificaPagamentoGatewayInterface;
import com.padocadev.interfaces.pedido.PedidoGatewayInterface;
import com.padocadev.interfaces.produto.ProdutoGatewayInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguracaoGateways {

    @Bean
    public ProdutoGatewayInterface produtoGatewayInterface(ProdutoRepositorioCustom produtoRepositorioCustom) {
        return new ProdutoGateway(produtoRepositorioCustom);
    }

    @Bean
    public PedidoGatewayInterface pedidoGatewayInterface(PedidoRepositorioCustom pedidoRepositorioCustom) {
        return new PedidoGateway(pedidoRepositorioCustom);
    }

    @Bean
    public ClienteGatewayInterface clienteGatewayInterface(ClienteRepositorioCustom clienteRepositorioCustom) {
        return new ClienteGateway(clienteRepositorioCustom);
    }

    @Bean
    public NotificaPagamentoGatewayInterface notificaPagamentoGatewayInterface(NotificaPagamentoMercadoPago notificaPagamentoMercadoPago) {
        return new NotificaPagamentoGateway(notificaPagamentoMercadoPago);
    }

    @Bean
    public GeraCodigoQRGatewayInterface geraCodigoQRGatewayInterface(GeraCodigoQRMercadoPago geraCodigoQRMercadoPago) {
        return new GeraCodigoQRGateway(geraCodigoQRMercadoPago);
    }
}
