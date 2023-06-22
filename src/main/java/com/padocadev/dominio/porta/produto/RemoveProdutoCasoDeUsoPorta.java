package com.padocadev.dominio.porta.produto;

import com.padocadev.dominio.entidade.produto.Produto;

public interface RemoveProdutoCasoDeUsoPorta {
    Produto remover(Long produtoId);
}
