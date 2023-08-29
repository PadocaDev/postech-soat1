package com.padocadev.dominio.casodeuso.pagamento;

import com.padocadev.dominio.entidade.pedido.Pedido;
import com.padocadev.dominio.porta.pagamento.NotificaPagamentoCriacaoPedidoCasoDeUsoPorta;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class NotificaPagamentoCriacaoPedidoCasoDeUso implements NotificaPagamentoCriacaoPedidoCasoDeUsoPorta {

    @Value("${mercado.pago.access.token}")
    private String API_ACCESS_TOKEN;

    @Value("${mercado.pago.api.criacao.pedido.url}")
    private String API_CRIACAO_PEDIDO_URL;

    @Value("${mercado.pago.api.vendedor.id}")
    private String API_ID_VENDEDOR;

    @Value("${mercado.pago.loja.url}")
    private String API_URL_LOJA;

    @Value("${mercado.pago.api.loja.id.externo}")
    private String API_LOJA_ID_EXTERNO;

    @Value("${mercado.pago.caixa.url}")
    private String API_URL_CAIXA;

    @Value("${mercado.pago.api.caixa.id.externo}")
    private String API_CAIXA_ID_EXTERNO;

    @Override
    public void notifica(Pedido pedidoCriado) {
        RestTemplate restTemplate = new RestTemplate();
        String urlPedido = buildUrl();
        DadosDoPedidoParaPagamento dadosDoPedidoParaPagamento = new DadosDoPedidoParaPagamento(pedidoCriado);
        HttpEntity<String> request = new HttpEntity<>(dadosDoPedidoParaPagamento.toJson(), pegaCabecalho());
        restTemplate.put(urlPedido, request);
    }

    private String buildUrl() {
        return API_CRIACAO_PEDIDO_URL
                .concat("/")
                .concat(API_ID_VENDEDOR)
                .concat("/")
                .concat(API_URL_LOJA)
                .concat("/")
                .concat(API_LOJA_ID_EXTERNO)
                .concat("/")
                .concat(API_URL_CAIXA)
                .concat("/")
                .concat(API_CAIXA_ID_EXTERNO)
                .concat("/orders");
    }

    private HttpHeaders pegaCabecalho() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(API_ACCESS_TOKEN);
        return headers;
    }
}
