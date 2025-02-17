package es.fempa.acd.MegaStream.services;

import es.fempa.acd.MegaStream.entities.Pelicula;
import es.fempa.acd.MegaStream.repositories.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;

    // Crear una nueva película
    public Pelicula crearPelicula(Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }

    // Obtener todas las películas
    public List<Pelicula> obtenerTodasLasPeliculas() {
        return peliculaRepository.findAll();
    }

    // Obtener una película por ID.
    public Optional<Pelicula> obtenerPeliculaPorId(Long id) {
        return peliculaRepository.findById(id);
    }

    // Verificar si existe una pelicula concreta.
    public boolean existePeliculaPorId(Long id) {
        return peliculaRepository.existsById(id);
    }

    // Actualizar una película.
    public Pelicula actualizarPelicula(Long id, Pelicula pelicula) {
        if (peliculaRepository.existsById(id)) {
            pelicula.setIdPelicula(id);
            return peliculaRepository.save(pelicula);
        }
        return null;
    }

    // Eliminar una película
    public void eliminarPelicula(Long id) {
        peliculaRepository.deleteById(id);
    }

    public List<Pelicula> obtenerPeliculasPorNombre(String nombre) {
        return peliculaRepository.findByNombre(nombre);  // Si lo has definido en el repositorio
    }

    public List<Pelicula> obtenerPeliculasPorNombreParcial(String nombre) {
        return peliculaRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Pelicula> obtenerPeliculasPorGenero(String genero) {
        return peliculaRepository.findByGeneroIgnoreCase(genero);
    }
}