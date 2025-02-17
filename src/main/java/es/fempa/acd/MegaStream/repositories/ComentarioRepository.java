package es.fempa.acd.MegaStream.repositories;
import es.fempa.acd.MegaStream.entities.Comentario;
import es.fempa.acd.MegaStream.entities.Pelicula;
import es.fempa.acd.MegaStream.entities.Serie;
import es.fempa.acd.MegaStream.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    // Devuelve comentarios para una película.
    public List<Comentario> findComentariosByPelicula(Pelicula pelicula);
    // Devuelve comentarios asociados a una serie.
    public List<Comentario> findComentariosBySerie(Serie serie);
}
