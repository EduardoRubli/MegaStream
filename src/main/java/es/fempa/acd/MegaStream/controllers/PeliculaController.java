package es.fempa.acd.MegaStream.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import es.fempa.acd.MegaStream.entities.Pelicula;
import es.fempa.acd.MegaStream.entities.Puntuacion;
import es.fempa.acd.MegaStream.entities.Usuario;
import es.fempa.acd.MegaStream.entities.views.Views;
import es.fempa.acd.MegaStream.services.PeliculaService;
import es.fempa.acd.MegaStream.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PeliculaController {

    private final PeliculaService peliculaService;
    private final UsuarioService usuarioService;

    @Autowired
    public PeliculaController(PeliculaService peliculaService, UsuarioService usuarioService) {
        this.peliculaService = peliculaService;
        this.usuarioService = usuarioService;
    }

    // Endpoint para mostrar todas las películas.
    @GetMapping("/cliente/peliculas/mostrar")
    @JsonView(Views.Resumen.class)
    public @ResponseBody Iterable<Pelicula> getAllPeliculas() {
        // Devuelve un JSON con todas las películas..
        return peliculaService.obtenerTodasLasPeliculas();
    }

    // Endpoint para buscar película por ID.
    @GetMapping("/cliente/peliculas/{id}")
    @JsonView(Views.Detalle.class)
    public ResponseEntity<?> buscarPeliculaPorId(@PathVariable Long id) {
        // Verifica si la película existe.
        if (peliculaService.existePeliculaPorId(id)) {
            // Recupera la película y la devuelve.
            Pelicula pelicula = peliculaService.obtenerPeliculaPorId(id).get();
            return ResponseEntity.ok(pelicula);
        }
        // Devuelve un error 404 si no existe.
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pelicula no encontrada.");
    }

    // Endpoint para buscar películas por nombre parcial.
    @GetMapping("/cliente/peliculas/buscarNombre")
    @JsonView(Views.Resumen.class)
    public ResponseEntity<List<Pelicula>> buscarPeliculasPorNombreParcial(@RequestParam String nombre) {
        List<Pelicula> peliculas = peliculaService.obtenerPeliculasPorNombreParcial(nombre);
        if (peliculas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(peliculas);
    }

    @GetMapping("/cliente/peliculas/buscarGenero")
    @JsonView(Views.Resumen.class)
    public ResponseEntity<List<Pelicula>> buscarPeliculasPorGenero(@RequestParam String genero) {
        List<Pelicula> peliculas = peliculaService.obtenerPeliculasPorGenero(genero);
        if (peliculas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(peliculas);
    }

    @PostMapping("/cliente/peliculas/puntuar")
    @JsonView(Views.Resumen.class)
    public ResponseEntity<?> puntuarPelicula(@RequestParam Long idPelicula,
                                             @RequestParam Long idUsuario,
                                             @RequestParam Double nota) {
        Optional<Pelicula> peliculaOpt = peliculaService.obtenerPeliculaPorId(idPelicula);
        Optional<Usuario> usuarioOpt = usuarioService.obtenerUsuarioPorId(idUsuario);

        if (peliculaOpt.isPresent() && usuarioOpt.isPresent()) {
            Pelicula pelicula = peliculaOpt.get();
            Usuario usuario = usuarioOpt.get();

            Puntuacion nuevaPuntuacion = new Puntuacion(pelicula, usuario, nota);
            pelicula.agregarPuntuacion(nuevaPuntuacion);

            peliculaService.actualizarPelicula(idPelicula, pelicula);
            return ResponseEntity.ok("Puntuación agregada con éxito. \nNueva nota media: " + pelicula.getPuntuacion());
        } else {
            return ResponseEntity.badRequest().body("Pelicula o usuario no encontrado");
        }
    }
}