package com.padocadev.interfaces.cliente;

import com.padocadev.adapters.requisicao.cliente.ClienteRequisicaoAdaptador;
import com.padocadev.adapters.resposta.cliente.ClienteRespostaAdaptador;

public interface ClienteControladorInterface {
    ClienteRespostaAdaptador criaCliente(ClienteRequisicaoAdaptador clienteRequisicao);
    ClienteRespostaAdaptador buscaClientePorCpf(String cpf);
}
