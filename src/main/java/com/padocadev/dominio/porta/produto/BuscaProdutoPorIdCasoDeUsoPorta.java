package com.padocadev.dominio.porta.produto;

import com.padocadev.dominio.entidade.produto.Produto;

public interface BuscaProdutoPorIdCasoDeUsoPorta {
    Produto buscaPorId(Long produtoId);
}
