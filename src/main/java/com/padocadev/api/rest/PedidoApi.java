package com.padocadev.api.rest;

import com.padocadev.adapters.requisicao.pedido.AtualizaStatusDoPedidoAdaptador;
import com.padocadev.adapters.resposta.pedido.PedidoRespostaAdaptador;
import com.padocadev.entities.cliente.Cliente;
import com.padocadev.entities.pedido.objetosDeValor.PedidoRequisicao;
import com.padocadev.external.configuracao.seguranca.ClienteLogado;
import com.padocadev.interfaces.pedido.PedidoControladorInterface;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;
import java.util.List;

import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoApi {

    private final PedidoControladorInterface pedidoControlador;
    private final ClienteLogado clienteLogado;

    public PedidoApi(PedidoControladorInterface pedidoControlador, ClienteLogado clienteLogado) {
        this.pedidoControlador = pedidoControlador;
        this.clienteLogado = clienteLogado;
    }

    @GetMapping("/todos")
    public ResponseEntity<List<PedidoRespostaAdaptador>> buscaPedidos() {
        List<PedidoRespostaAdaptador> todosPedidos = pedidoControlador.buscaPedidos();
        return ResponseEntity.ok(todosPedidos);
    }

    @PostMapping(produces = IMAGE_PNG_VALUE)
    @Transactional
    public ResponseEntity<BufferedImage> criaPedido(@RequestBody @Valid PedidoRequisicao pedidoRequisicao) {
        String cpfCliente = clienteLogado.getCliente().map(Cliente::getCpf).orElse("");
        pedidoRequisicao.setCpfCliente(cpfCliente);
        BufferedImage codigoQrDoPedidoCriado = pedidoControlador.criaPedido(pedidoRequisicao);
        return ResponseEntity.ok(codigoQrDoPedidoCriado);
    }

    @PostMapping("/atualiza-status")
    @Transactional
    public ResponseEntity<Void> atualizaStatus(@RequestBody @Valid AtualizaStatusDoPedidoAdaptador atualizaStatusDoPedidoAdaptador) {
        pedidoControlador.atualizaStatusDoPedido(atualizaStatusDoPedidoAdaptador);
        return ResponseEntity.noContent().build();
    }
}
