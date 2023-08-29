package com.padocadev.controllers;

import com.padocadev.adapters.requisicao.produto.ProdutoRequisicaoAdaptador;
import com.padocadev.adapters.resposta.produto.ProdutoRespostaAdaptador;
import com.padocadev.entities.produto.Categoria;
import com.padocadev.entities.produto.Produto;
import com.padocadev.interfaces.produto.*;

import java.util.List;

public class ProdutoControlador implements ProdutoControladorInterface {

    private final CriaProdutoCasoDeUsoInterface criaProdutoCasoDeUso;
    private final BuscaProdutoPorIdCasoDeUsoInterface buscaProdutoPorIdCasoDeUso;
    private final EditaProdutoCasoDeUsoInterface editaProdutoCasosDeUso;
    private final RemoveProdutoCasoDeUsoInterface removeProdutoCasoDeUso;
    private final BuscaProdutoPorCategoriaCasoDeUsoInterface buscaProdutoPorCategoriaCasoDeUso;
    private final ProdutoGatewayInterface produtoGateway;

    public ProdutoControlador(CriaProdutoCasoDeUsoInterface criaProdutoCasoDeUso, BuscaProdutoPorIdCasoDeUsoInterface buscaProdutoPorIdCasoDeUso,
                              EditaProdutoCasoDeUsoInterface editaProdutoCasosDeUso, RemoveProdutoCasoDeUsoInterface removeProdutoCasoDeUso, BuscaProdutoPorCategoriaCasoDeUsoInterface buscaProdutoPorCategoriaCasoDeUso, ProdutoGatewayInterface produtoGateway) {
        this.criaProdutoCasoDeUso = criaProdutoCasoDeUso;
        this.buscaProdutoPorIdCasoDeUso = buscaProdutoPorIdCasoDeUso;
        this.editaProdutoCasosDeUso = editaProdutoCasosDeUso;
        this.removeProdutoCasoDeUso = removeProdutoCasoDeUso;
        this.buscaProdutoPorCategoriaCasoDeUso = buscaProdutoPorCategoriaCasoDeUso;
        this.produtoGateway = produtoGateway;
    }

    public ProdutoRespostaAdaptador buscaPorId(Long produtoId) {
        Produto produto = buscaProdutoPorIdCasoDeUso.buscaPorId(produtoId, produtoGateway);
        return ProdutoRespostaAdaptador.deProduto(produto);
    }

    public ProdutoRespostaAdaptador cria(ProdutoRequisicaoAdaptador produtoRequisicaoAdaptador) {
        Produto produtoCriado = criaProdutoCasoDeUso.cria(produtoRequisicaoAdaptador.converteParaProduto(), produtoGateway);
        return ProdutoRespostaAdaptador.deProduto(produtoCriado);
    }

    public ProdutoRespostaAdaptador edita(Long produtoId, ProdutoRequisicaoAdaptador produtoRequisicaoAdaptador) {
        Produto produtoEditado = editaProdutoCasosDeUso.edita(produtoId, produtoRequisicaoAdaptador.converteParaProduto(), produtoGateway);
        return ProdutoRespostaAdaptador.deProduto(produtoEditado);
    }

    public void removeProduto(Long produtoId) {
        removeProdutoCasoDeUso.remover(produtoId, produtoGateway);
    }

    public List<ProdutoRespostaAdaptador> buscaProdutosPorCategoria(String categoria) {
        List<Produto> produtos = buscaProdutoPorCategoriaCasoDeUso.buscaPorCategoria(Categoria.valueOf(categoria), produtoGateway);
        return ProdutoRespostaAdaptador.deProduto(produtos);
    }
}


