package com.padocadev.interfaces.produto;

import com.padocadev.adapters.requisicao.produto.ProdutoRequisicaoAdaptador;
import com.padocadev.adapters.resposta.produto.ProdutoRespostaAdaptador;

import java.util.List;

public interface ProdutoControladorInterface {
    ProdutoRespostaAdaptador buscaPorId(Long produtoId);
    ProdutoRespostaAdaptador cria(ProdutoRequisicaoAdaptador produtoRequisicaoAdaptador);
    ProdutoRespostaAdaptador edita(Long produtoId, ProdutoRequisicaoAdaptador produtoRequisicaoAdaptador);
    void removeProduto(Long produtoId);
    List<ProdutoRespostaAdaptador> buscaProdutosPorCategoria(String categoria);
}
