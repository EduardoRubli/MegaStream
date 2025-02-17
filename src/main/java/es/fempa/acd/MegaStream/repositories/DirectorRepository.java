package es.fempa.acd.MegaStream.repositories;
import es.fempa.acd.MegaStream.entities.Actor;
import es.fempa.acd.MegaStream.entities.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {
}
