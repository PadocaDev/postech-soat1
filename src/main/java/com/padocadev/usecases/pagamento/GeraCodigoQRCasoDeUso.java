package com.padocadev.usecases.pagamento;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.padocadev.adapters.resposta.pagamento.NotificacaoDePagamentoDoPedidoResposta;
import com.padocadev.entities.pagamento.objetosDeValor.DadosDoPedidoParaPagamento;
import com.padocadev.exceptions.pagamento.GeracaoQrCodeException;
import com.padocadev.interfaces.pagamento.GeraCodigoQRCasoDeUsoInterface;
import com.padocadev.entities.pedido.Pedido;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.awt.image.BufferedImage;

public class GeraCodigoQRCasoDeUso implements GeraCodigoQRCasoDeUsoInterface {

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
    public BufferedImage gera(Pedido pedidoCriado) {
        RestTemplate restTemplate = new RestTemplate();
        String url = constroiUrl();
        DadosDoPedidoParaPagamento dadosDoPedidoParaPagamento = new DadosDoPedidoParaPagamento(pedidoCriado);
        HttpEntity<String> dadosDoPedidoParaPagamentoRequisicao = new HttpEntity<>(dadosDoPedidoParaPagamento.toJson(), pegaCabecalho());

        try {
            ResponseEntity<NotificacaoDePagamentoDoPedidoResposta> notificacaoDePagamentoDoPedidoResposta = restTemplate
                    .postForEntity(url, dadosDoPedidoParaPagamentoRequisicao, NotificacaoDePagamentoDoPedidoResposta.class);
            String dadoQrDoPedido = notificacaoDePagamentoDoPedidoResposta.getBody().getQr_data();
            return geraCodigoQr(dadoQrDoPedido);

        } catch (Exception e) {
            throw new GeracaoQrCodeException(e.getMessage());
        }
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

    private BufferedImage geraCodigoQr(String barcodeText) throws Exception {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = barcodeWriter
                .encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
}
