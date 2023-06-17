package com.padocadev.aplicacao.rest.cliente;

import com.padocadev.aplicacao.requisicao.cliente.ClienteRequisicao;
import com.padocadev.aplicacao.resposta.cliente.ClienteResposta;
import com.padocadev.dominio.entidade.cliente.Cliente;
import com.padocadev.dominio.porta.cliente.BuscaClientePorCpfCasoDeUsoPorta;
import com.padocadev.dominio.porta.cliente.CriaClienteCasoDeUsoPorta;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/clientes")
public class ClienteControlador {

    private final CriaClienteCasoDeUsoPorta criaClienteCasoDeUso;
    private final BuscaClientePorCpfCasoDeUsoPorta buscaClientePorCpfCasoDeUso;

    public ClienteControlador(CriaClienteCasoDeUsoPorta criaClienteCasoDeUso, BuscaClientePorCpfCasoDeUsoPorta buscaClientePorCpfCasoDeUso) {
        this.criaClienteCasoDeUso = criaClienteCasoDeUso;
        this.buscaClientePorCpfCasoDeUso = buscaClientePorCpfCasoDeUso;
    }

    @PostMapping
    public ResponseEntity<ClienteResposta> criaCliente(@RequestBody @Valid ClienteRequisicao clienteRequisicao) {
        Cliente clienteCriado = criaClienteCasoDeUso.criar(clienteRequisicao.converteParaCliente());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{cpf}")
                .buildAndExpand(clienteCriado.getCpf())
                .toUri();
        return ResponseEntity.created(location).body(ClienteResposta.deCliente(clienteCriado));
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ClienteResposta> buscaClientePorCpf(@PathVariable String cpf) {
        Cliente cliente = buscaClientePorCpfCasoDeUso.buscaPorCpf(cpf);
        return ResponseEntity.ok(ClienteResposta.deCliente(cliente));
    }
}
