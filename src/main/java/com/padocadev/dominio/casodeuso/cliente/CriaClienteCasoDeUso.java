package com.padocadev.dominio.casodeuso.cliente;

import com.padocadev.dominio.excecao.cliente.JaExisteClienteExcecao;
import com.padocadev.dominio.entidade.cliente.Cliente;
import com.padocadev.dominio.porta.cliente.*;

public class CriaClienteCasoDeUso implements CriaClienteCasoDeUsoPorta {

    private final ClienteRepositorioAdaptadorPorta clienteRepositorioAdaptadorPorta;

    public CriaClienteCasoDeUso(ClienteRepositorioAdaptadorPorta clienteRepositorioAdaptadorPorta) {
        this.clienteRepositorioAdaptadorPorta = clienteRepositorioAdaptadorPorta;
    }

    @Override
    public Cliente criar(Cliente cliente) {
        if (clienteRepositorioAdaptadorPorta.existeComCpf(cliente.getCpf()) || clienteRepositorioAdaptadorPorta.existeComEmail(cliente.getEmail())) {
            throw new JaExisteClienteExcecao();
        }

        return clienteRepositorioAdaptadorPorta.criar(cliente);
    }
}
