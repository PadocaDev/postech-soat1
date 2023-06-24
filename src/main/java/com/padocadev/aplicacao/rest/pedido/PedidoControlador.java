package com.padocadev.aplicacao.rest.pedido;

import com.padocadev.aplicacao.requisicao.pedido.PedidoRequisicao;
import com.padocadev.dominio.entidade.pedido.Pedido;
import com.padocadev.dominio.porta.pedido.BuscaTodosPedidosCasoDeUsoPorta;
import com.padocadev.dominio.porta.pedido.CriaPedidoCasoDeUsoPorta;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pedidos")
public class PedidoControlador {

    private final CriaPedidoCasoDeUsoPorta criaPedidoCasoDeUso;

    public PedidoControlador(CriaPedidoCasoDeUsoPorta criaPedidoCasoDeUso) {
        this.criaPedidoCasoDeUso = criaPedidoCasoDeUso;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> teste(@RequestBody @Valid PedidoRequisicao pedidoRequisicao) {
        criaPedidoCasoDeUso.criar(pedidoRequisicao);

        return ResponseEntity.ok().build();
    }
}
