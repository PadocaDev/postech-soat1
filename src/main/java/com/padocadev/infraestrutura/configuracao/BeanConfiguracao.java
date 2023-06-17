package com.padocadev.infraestrutura.configuracao;

import com.padocadev.dominio.casodeuso.cliente.BuscaClientePorCpfCasoDeUso;
import com.padocadev.dominio.casodeuso.cliente.CriaClienteCasoDeUso;
import com.padocadev.dominio.casodeuso.produto.*;
import com.padocadev.dominio.porta.cliente.BuscaClientePorCpfCasoDeUsoPorta;
import com.padocadev.dominio.porta.cliente.CriaClienteCasoDeUsoPorta;
import com.padocadev.dominio.porta.produto.*;
import com.padocadev.infraestrutura.adaptador.repositorio.cliente.ClienteRepositorioAdaptadorJpa;
import com.padocadev.infraestrutura.adaptador.repositorio.produto.ProdutoRepositorioAdaptadorJpa;
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

    @Bean
    CriaProdutoCasoDeUsoPorta criaProdutoCasoDeUsoPorta(ProdutoRepositorioAdaptadorJpa produtoRepositorioAdaptadorJpa) {
        return new CriaProdutoCasoDeUso(produtoRepositorioAdaptadorJpa);
    }

    @Bean
    BuscaProdutoPorIdCasoDeUsoPorta buscaProdutoPorIdCasoDeUsoPorta(ProdutoRepositorioAdaptadorJpa produtoRepositorioAdaptadorJpa) {
        return new BuscaProdutoPorIdCasoDeUso(produtoRepositorioAdaptadorJpa);
    }

    @Bean
    EditaProdutoCasoDeUsoPorta editaProdutoCasoDeUsoPorta(ProdutoRepositorioAdaptadorJpa produtoRepositorioAdaptadorJpa) {
        return new EditaProdutoCasoDeUso(produtoRepositorioAdaptadorJpa);
    }
}
