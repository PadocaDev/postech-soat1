package com.padocadev.aplicacao.rest.produto;

import com.padocadev.aplicacao.requisicao.produto.ProdutoRequisicao;
import com.padocadev.aplicacao.resposta.produto.ProdutoResposta;
import com.padocadev.dominio.entidade.produto.Produto;
import com.padocadev.dominio.porta.produto.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/produtos")
public class ProdutoControlador {

    private final CriaProdutoCasoDeUsoPorta criaProdutoCasoDeUso;
    private final BuscaProdutoPorIdCasoDeUsoPorta buscaProdutoPorIdCasoDeUso;
    private final EditaProdutoCasoDeUsoPorta editaProdutoCasosDeUso;

    public ProdutoControlador(CriaProdutoCasoDeUsoPorta criaProdutoCasoDeUso,
                              BuscaProdutoPorIdCasoDeUsoPorta buscaProdutoPorIdCasoDeUso,
                              EditaProdutoCasoDeUsoPorta editaProdutoCasosDeUso) {
        this.criaProdutoCasoDeUso = criaProdutoCasoDeUso;
        this.buscaProdutoPorIdCasoDeUso = buscaProdutoPorIdCasoDeUso;
        this.editaProdutoCasosDeUso = editaProdutoCasosDeUso;
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

    @PutMapping("/{produtoId}/edita")
    @Transactional
    public ResponseEntity<ProdutoResposta> editaProduto(@PathVariable("produtoId") Long produtoId,
                                                        @RequestBody @Valid ProdutoRequisicao produtoRequisicao) {
        Produto produtoEditado = editaProdutoCasosDeUso.edita(produtoId, produtoRequisicao.converteParaProduto());
        return ResponseEntity.ok((ProdutoResposta.deProduto(produtoEditado)));
    }

}
