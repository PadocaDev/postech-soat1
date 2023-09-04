package com.padocadev.external.configuracao;

import com.padocadev.interfaces.cliente.BuscaClientePorCpfCasoDeUsoInterface;
import com.padocadev.interfaces.cliente.CriaClienteCasoDeUsoInterface;
import com.padocadev.interfaces.pagamento.*;
import com.padocadev.interfaces.pedido.BuscaPedidoCasoDeUsoInterface;
import com.padocadev.interfaces.pedido.CriaPedidoCasoDeUsoInterface;
import com.padocadev.interfaces.produto.*;
import com.padocadev.usecases.cliente.BuscaClientePorCpfCasoDeUso;
import com.padocadev.usecases.cliente.CriaClienteCasoDeUso;
import com.padocadev.usecases.pagamento.*;
import com.padocadev.usecases.pedido.BuscaPedidoCasoDeUso;
import com.padocadev.usecases.pedido.CriaPedidoCasoDeUso;
import com.padocadev.usecases.produto.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;

import java.awt.image.BufferedImage;

@Configuration
public class BeanConfiguracaoCasosDeUso {

    @Bean
    public RemoveProdutoCasoDeUsoInterface removeProdutoCasoDeUsoInterface() {
        return new RemoveProdutoCasoDeUso();
    }

    @Bean
    public EditaProdutoCasoDeUsoInterface editaProdutoCasoDeUsoInterface() {
        return new EditaProdutoCasoDeUso();
    }

    @Bean
    public CriaProdutoCasoDeUsoInterface criaProdutoCasoDeUsoInterface() {
        return new CriaProdutoCasoDeUso();
    }

    @Bean
    public BuscaProdutoPorIdCasoDeUsoInterface buscaProdutoPorIdCasoDeUsoInterface() {
        return new BuscaProdutoPorIdCasoDeUso();
    }

    @Bean
    public BuscaProdutoPorCategoriaCasoDeUsoInterface buscaProdutoPorCategoriaCasoDeUsoInterface() {
        return new BuscaProdutoPorCategoriaCasoDeUso();
    }

    @Bean
    public CriaPedidoCasoDeUsoInterface criaPedidoCasoDeUsoInterface() {
        return new CriaPedidoCasoDeUso();
    }

    @Bean
    public BuscaPedidoCasoDeUsoInterface buscaPedidoCasoDeUsoInterface() {
        return new BuscaPedidoCasoDeUso();
    }

    @Bean
    public CriaClienteCasoDeUsoInterface criaClienteCasoDeUsoInterface() {
        return new CriaClienteCasoDeUso();
    }

    @Bean
    public BuscaClientePorCpfCasoDeUsoInterface buscaClientePorCpfCasoDeUsoInterface() {
        return new BuscaClientePorCpfCasoDeUso();
    }

    @Bean
    public GeraCodigoQRCasoDeUsoInterface geraCodigoQRCasoDeUso() {
        return new GeraCodigoQRCasoDeUso();
    }

    @Bean
    public NotificaPagamentoCriacaoPedidoCasoDeUsoInterface notificaPagamentoCriacaoPedidoCasoDeUso() {
        return new NotificaPagamentoCriacaoPedidoCasoDeUso();
    }

    @Bean
    public CriaPagamentoCasoDeUsoInterface criaPagamentoCasoDeUsoInterface() {
        return new CriaPagamentoCasoDeUso();
    }

    @Bean
    public HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
        return new BufferedImageHttpMessageConverter();
    }

}
