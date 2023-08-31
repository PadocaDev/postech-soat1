package com.padocadev.external.configuracao;

import com.padocadev.controllers.*;
import com.padocadev.interfaces.cliente.*;
import com.padocadev.interfaces.pagamento.*;
import com.padocadev.interfaces.pedido.*;
import com.padocadev.interfaces.produto.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguracaoControladores {

    @Bean
    public ClienteControladorInterface clienteControladorInterface(CriaClienteCasoDeUsoInterface criaClienteCasoDeUsoInterface,
                                                            BuscaClientePorCpfCasoDeUsoInterface buscaClientePorCpfCasoDeUsoInterface, ClienteGatewayInterface clienteGatewayInterface) {
        return new ClienteControlador(criaClienteCasoDeUsoInterface, buscaClientePorCpfCasoDeUsoInterface, clienteGatewayInterface);
    }

    @Bean
    public PedidoControladorInterface pedidoControladorInterface(CriaPedidoCasoDeUsoInterface criaPedidoCasoDeUsoInterface,
                                                          BuscaPedidoCasoDeUsoInterface buscaPedidoCasoDeUsoInterface,
                                                          NotificaPagamentoCriacaoPedidoCasoDeUsoInterface notificaPagamentoCriacaoPedidoCasoDeUsoInterface,
                                                          GeraCodigoQRCasoDeUsoInterface geraCodigoQRCasoDeUsoInterface,
                                                          PedidoGatewayInterface pedidoGatewayInterface,
                                                          ProdutoGatewayInterface produtoGatewayInterface,
                                                          ClienteGatewayInterface clienteGatewayInterface,
                                                          NotificaPagamentoGatewayInterface notificaPagamentoGateway,
                                                          GeraCodigoQRGatewayInterface geraCodigoQRGateway,
                                                          CriaPagamentoCasoDeUsoInterface criaPagamentoCasoDeUso,
                                                          PagamentoGatewayInterface pagamentoGateway,
                                                          AtualizaStatusDoPedidoCasoDeUsoInterface atualizaStatusDoPedidoCasoDeUso) {
        return new PedidoControlador(criaPedidoCasoDeUsoInterface, buscaPedidoCasoDeUsoInterface, notificaPagamentoCriacaoPedidoCasoDeUsoInterface, geraCodigoQRCasoDeUsoInterface, pedidoGatewayInterface, produtoGatewayInterface, clienteGatewayInterface, notificaPagamentoGateway, geraCodigoQRGateway, criaPagamentoCasoDeUso, pagamentoGateway, atualizaStatusDoPedidoCasoDeUso);
    }

    @Bean
    public ProdutoControladorInterface produtoControladorInterface(CriaProdutoCasoDeUsoInterface criaProdutoCasoDeUsoInterface,
                                                            BuscaProdutoPorIdCasoDeUsoInterface buscaProdutoPorIdCasoDeUsoInterface,
                                                            EditaProdutoCasoDeUsoInterface editaProdutoCasoDeUsoInterface,
                                                            RemoveProdutoCasoDeUsoInterface removeProdutoCasoDeUsoInterface,
                                                            BuscaProdutoPorCategoriaCasoDeUsoInterface buscaProdutoPorCategoriaCasoDeUsoInterface,
                                                            ProdutoGatewayInterface produtoGatewayInterface) {
        return new ProdutoControlador(criaProdutoCasoDeUsoInterface, buscaProdutoPorIdCasoDeUsoInterface, editaProdutoCasoDeUsoInterface, removeProdutoCasoDeUsoInterface, buscaProdutoPorCategoriaCasoDeUsoInterface, produtoGatewayInterface);
    }

    @Bean
    public PagamentoControladorInterface pagamentoControladorInterface(ConfirmaPagamentoCasoDeUsoInterface confirmaPagamentoCasoDeUsoInterface,
                                                                       PagamentoGatewayInterface pagamentoGatewayInterface,
                                                                       AtualizaStatusDoPedidoCasoDeUsoInterface atualizaStatusDoPedidoCasoDeUso,
                                                                       PedidoGatewayInterface pedidoGateway,
                                                                       ConsultaStatusDoPagamentoDoPedidoCasoDeUsoInterface consultaStatusDoPagamentoDoPedidoCasoDeUso) {
        return new PagamentoControlador(confirmaPagamentoCasoDeUsoInterface, pagamentoGatewayInterface, atualizaStatusDoPedidoCasoDeUso, pedidoGateway, consultaStatusDoPagamentoDoPedidoCasoDeUso);
    }
}
