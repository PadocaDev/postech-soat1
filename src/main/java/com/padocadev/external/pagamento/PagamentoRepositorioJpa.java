package com.padocadev.external.pagamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepositorioJpa extends JpaRepository<PagamentoEntidadeJpa, Long> {
}
