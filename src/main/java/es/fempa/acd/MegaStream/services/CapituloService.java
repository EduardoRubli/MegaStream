package es.fempa.acd.MegaStream.services;

import es.fempa.acd.MegaStream.entities.Capitulo;
import es.fempa.acd.MegaStream.repositories.CapituloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CapituloService {

    @Autowired
    private CapituloRepository capituloRepository;

    public Capitulo crearCapitulo(Capitulo capitulo) {
        return capituloRepository.save(capitulo);
    }

    public List<Capitulo> obtenerTodosLosCapitulos() {
        return capituloRepository.findAll();
    }

    public Optional<Capitulo> obtenerCapituloPorId(Long id) {
        return capituloRepository.findById(id);
    }

    public Capitulo actualizarCapitulo(Long id, Capitulo capitulo) {
        if (capituloRepository.existsById(id)) {
            capitulo.setIdCapitulo(id);
            return capituloRepository.save(capitulo);
        }
        return null;
    }

    public void eliminarCapitulo(Long id) {
        capituloRepository.deleteById(id);
    }
}
