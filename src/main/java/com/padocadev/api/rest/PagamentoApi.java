package com.padocadev.api.rest;

import com.padocadev.adapters.requisicao.pagamento.ConfirmacaoPagamentoAdaptador;
import com.padocadev.interfaces.pagamento.PagamentoControladorInterface;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoApi {

    private final PagamentoControladorInterface pagamentoControlador;

    public PagamentoApi(PagamentoControladorInterface pagamentoControlador) {
        this.pagamentoControlador = pagamentoControlador;
    }

    @PostMapping("/confirma-pagamento")
    @Transactional
    public ResponseEntity<Void> recebeConfirmacaoPagamento(@RequestBody @Valid ConfirmacaoPagamentoAdaptador confirmacaoPagamentoAdaptador) {
        pagamentoControlador.recebeConfirmacaoPagamento(confirmacaoPagamentoAdaptador);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/consulta-status-pagamento/pedido/{pedidoId}")
    public ResponseEntity<Map<String, String>> consultaStatusDoPagamentoDoPedido(@PathVariable Long pedidoId) {
        String pagamentoStatus = pagamentoControlador.consultaStatusDoPagamentoDoPedido(pedidoId);
        return ResponseEntity.ok(Map.of("pagamentoStatus", pagamentoStatus));
    }
}
