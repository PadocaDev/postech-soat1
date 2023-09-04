package com.padocadev.external.pagamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PagamentoRepositorioJpa extends JpaRepository<PagamentoEntidadeJpa, Long> {
    Optional<PagamentoEntidadeJpa> findByPedidoId(Long id);
}
