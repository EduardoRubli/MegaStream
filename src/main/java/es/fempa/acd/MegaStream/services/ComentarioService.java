package es.fempa.acd.MegaStream.services;

import es.fempa.acd.MegaStream.entities.Comentario;
import es.fempa.acd.MegaStream.entities.Pelicula;
import es.fempa.acd.MegaStream.entities.Serie;
import es.fempa.acd.MegaStream.entities.Usuario;
import es.fempa.acd.MegaStream.repositories.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    public Comentario crearComentario(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    // Muestra los comentarios de una película.
    public List<Comentario> obtenerComentariosPorPelicula(Pelicula pelicula) {
        return comentarioRepository.findComentariosByPelicula(pelicula);
    }

    // Muestra los comentarios de una serie.
    public List<Comentario> obtenerComentariosPorSerie(Serie serie) {
        return comentarioRepository.findComentariosBySerie(serie);
    }

    public List<Comentario> obtenerTodosLosComentarios() {
        return comentarioRepository.findAll();
    }

    public Optional<Comentario> obtenerComentarioPorId(Long id) {
        return comentarioRepository.findById(id);
    }

    public Comentario actualizarComentario(Long id, Comentario comentario) {
        if (comentarioRepository.existsById(id)) {
            comentario.setIdComentario(id);
            return comentarioRepository.save(comentario);
        }
        return null;
    }

    public void eliminarComentario(Long id) {
        comentarioRepository.deleteById(id);
    }
}