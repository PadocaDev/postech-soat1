package com.padocadev.configuracao;

import com.padocadev.adaptador.cliente.repositorio.jpa.ClienteRepositorioAdaptadorJpa;
import com.padocadev.adaptador.cliente.repositorio.jpa.ClienteRepositorioJpa;
import com.padocadev.dominio.casodeuso.cliente.BuscaClientePorCpfCasoDeUso;
import com.padocadev.dominio.casodeuso.cliente.CriaClienteCasoDeUso;
import com.padocadev.dominio.porta.cliente.BuscaClientePorCpfCasoDeUsoPorta;
import com.padocadev.dominio.porta.cliente.CriaClienteCasoDeUsoPorta;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguracao {

    @Bean
    CriaClienteCasoDeUsoPorta criaClienteCasoDeUsoPorta(ClienteRepositorioAdaptadorJpa clienteRepositorioAdaptadorJpa) {
        return new CriaClienteCasoDeUso(clienteRepositorioAdaptadorJpa);
    }

    @Bean
    BuscaClientePorCpfCasoDeUsoPorta buscaClientePorCpfCasoDeUsoPorta(ClienteRepositorioAdaptadorJpa clienteRepositorioAdaptadorJpa) {
        return new BuscaClientePorCpfCasoDeUso(clienteRepositorioAdaptadorJpa);
    }
}
