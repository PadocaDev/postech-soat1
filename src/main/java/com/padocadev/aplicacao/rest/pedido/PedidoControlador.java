package com.padocadev.aplicacao.rest.pedido;

import com.padocadev.aplicacao.requisicao.pedido.PedidoRequisicao;
import com.padocadev.aplicacao.resposta.pedido.PedidoResposta;
import com.padocadev.dominio.entidade.pedido.Pedido;
import com.padocadev.dominio.porta.pedido.BuscaTodosPedidosCasoDeUsoPorta;
import com.padocadev.dominio.porta.pedido.CriaPedidoCasoDeUsoPorta;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoControlador {

    private final CriaPedidoCasoDeUsoPorta criaPedidoCasoDeUso;
    private final BuscaTodosPedidosCasoDeUsoPorta buscaTodosPedidosCasoDeUso;

    public PedidoControlador(CriaPedidoCasoDeUsoPorta criaPedidoCasoDeUso, BuscaTodosPedidosCasoDeUsoPorta buscaTodosPedidosCasoDeUso) {
        this.criaPedidoCasoDeUso = criaPedidoCasoDeUso;
        this.buscaTodosPedidosCasoDeUso = buscaTodosPedidosCasoDeUso;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<PedidoResposta>> buscaTodosPedidos() {
        List<Pedido> pedidos = buscaTodosPedidosCasoDeUso.buscarTodosPedidos();
        return ResponseEntity.ok(PedidoResposta.listarPedidos(pedidos));
    }

    @PostMapping
    public ResponseEntity<PedidoResposta> criaPedido(@RequestBody @Valid PedidoRequisicao pedidoRequisicao) {
        Pedido pedidoCriado = criaPedidoCasoDeUso.criar(pedidoRequisicao.converteParaPedido());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{numeroPedido}")
                .buildAndExpand(pedidoCriado.getNumeroPedido())
                .toUri();
        return ResponseEntity.created(location).body(PedidoResposta.dePedido(pedidoCriado));
    }
}
