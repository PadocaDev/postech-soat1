package com.padocadev.entities.pagamento.objetosDeValor;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.padocadev.entities.pedido.Pedido;

import java.math.BigDecimal;
import java.util.List;

public class DadosDoPedidoParaPagamento {

    @JsonProperty("external_reference")
    private String referenciaExterna;

    @JsonProperty("title")
    private final String titulo = "Pedido realizado";

    @JsonProperty("notification_url")
    private final String urlParaNotificacao = "https://webhook.site/1ed35820-6696-42d1-8137-5bb696e2a5b4";

    @JsonProperty("total_amount")
    private BigDecimal valorTotal;

    @JsonProperty("description")
    private final String descricao = "Boas vindas à padoca dev";

    @JsonProperty("items")
    private List<ItemPedidoParaPagamento> itens;

    public DadosDoPedidoParaPagamento(Pedido pedidoCriado) {
        this.referenciaExterna = pedidoCriado.getNumeroPedido();
        this.valorTotal = pedidoCriado.getValorTotal();
        this.itens = pedidoCriado.getItensPedido().stream().map(ItemPedidoParaPagamento::new).toList();
    }

    public String toJson() {
        return toJson(this);
    }

    public String toJson(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        String resultJson = null;
        try {
            resultJson = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return resultJson;
    }
}
