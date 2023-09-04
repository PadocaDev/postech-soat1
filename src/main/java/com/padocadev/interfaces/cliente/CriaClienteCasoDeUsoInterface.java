package com.padocadev.interfaces.cliente;

import com.padocadev.entities.cliente.Cliente;

public interface CriaClienteCasoDeUsoInterface {
    Cliente criar(Cliente cliente, ClienteGatewayInterface clienteGatewayInterface);
}
