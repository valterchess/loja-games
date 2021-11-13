package br.org.generation.squadspringgames.repository;

import br.org.generation.squadspringgames.model.Usuario;
import br.org.generation.squadspringgames.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public Optional<Usuario> findByUsuario(String usuario);
}
