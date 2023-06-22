package com.padocadev.infraestrutura.adaptador.repositorio.produto;

import com.padocadev.dominio.entidade.produto.Categoria;
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
