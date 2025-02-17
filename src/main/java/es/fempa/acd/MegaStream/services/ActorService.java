package es.fempa.acd.MegaStream.services;

import es.fempa.acd.MegaStream.entities.Actor;
import es.fempa.acd.MegaStream.repositories.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;

    public Actor crearActor(Actor actor) {
        return actorRepository.save(actor);
    }

    public List<Actor> obtenerTodosLosActores() {
        return actorRepository.findAll();
    }

    public Optional<Actor> obtenerActorPorId(Long id) {
        return actorRepository.findById(id);
    }

    public Actor actualizarActor(Long id, Actor actor) {
        if (actorRepository.existsById(id)) {
            actor.setIdActor(id);
            return actorRepository.save(actor);
        }
        return null;
    }

    public void eliminarActor(Long id) {
        actorRepository.deleteById(id);
    }
}