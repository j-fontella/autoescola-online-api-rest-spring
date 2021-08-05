package br.com.joaofontella.autoescolaonlinespringangular.repository;

import br.com.joaofontella.autoescolaonlinespringangular.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value = "select * from usuario where usuario = :nome", nativeQuery = true)
    Optional<Usuario> findByName(String nome);

}
