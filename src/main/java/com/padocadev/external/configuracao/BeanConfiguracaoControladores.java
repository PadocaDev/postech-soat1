package com.padocadev.external.configuracao;

import com.padocadev.controllers.*;
import com.padocadev.interfaces.cliente.*;
import com.padocadev.interfaces.pedido.*;
import com.padocadev.interfaces.produto.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguracaoControladores {

    @Bean
    ClienteControladorInterface clienteControladorInterface(CriaClienteCasoDeUsoInterface criaClienteCasoDeUsoInterface,
                                                            BuscaClientePorCpfCasoDeUsoInterface buscaClientePorCpfCasoDeUsoInterface, ClienteGatewayInterface clienteGatewayInterface) {
        return new ClienteControlador(criaClienteCasoDeUsoInterface, buscaClientePorCpfCasoDeUsoInterface, clienteGatewayInterface);
    }

    @Bean
    PedidoControladorInterface pedidoControladorInterface(CriaPedidoCasoDeUsoInterface criaPedidoCasoDeUsoInterface,
                                                          BuscaPedidoCasoDeUsoInterface buscaPedidoCasoDeUsoInterface,
                                                          PedidoGatewayInterface pedidoGatewayInterface,
                                                          ProdutoGatewayInterface produtoGatewayInterface,
                                                          ClienteGatewayInterface clienteGatewayInterface) {
        return new PedidoControlador(criaPedidoCasoDeUsoInterface, buscaPedidoCasoDeUsoInterface, pedidoGatewayInterface, produtoGatewayInterface, clienteGatewayInterface);
    }

    @Bean
    ProdutoControladorInterface produtoControladorInterface(CriaProdutoCasoDeUsoInterface criaProdutoCasoDeUsoInterface,
                                                            BuscaProdutoPorIdCasoDeUsoInterface buscaProdutoPorIdCasoDeUsoInterface,
                                                            EditaProdutoCasoDeUsoInterface editaProdutoCasoDeUsoInterface,
                                                            RemoveProdutoCasoDeUsoInterface removeProdutoCasoDeUsoInterface,
                                                            BuscaProdutoPorCategoriaCasoDeUsoInterface buscaProdutoPorCategoriaCasoDeUsoInterface,
                                                            ProdutoGatewayInterface produtoGatewayInterface) {
        return new ProdutoControlador(criaProdutoCasoDeUsoInterface, buscaProdutoPorIdCasoDeUsoInterface, editaProdutoCasoDeUsoInterface, removeProdutoCasoDeUsoInterface, buscaProdutoPorCategoriaCasoDeUsoInterface, produtoGatewayInterface);
    }
}
