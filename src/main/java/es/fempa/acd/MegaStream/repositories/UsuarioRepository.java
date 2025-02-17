package es.fempa.acd.MegaStream.repositories;
import es.fempa.acd.MegaStream.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Buscar usuario por email
    Optional<Usuario> findByEmailIgnoreCase(String email);
    boolean existsByEmail(String email);
    List<Usuario> findByNombreContainingIgnoreCase(String nombre);
    Usuario findByNombreIgnoreCase(String nombre);
}
