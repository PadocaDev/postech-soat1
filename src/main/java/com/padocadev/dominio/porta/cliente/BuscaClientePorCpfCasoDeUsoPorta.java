package com.padocadev.dominio.porta.cliente;

import com.padocadev.dominio.entidade.cliente.Cliente;

public interface BuscaClientePorCpfCasoDeUsoPorta {
    Cliente buscaPorCpf(String cpf);
}
