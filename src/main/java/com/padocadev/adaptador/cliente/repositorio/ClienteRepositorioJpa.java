package com.padocadev.adaptador.cliente.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepositorioJpa extends JpaRepository<ClienteEntidade, Long> {
    Optional<ClienteEntidade> findByCpf(String cpf);

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);
}
