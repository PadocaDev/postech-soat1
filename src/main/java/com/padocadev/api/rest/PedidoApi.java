package com.padocadev.api.rest;

import com.padocadev.adapters.resposta.pedido.PedidoRespostaAdaptador;
import com.padocadev.entities.pedido.Pedido;
import com.padocadev.entities.pedido.objetosDeValor.PedidoRequisicao;
import com.padocadev.interfaces.pedido.PedidoControladorInterface;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoApi {

    private final PedidoControladorInterface pedidoControlador;

    public PedidoApi(PedidoControladorInterface pedidoControlador) {
        this.pedidoControlador = pedidoControlador;
    }

    @GetMapping("/todos")
    public ResponseEntity<List<PedidoRespostaAdaptador>> buscaPedidos() {
        List<PedidoRespostaAdaptador> todosPedidos = pedidoControlador.buscaPedidos();
        return ResponseEntity.ok(todosPedidos);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PedidoRespostaAdaptador> criaPedido(@RequestBody @Valid PedidoRequisicao pedidoRequisicao) {
        PedidoRespostaAdaptador pedidoCriado = pedidoControlador.criaPedido(pedidoRequisicao);
        return ResponseEntity.created(URI.create("/todos")).body(pedidoCriado);
    }
}
