package com.padocadev.dominio.casodeuso;

import com.padocadev.dominio.entidade.Cliente;
import com.padocadev.dominio.porta.cliente.CriaClienteAdaptadorPorta;
import com.padocadev.dominio.porta.cliente.CriaClienteCasoDeUsoPorta;

public class CriaClienteCasoDeUso implements CriaClienteCasoDeUsoPorta {

    private final CriaClienteAdaptadorPorta criaClienteAdaptadorPorta;

    public CriaClienteCasoDeUso(CriaClienteAdaptadorPorta criaClienteAdaptadorPorta) {
        this.criaClienteAdaptadorPorta = criaClienteAdaptadorPorta;
    }

    @Override
    public Cliente criar(Cliente cliente) {
        criaClienteAdaptadorPorta.criar(cliente);
        return cliente;
    }
}
