package com.padocadev.adapters.requisicao.pedido;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtualizaStatusDoPedidoAdaptador(@NotNull Long idPedido, @NotBlank String statusDoPedido) {
}
