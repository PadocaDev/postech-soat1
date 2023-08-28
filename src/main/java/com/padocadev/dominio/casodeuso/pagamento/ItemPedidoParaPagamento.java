package com.padocadev.dominio.casodeuso.pagamento;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.padocadev.dominio.entidade.pedido.ItemPedido;

import java.math.BigDecimal;

public class ItemPedidoParaPagamento {

    @JsonProperty("id")
    private String id;

    @JsonProperty("category")
    private String categoria;

    @JsonProperty("title")
    private String titulo;

    @JsonProperty("description")
    private final String descricao = "Item da padoca dev";

    @JsonProperty("unit_price")
    private BigDecimal precoUnitario;

    @JsonProperty("quantity")
    private Long quantidade;

    @JsonProperty("unit_measure")
    private final String unidadeDeMedida = "unit";

    @JsonProperty("total_amount")
    private BigDecimal valorTotal;

    public ItemPedidoParaPagamento(ItemPedido itemPedido) {
        this.id = itemPedido.getIdDoProdutoComoString();
        this.titulo = itemPedido.getNomeDoProduto();
        this.categoria = itemPedido.getNomeDaCategoria();
        this.precoUnitario = itemPedido.getPrecoUnitario();
        this.quantidade = itemPedido.getQuantidade();
        this.valorTotal = itemPedido.getValorTotalDoItem();
    }
}
