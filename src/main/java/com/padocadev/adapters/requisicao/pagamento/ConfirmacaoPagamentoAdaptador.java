package com.padocadev.adapters.requisicao.pagamento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ConfirmacaoPagamentoAdaptador(@NotNull Long pedidoId, @NotBlank String pagamentoStatus) {
}
