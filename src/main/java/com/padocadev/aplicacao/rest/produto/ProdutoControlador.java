package com.padocadev.aplicacao.rest.produto;

import com.padocadev.aplicacao.requisicao.produto.ProdutoRequisicao;
import com.padocadev.aplicacao.resposta.produto.ProdutoResposta;
import com.padocadev.dominio.entidade.produto.Categoria;
import com.padocadev.dominio.entidade.produto.Produto;
import com.padocadev.dominio.porta.produto.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produtos")
public class ProdutoControlador {

    private final CriaProdutoCasoDeUsoPorta criaProdutoCasoDeUso;
    private final BuscaProdutoPorIdCasoDeUsoPorta buscaProdutoPorIdCasoDeUso;
    private final EditaProdutoCasoDeUsoPorta editaProdutoCasosDeUso;
    private final RemoveProdutoCasoDeUsoPorta removeProdutoCasoDeUso;
    private final BuscaProdutoPorCategoriaCasoDeUsoPorta buscaProdutoPorCategoriaCasoDeUso;

    public ProdutoControlador(CriaProdutoCasoDeUsoPorta criaProdutoCasoDeUso,
                              BuscaProdutoPorIdCasoDeUsoPorta buscaProdutoPorIdCasoDeUso,
                              EditaProdutoCasoDeUsoPorta editaProdutoCasosDeUso,
                              RemoveProdutoCasoDeUsoPorta removeProdutoCasosDeUso,
                              BuscaProdutoPorCategoriaCasoDeUsoPorta buscaProdutoPorCategoriaCasoDeUso) {
        this.criaProdutoCasoDeUso = criaProdutoCasoDeUso;
        this.buscaProdutoPorIdCasoDeUso = buscaProdutoPorIdCasoDeUso;
        this.editaProdutoCasosDeUso = editaProdutoCasosDeUso;
        this.removeProdutoCasoDeUso = removeProdutoCasosDeUso;
        this.buscaProdutoPorCategoriaCasoDeUso = buscaProdutoPorCategoriaCasoDeUso;
    }

    @GetMapping("/{produtoId}")
    public ResponseEntity<ProdutoResposta> buscaProdutoPorId(@PathVariable Long produtoId) {
        Produto produto = buscaProdutoPorIdCasoDeUso.buscaPorId(produtoId);
        return ResponseEntity.ok(ProdutoResposta.deProduto(produto));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ProdutoResposta> criaProduto(@RequestBody @Valid ProdutoRequisicao produtoRequisicao) {
        Produto produtoCriado = criaProdutoCasoDeUso.criar(produtoRequisicao.converteParaProduto());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{produtoId}")
                .buildAndExpand(produtoCriado.getId())
                .toUri();
        return ResponseEntity.created(location).body(ProdutoResposta.deProduto(produtoCriado));
    }

    @PostMapping("/{produtoId}/edita")
    @Transactional
    public ResponseEntity<ProdutoResposta> editaProduto(@PathVariable("produtoId") Long produtoId,
                                                        @RequestBody @Valid ProdutoRequisicao produtoRequisicao) {
        Produto produtoEditado = editaProdutoCasosDeUso.edita(produtoId, produtoRequisicao.converteParaProduto());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{produtoId}")
                .buildAndExpand(produtoEditado.getId())
                .toUri();
        return ResponseEntity.created(location).body(ProdutoResposta.deProduto(produtoEditado));
    }

    @DeleteMapping("/{produtoId}/remove")
    @Transactional
    public ResponseEntity<Void> removeProduto(@PathVariable("produtoId") Long produtoId) {
        removeProdutoCasoDeUso.remover(produtoId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<ProdutoResposta>> buscaProdutosPorCategoria(@PathVariable String categoria) {
            List<Produto> produtos = buscaProdutoPorCategoriaCasoDeUso.buscaPorCategoria(Categoria.valueOf(categoria));
        return ResponseEntity.ok(ProdutoResposta.deProduto(produtos));
    }

}


