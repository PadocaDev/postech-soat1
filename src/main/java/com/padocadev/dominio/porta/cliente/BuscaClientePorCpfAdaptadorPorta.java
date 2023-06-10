package com.padocadev.dominio.porta.cliente;

import com.padocadev.dominio.entidade.Cliente;

public interface BuscaClientePorCpfAdaptadorPorta {
    Cliente buscarPorCpf(String cpf);
}
