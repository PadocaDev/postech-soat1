package com.padocadev.dominio.casodeuso;

import com.padocadev.dominio.entidade.Cliente;
import com.padocadev.dominio.porta.cliente.BuscaClientePorCpfAdaptadorPorta;
import com.padocadev.dominio.porta.cliente.BuscaClientePorCpfCasoDeUsoPorta;

public class BuscaClientePorCpfCasoDeUso implements BuscaClientePorCpfCasoDeUsoPorta {

    private final BuscaClientePorCpfAdaptadorPorta buscaClientePorCpfAdaptadorPorta;


    public BuscaClientePorCpfCasoDeUso(BuscaClientePorCpfAdaptadorPorta buscaClientePorCpfAdaptadorPorta) {
        this.buscaClientePorCpfAdaptadorPorta = buscaClientePorCpfAdaptadorPorta;
    }

    @Override
    public Cliente buscaPorCpf(String cpf) {
        return buscaClientePorCpfAdaptadorPorta.buscarPorCpf(cpf);
    }
}
