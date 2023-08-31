package com.padocadev.external.pagamento;

import com.padocadev.adapters.resposta.pagamento.NotificacaoDePagamentoDoPedidoResposta;
import com.padocadev.entities.pagamento.objetosDeValor.DadosDoPedidoParaPagamento;
import com.padocadev.entities.pedido.Pedido;
import com.padocadev.interfaces.pagamento.GeraCodigoQRRepositorio;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GeraCodigoQRMercadoPago implements GeraCodigoQRRepositorio {

    @Value("${mercado.pago.access.token}")
    private String API_ACCESS_TOKEN;

    @Value("${mercado.pago.api.criacao.qr.code.url}")
    private String API_CODIGO_QR_URL;

    @Value("${mercado.pago.api.vendedor.id}")
    private String API_ID_VENDEDOR;

    @Value("${mercado.pago.caixa.url}")
    private String API_URL_CAIXA;

    @Value("${mercado.pago.api.caixa.id.externo}")
    private String API_CAIXA_ID_EXTERNO;

    @Override
    public String geraCodigoQR(Pedido pedidoCriado) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String url = constroiUrl();
        DadosDoPedidoParaPagamento dadosDoPedidoParaPagamento = new DadosDoPedidoParaPagamento(pedidoCriado);
        HttpEntity<String> dadosDoPedidoParaPagamentoRequisicao = new HttpEntity<>(dadosDoPedidoParaPagamento.toJson(), pegaCabecalho());

        ResponseEntity<NotificacaoDePagamentoDoPedidoResposta> notificacaoDePagamentoDoPedidoResposta = restTemplate
                    .postForEntity(url, dadosDoPedidoParaPagamentoRequisicao, NotificacaoDePagamentoDoPedidoResposta.class);

        String dadoQrDoPedido = notificacaoDePagamentoDoPedidoResposta.getBody().getQr_data();

        return dadoQrDoPedido;
    }

    private String constroiUrl() {
        return API_CODIGO_QR_URL
                .concat("/")
                .concat(API_ID_VENDEDOR)
                .concat("/")
                .concat(API_URL_CAIXA)
                .concat("/")
                .concat(API_CAIXA_ID_EXTERNO)
                .concat("/qrs");
    }

    private HttpHeaders pegaCabecalho() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(API_ACCESS_TOKEN);
        return headers;
    }
}
