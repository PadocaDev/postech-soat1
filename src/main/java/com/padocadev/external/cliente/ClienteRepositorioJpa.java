package com.padocadev.external.cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepositorioJpa extends JpaRepository<ClienteEntidadeJpa, Long> {
    Optional<ClienteEntidadeJpa> findByCpf(String cpf);
    Optional<ClienteEntidadeJpa> findByEmail(String email);
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
}
