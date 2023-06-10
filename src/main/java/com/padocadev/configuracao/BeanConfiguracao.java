package com.padocadev.configuracao;

import com.padocadev.adaptador.cliente.repositorio.BuscaClientePorCpfAdaptador;
import com.padocadev.adaptador.cliente.repositorio.CriaClienteAdaptador;
import com.padocadev.dominio.casodeuso.BuscaClientePorCpfCasoDeUso;
import com.padocadev.dominio.casodeuso.CriaClienteCasoDeUso;
import com.padocadev.dominio.porta.cliente.BuscaClientePorCpfCasoDeUsoPorta;
import com.padocadev.dominio.porta.cliente.CriaClienteCasoDeUsoPorta;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguracao {

    @Bean
    CriaClienteCasoDeUsoPorta criaClienteCasoDeUsoPorta(CriaClienteAdaptador criaClienteAdaptador) {
        return new CriaClienteCasoDeUso(criaClienteAdaptador);
    }

    @Bean
    BuscaClientePorCpfCasoDeUsoPorta buscaClientePorCpfCasoDeUsoPorta(BuscaClientePorCpfAdaptador buscaClientePorCpfAdaptador) {
        return new BuscaClientePorCpfCasoDeUso(buscaClientePorCpfAdaptador);
    }
}
