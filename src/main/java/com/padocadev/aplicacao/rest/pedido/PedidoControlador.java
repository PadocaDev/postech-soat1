package com.padocadev.aplicacao.rest.pedido;

import com.padocadev.aplicacao.resposta.pedido.PedidoResposta;
import com.padocadev.dominio.casodeuso.pedido.objetosDeValor.PedidoRequisicao;
import com.padocadev.dominio.entidade.pedido.Pedido;
import com.padocadev.dominio.porta.pedido.BuscaTodosPedidosCasoDeUsoPorta;
import com.padocadev.dominio.porta.pedido.CriaPedidoCasoDeUsoPorta;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/todos")
    public ResponseEntity<List<PedidoResposta>> buscaPedidos() {
        List<Pedido> pedidos = buscaTodosPedidosCasoDeUso.buscarTodosPedidosNaoFinalizados();
        List<PedidoResposta> todosPedidos = pedidos.stream().map(PedidoResposta::new).toList();
        return ResponseEntity.ok(todosPedidos);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> criaPedido(@RequestBody @Valid PedidoRequisicao pedidoRequisicao) {
        Pedido pedidoCriado = criaPedidoCasoDeUso.criar(pedidoRequisicao);
        return ResponseEntity.ok(pedidoCriado.getNumeroPedido());
    }
}
