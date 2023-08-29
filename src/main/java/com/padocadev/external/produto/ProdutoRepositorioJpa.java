package com.padocadev.external.produto;

import com.padocadev.entities.produto.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepositorioJpa extends JpaRepository<ProdutoEntidadeJpa, Long> {
    boolean existsByNome(String nome);
    Optional<ProdutoEntidadeJpa> findByNome(String nome);
    List<ProdutoEntidadeJpa> findAllByCategoria(Categoria categoria);
}
