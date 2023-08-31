package com.padocadev.gateways.pagamento;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.padocadev.entities.pedido.Pedido;
import com.padocadev.interfaces.pagamento.GeraCodigoQRGatewayInterface;
import com.padocadev.interfaces.pagamento.GeraCodigoQRRepositorio;

import java.awt.image.BufferedImage;

public class GeraCodigoQRGateway implements GeraCodigoQRGatewayInterface {

    private final GeraCodigoQRRepositorio geraCodigoQRRepositorio;

    public GeraCodigoQRGateway(GeraCodigoQRRepositorio geraCodigoQRRepositorio) {
        this.geraCodigoQRRepositorio = geraCodigoQRRepositorio;
    }

    @Override
    public BufferedImage gera(Pedido pedidoCriado) throws Exception {
        return geraCodigoQr(geraCodigoQRRepositorio.geraCodigoQR(pedidoCriado));
    }

    private BufferedImage geraCodigoQr(String barcodeText) throws Exception {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = barcodeWriter
                .encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
}
