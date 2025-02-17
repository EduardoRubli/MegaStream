package es.fempa.acd.MegaStream.services;


import es.fempa.acd.MegaStream.entities.Director;
import es.fempa.acd.MegaStream.repositories.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DirectorService {

    @Autowired
    private DirectorRepository directorRepository;

    public Director crearDirector(Director director) {
        return directorRepository.save(director);
    }

    public List<Director> obtenerTodosLosDirectores() {
        return directorRepository.findAll();
    }

    public Optional<Director> obtenerDirectorPorId(Long id) {
        return directorRepository.findById(id);
    }

    public Director actualizarDirector(Long id, Director director) {
        if (directorRepository.existsById(id)) {
            director.setIdDirector(id);
            return directorRepository.save(director);
        }
        return null;
    }

    public void eliminarDirector(Long id) {
        directorRepository.deleteById(id);
    }
}