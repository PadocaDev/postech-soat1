package com.padocadev.adaptador.cliente.repositorio;

import com.padocadev.configuracao.excecao.cliente.JaExisteClienteExcecao;
import com.padocadev.dominio.entidade.Cliente;
import com.padocadev.dominio.porta.cliente.CriaClienteAdaptadorPorta;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CriaClienteAdaptador implements CriaClienteAdaptadorPorta {

    private final ClienteRepositorioJpa clienteRepositorioJpa;

    public CriaClienteAdaptador(ClienteRepositorioJpa clienteRepositorioJpa) {
        this.clienteRepositorioJpa = clienteRepositorioJpa;
    }

    @Override
    @Transactional
    public Cliente criar(Cliente cliente) {
        if (clienteRepositorioJpa.existsByCpf(cliente.getCpf()) || clienteRepositorioJpa.existsByEmail(cliente.getEmail())) {
            throw new JaExisteClienteExcecao();
        }

        ClienteEntidade clienteEntidade = new ClienteEntidade(cliente.getDataCadastro(), cliente.getNome(),
                cliente.getEmail(), cliente.getCpf());

        clienteRepositorioJpa.save(clienteEntidade);
        cliente.setId(clienteEntidade.getId());
        return cliente;
    }
}
