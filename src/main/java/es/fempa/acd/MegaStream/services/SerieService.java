package es.fempa.acd.MegaStream.services;

import es.fempa.acd.MegaStream.entities.Pelicula;
import es.fempa.acd.MegaStream.entities.Serie;
import es.fempa.acd.MegaStream.repositories.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SerieService {

    @Autowired
    private SerieRepository serieRepository;

    // Obtener todas las series
    public List<Serie> obtenerTodasLasSeries() {
        return serieRepository.findAll();
    }

    // Verificar si existe una serie concreta.
    public boolean existeSeriePorId(Long id) {
        return serieRepository.existsById(id);
    }

    // Obtener una serie por ID
    public Optional<Serie> obtenerSeriePorId(Long id) {
        return serieRepository.findById(id);
    }

    // Buscar series por nombre
    public List<Serie> obtenerSeriesPorNombre(String nombre) {
        return serieRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Serie> obtenerSeriesPorGenero(String genero) {
        return serieRepository.findByGeneroIgnoreCase(genero);
    }

    // Actualizar una película.
    public Serie actualizarSerie(Long id, Serie serie) {
        if (serieRepository.existsById(id)) {
            serie.setIdSerie(id);
            return serieRepository.save(serie);
        }
        return null;
    }
}