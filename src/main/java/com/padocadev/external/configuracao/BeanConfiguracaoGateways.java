package com.padocadev.external.configuracao;

import com.padocadev.external.cliente.ClienteRepositorioCustom;
import com.padocadev.external.pedido.PedidoRepositorioCustom;
import com.padocadev.external.produto.ProdutoRepositorioCustom;
import com.padocadev.gateways.cliente.ClienteGateway;
import com.padocadev.gateways.pedido.PedidoGateway;
import com.padocadev.gateways.produto.ProdutoGateway;
import com.padocadev.interfaces.cliente.ClienteGatewayInterface;
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

}
