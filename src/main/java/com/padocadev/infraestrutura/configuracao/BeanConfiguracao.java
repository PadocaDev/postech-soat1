package com.padocadev.infraestrutura.configuracao;

import com.padocadev.dominio.casodeuso.pedido.BuscaPedidoCasoDeUso;
import com.padocadev.dominio.casodeuso.pedido.CriaPedidoCasoDeUso;
import com.padocadev.dominio.porta.pedido.*;
import com.padocadev.infraestrutura.adaptador.repositorio.cliente.ClienteRepositorioAdaptadorJpa;
import com.padocadev.dominio.casodeuso.cliente.BuscaClientePorCpfCasoDeUso;
import com.padocadev.dominio.casodeuso.cliente.CriaClienteCasoDeUso;
import com.padocadev.dominio.casodeuso.produto.*;
import com.padocadev.dominio.porta.cliente.BuscaClientePorCpfCasoDeUsoPorta;
import com.padocadev.dominio.porta.cliente.CriaClienteCasoDeUsoPorta;
import com.padocadev.infraestrutura.adaptador.repositorio.pedido.PedidoRepositorioAdaptadorJpa;
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
    CriaPedidoCasoDeUsoPorta criaPedidoCasoDeUsoPorta(ClienteRepositorioAdaptadorJpa clienteRepositorioAdaptadorJpa, PedidoRepositorioAdaptadorJpa pedidoRepositorioAdaptadorJpa, ProdutoRepositorioAdaptadorJpa produtoRepositorioAdaptadorJpa ) {
        return new CriaPedidoCasoDeUso(pedidoRepositorioAdaptadorJpa, produtoRepositorioAdaptadorJpa, clienteRepositorioAdaptadorJpa);
    }

    @Bean
    BuscaTodosPedidosCasoDeUsoPorta buscaTodosPedidosCasoDeUso(PedidoRepositorioAdaptadorJpa pedidoRepositorioAdaptadorJpa) {
        return new BuscaPedidoCasoDeUso(pedidoRepositorioAdaptadorJpa);
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

    @Bean
    RemoveProdutoCasoDeUsoPorta removeProdutoCasoDeUsoPorta(ProdutoRepositorioAdaptadorJpa produtoRepositorioAdaptadorJpa) {
        return new RemoveProdutoCasoDeUso(produtoRepositorioAdaptadorJpa);
    }
    @Bean
    BuscaProdutoPorCategoriaCasoDeUsoPorta buscaProdutoPorCategoriaCasoDeUsoPorta(ProdutoRepositorioAdaptadorJpa produtoRepositorioAdaptadorJpa) {
        return new BuscaProdutoPorCategoriaCasoDeUso(produtoRepositorioAdaptadorJpa);
    }
}
