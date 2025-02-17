package es.fempa.acd.MegaStream.repositories;
import es.fempa.acd.MegaStream.entities.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
    // Devuelve lista de películas por nombre.
    List<Pelicula> findByNombre(String nombre);
    // Devuelve lista de películas que contengan el nombre.
    List<Pelicula> findByNombreContainingIgnoreCase(String nombre);
    // Deviuelve lista de películas por género.
    List<Pelicula> findByGeneroIgnoreCase(String genero);
}
