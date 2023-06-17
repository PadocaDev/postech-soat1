package com.padocadev.dominio.porta.produto;

import com.padocadev.dominio.entidade.produto.Produto;

public interface EditaProdutoCasoDeUsoPorta {
    Produto edita(Long produtoId, Produto produtoParaEditar);
}
