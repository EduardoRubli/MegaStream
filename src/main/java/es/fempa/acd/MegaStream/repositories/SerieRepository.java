package es.fempa.acd.MegaStream.repositories;

import es.fempa.acd.MegaStream.entities.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long> {
    List<Serie> findByNombreContainingIgnoreCase(String nombre);
    List<Serie> findByGeneroIgnoreCase(String genero);
    boolean existsById(Long idSerie);
}