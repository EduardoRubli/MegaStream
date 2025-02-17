package es.fempa.acd.MegaStream.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import es.fempa.acd.MegaStream.entities.Actor;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
}
