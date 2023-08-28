package com.padocadev.aplicacao.rest.pedido;

import com.padocadev.aplicacao.resposta.pedido.PedidoResposta;
import com.padocadev.dominio.casodeuso.pedido.objetosDeValor.PedidoRequisicao;
import com.padocadev.dominio.entidade.pedido.Pedido;
import com.padocadev.dominio.porta.pagamento.GeraCodigoQRCasoDeUsoPorta;
import com.padocadev.dominio.porta.pagamento.NotificaPagamentoCriacaoPedidoCasoDeUsoPorta;
import com.padocadev.dominio.porta.pedido.BuscaTodosPedidosCasoDeUsoPorta;
import com.padocadev.dominio.porta.pedido.CriaPedidoCasoDeUsoPorta;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoControlador {

    private final CriaPedidoCasoDeUsoPorta criaPedidoCasoDeUso;
    private final BuscaTodosPedidosCasoDeUsoPorta buscaTodosPedidosCasoDeUso;
    private final GeraCodigoQRCasoDeUsoPorta geraCodigoQRCasoDeUso;
    private final NotificaPagamentoCriacaoPedidoCasoDeUsoPorta notificaPagamentoCriacaoPedidoCasoDeUso;

    public PedidoControlador(CriaPedidoCasoDeUsoPorta criaPedidoCasoDeUso,
                             BuscaTodosPedidosCasoDeUsoPorta buscaTodosPedidosCasoDeUso,
                             GeraCodigoQRCasoDeUsoPorta geraCodigoQRCasoDeUso,
                             NotificaPagamentoCriacaoPedidoCasoDeUsoPorta notificaPagamentoCriacaoPedidoCasoDeUso) {
        this.criaPedidoCasoDeUso = criaPedidoCasoDeUso;
        this.buscaTodosPedidosCasoDeUso = buscaTodosPedidosCasoDeUso;
        this.geraCodigoQRCasoDeUso = geraCodigoQRCasoDeUso;
        this.notificaPagamentoCriacaoPedidoCasoDeUso = notificaPagamentoCriacaoPedidoCasoDeUso;
    }

    @GetMapping("/todos")
    public ResponseEntity<List<PedidoResposta>> buscaPedidos() {
        List<Pedido> pedidos = buscaTodosPedidosCasoDeUso.buscarTodosPedidosNaoFinalizados();
        List<PedidoResposta> todosPedidos = pedidos.stream().map(PedidoResposta::new).toList();
        return ResponseEntity.ok(todosPedidos);
    }

    @PostMapping(produces = MediaType.IMAGE_PNG_VALUE)
    @Transactional
    public ResponseEntity<BufferedImage> criaPedido(@RequestBody @Valid PedidoRequisicao pedidoRequisicao) {
        Pedido pedidoCriado = criaPedidoCasoDeUso.criar(pedidoRequisicao);
        notificaPagamentoCriacaoPedidoCasoDeUso.notifica(pedidoCriado);
        return ResponseEntity.ok(geraCodigoQRCasoDeUso.gera(pedidoCriado));
    }


}
