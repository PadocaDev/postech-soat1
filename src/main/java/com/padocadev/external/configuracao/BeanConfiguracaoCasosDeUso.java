package com.padocadev.external.configuracao;

import com.padocadev.interfaces.cliente.BuscaClientePorCpfCasoDeUsoInterface;
import com.padocadev.interfaces.cliente.CriaClienteCasoDeUsoInterface;
import com.padocadev.interfaces.pagamento.GeraCodigoQRCasoDeUsoInterface;
import com.padocadev.interfaces.pagamento.NotificaPagamentoCriacaoPedidoCasoDeUsoInterface;
import com.padocadev.interfaces.pedido.BuscaPedidoCasoDeUsoInterface;
import com.padocadev.interfaces.pedido.CriaPedidoCasoDeUsoInterface;
import com.padocadev.interfaces.produto.*;
import com.padocadev.usecases.cliente.BuscaClientePorCpfCasoDeUso;
import com.padocadev.usecases.cliente.CriaClienteCasoDeUso;
import com.padocadev.usecases.pagamento.GeraCodigoQRCasoDeUso;
import com.padocadev.usecases.pagamento.NotificaPagamentoCriacaoPedidoCasoDeUso;
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
    RemoveProdutoCasoDeUsoInterface removeProdutoCasoDeUsoInterface() {
        return new RemoveProdutoCasoDeUso();
    }

    @Bean
    EditaProdutoCasoDeUsoInterface editaProdutoCasoDeUsoInterface() {
        return new EditaProdutoCasoDeUso();
    }

    @Bean
    CriaProdutoCasoDeUsoInterface criaProdutoCasoDeUsoInterface() {
        return new CriaProdutoCasoDeUso();
    }

    @Bean
    BuscaProdutoPorIdCasoDeUsoInterface buscaProdutoPorIdCasoDeUsoInterface() {
        return new BuscaProdutoPorIdCasoDeUso();
    }

    @Bean
    BuscaProdutoPorCategoriaCasoDeUsoInterface buscaProdutoPorCategoriaCasoDeUsoInterface() {
        return new BuscaProdutoPorCategoriaCasoDeUso();
    }

    @Bean
    CriaPedidoCasoDeUsoInterface criaPedidoCasoDeUsoInterface() {
        return new CriaPedidoCasoDeUso();
    }

    @Bean
    BuscaPedidoCasoDeUsoInterface buscaPedidoCasoDeUsoInterface() {
        return new BuscaPedidoCasoDeUso();
    }

    @Bean
    CriaClienteCasoDeUsoInterface criaClienteCasoDeUsoInterface() {
        return new CriaClienteCasoDeUso();
    }

    @Bean
    BuscaClientePorCpfCasoDeUsoInterface buscaClientePorCpfCasoDeUsoInterface() {
        return new BuscaClientePorCpfCasoDeUso();
    }

    @Bean
    GeraCodigoQRCasoDeUsoInterface geraCodigoQRCasoDeUso() {
        return new GeraCodigoQRCasoDeUso();
    }

    @Bean
    NotificaPagamentoCriacaoPedidoCasoDeUsoInterface notificaPagamentoCriacaoPedidoCasoDeUso() {
        return new NotificaPagamentoCriacaoPedidoCasoDeUso();
    }

    @Bean
    public HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
        return new BufferedImageHttpMessageConverter();
    }

}
