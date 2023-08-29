package com.padocadev.api.rest;

import com.padocadev.adapters.requisicao.cliente.ClienteRequisicaoAdaptador;
import com.padocadev.adapters.resposta.cliente.ClienteRespostaAdaptador;
import com.padocadev.interfaces.cliente.ClienteControladorInterface;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/clientes")
public class ClienteApi {

    private final ClienteControladorInterface clienteControlador;

    public ClienteApi(ClienteControladorInterface clienteControlador) {
        this.clienteControlador = clienteControlador;
    }

    @PostMapping
    public ResponseEntity<ClienteRespostaAdaptador> criaCliente(@RequestBody @Valid ClienteRequisicaoAdaptador clienteRequisicao) {
        ClienteRespostaAdaptador clienteCriado = clienteControlador.criaCliente(clienteRequisicao);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{cpf}")
                .buildAndExpand(clienteCriado.cpf())
                .toUri();
        return ResponseEntity.created(location).body(clienteCriado);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ClienteRespostaAdaptador> buscaClientePorCpf(@PathVariable String cpf) {
        ClienteRespostaAdaptador clienteEncontrado = clienteControlador.buscaClientePorCpf(cpf);
        return ResponseEntity.ok(clienteEncontrado);
    }
}
