package com.padocadev.api.rest;

import com.padocadev.adapters.requisicao.produto.ProdutoRequisicaoAdaptador;
import com.padocadev.adapters.resposta.produto.ProdutoRespostaAdaptador;
import com.padocadev.interfaces.produto.ProdutoControladorInterface;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoApi {

    private final ProdutoControladorInterface produtoControlador;

    public ProdutoApi(ProdutoControladorInterface produtoControlador) {
        this.produtoControlador = produtoControlador;
    }

    @GetMapping("/{produtoId}")
    public ResponseEntity<ProdutoRespostaAdaptador> buscaPorId(@PathVariable Long produtoId) {
        return ResponseEntity.ok(produtoControlador.buscaPorId(produtoId));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ProdutoRespostaAdaptador> cria(@RequestBody @Valid ProdutoRequisicaoAdaptador produtoRequisicaoAdaptador) {
        ProdutoRespostaAdaptador produtoCriado = produtoControlador.cria(produtoRequisicaoAdaptador);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{produtoId}")
                .buildAndExpand(produtoCriado.id())
                .toUri();
        return ResponseEntity.created(location).body(produtoCriado);
    }

    @PutMapping("/{produtoId}/edita")
    @Transactional
    public ResponseEntity<ProdutoRespostaAdaptador> edita(@PathVariable("produtoId") Long produtoId,
                                                          @RequestBody @Valid ProdutoRequisicaoAdaptador produtoRequisicaoAdaptador) {
        ProdutoRespostaAdaptador produtoEditado = produtoControlador.edita(produtoId, produtoRequisicaoAdaptador);
        return ResponseEntity.ok(produtoEditado);
    }

    @DeleteMapping("/{produtoId}/remove")
    @Transactional
    public ResponseEntity<Void> removeProduto(@PathVariable("produtoId") Long produtoId) {
        produtoControlador.removeProduto(produtoId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<ProdutoRespostaAdaptador>> buscaProdutosPorCategoria(@PathVariable String categoria) {
        return ResponseEntity.ok(produtoControlador.buscaProdutosPorCategoria(categoria));
    }
}
