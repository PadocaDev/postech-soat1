package com.padocadev.adaptador.cliente.repositorio;

import com.padocadev.configuracao.excecao.cliente.ClienteNaoExisteExcecao;
import com.padocadev.dominio.entidade.Cliente;
import com.padocadev.dominio.porta.cliente.BuscaClientePorCpfAdaptadorPorta;
import org.springframework.stereotype.Component;

@Component
public class BuscaClientePorCpfAdaptador implements BuscaClientePorCpfAdaptadorPorta {
    private final ClienteRepositorioJpa clienteRepositorioJpa;

    public BuscaClientePorCpfAdaptador(ClienteRepositorioJpa clienteRepositorioJpa) {
        this.clienteRepositorioJpa = clienteRepositorioJpa;
    }

    @Override
    public Cliente buscarPorCpf(String cpf) {
        ClienteEntidade clienteEntidade = clienteRepositorioJpa.findByCpf(cpf).orElseThrow(ClienteNaoExisteExcecao::new);
        return new Cliente(clienteEntidade.getId(), clienteEntidade.getDataCadastro(), clienteEntidade.getNome(),
                clienteEntidade.getEmail(), clienteEntidade.getCpf());
    }
}

