package es.fempa.acd.MegaStream.repositories;
import es.fempa.acd.MegaStream.entities.Capitulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapituloRepository extends JpaRepository<Capitulo, Long> {
}
