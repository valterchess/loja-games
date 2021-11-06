package br.org.generation.squadspringgames.repository;

import br.org.generation.squadspringgames.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    public List<Categoria> findALlByTipoContainingIgnoreCase(String tipo);
}
