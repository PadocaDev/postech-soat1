package com.padocadev.dominio.casodeuso.cliente;

import com.padocadev.configuracao.excecao.cliente.ClienteNaoExisteExcecao;
import com.padocadev.dominio.entidade.cliente.Cliente;
import com.padocadev.dominio.porta.cliente.*;

public class BuscaClientePorCpfCasoDeUso implements BuscaClientePorCpfCasoDeUsoPorta {

    private final ClienteRepositorioAdaptadorPorta clienteRepositorioAdaptadorPorta;

    public BuscaClientePorCpfCasoDeUso(ClienteRepositorioAdaptadorPorta clienteRepositorioAdaptadorPorta) {
        this.clienteRepositorioAdaptadorPorta = clienteRepositorioAdaptadorPorta;
    }

    @Override
    public Cliente buscaPorCpf(String cpf) {
        return clienteRepositorioAdaptadorPorta.buscaClientePorCpf(cpf).orElseThrow(ClienteNaoExisteExcecao::new);
    }
}
