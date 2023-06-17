package com.padocadev.aplicacao.rest.pedido;

import com.padocadev.aplicacao.requisicao.pedido.PedidoRequisicao;
import com.padocadev.dominio.entidade.pedido.Pedido;
import com.padocadev.dominio.porta.pedido.CriaPedidoCasoDeUsoPorta;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoControlador {

    private final CriaPedidoCasoDeUsoPorta criaPedidoCasoDeUso;

    public PedidoControlador(CriaPedidoCasoDeUsoPorta criaPedidoCasoDeUso) {
        this.criaPedidoCasoDeUso = criaPedidoCasoDeUso;
    }

    @PostMapping
    public ResponseEntity criaPedido(@RequestBody @Valid PedidoRequisicao pedidoRequisicao) {

        Pedido pedidoRealizado = new Pedido(pedidoRequisicao.produtos(), pedidoRequisicao.getClienteId());
        Pedido pedidoCriado = criaPedidoCasoDeUso.criar(pedidoRealizado);

        return ResponseEntity.ok().build();
    }



}
