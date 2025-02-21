package es.fempa.acd.MegaStream.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import es.fempa.acd.MegaStream.entities.Pelicula;
import es.fempa.acd.MegaStream.entities.Puntuacion;
import es.fempa.acd.MegaStream.entities.Serie;
import es.fempa.acd.MegaStream.entities.Usuario;
import es.fempa.acd.MegaStream.entities.views.Views;
import es.fempa.acd.MegaStream.services.SerieService;
import es.fempa.acd.MegaStream.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SerieController {

    private final SerieService serieService;
    private final UsuarioService usuarioService;

    @Autowired
    public SerieController(SerieService serieService, UsuarioService usuarioService) {
        this.serieService = serieService;
        this.usuarioService = usuarioService;
    }

    // Mostrar todas las series.
    @GetMapping("/cliente/series/mostrar")
    @JsonView(Views.Resumen.class)
    public @ResponseBody Iterable<Serie> getAllSeries() {
        return serieService.obtenerTodasLasSeries();
    }

    // Buscar serie por ID.
    @GetMapping("/cliente/series/{id}")
    @JsonView(Views.Detalle.class)
    public ResponseEntity<?> buscarSeriePorId(@PathVariable Long id) {
        if (serieService.existeSeriePorId(id)){
            // Recupera la serie y la devuelve.
            Serie serie = serieService.obtenerSeriePorId(id).get();
            return ResponseEntity.ok(serie);
        }
        // Devuelve un error 404 si no existe.
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Serie no encontrada.");
    }

    // Endpoint para buscar series por nombre parcial.
    @GetMapping("/cliente/series/buscarNombre")
    @JsonView(Views.Resumen.class)
    public ResponseEntity<?> buscarSeriesPorNombre(@RequestParam String nombre) {
        List<Serie> series = serieService.obtenerSeriesPorNombre(nombre);
        if (series.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron series que coincidan con: " + nombre);
        }
        return ResponseEntity.ok(series);
    }

    @GetMapping("/cliente/series/buscarGenero")
    @JsonView(Views.Resumen.class)
    public ResponseEntity<List<Serie>> buscarSeriesPorGenero(@RequestParam String genero) {
        List<Serie> series = serieService.obtenerSeriesPorGenero(genero);
        if (series.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(series);
    }

    // Puntuar una serie (como usuario).
    @PostMapping("/cliente/series/puntuar")
    @JsonView(Views.Resumen.class)
    public ResponseEntity<?> puntuarSerie(@RequestParam Long idSerie,
                                          @RequestParam Long idUsuario,
                                          @RequestParam Double nota) {
        Optional<Serie> serieOpt = serieService.obtenerSeriePorId(idSerie);
        Optional<Usuario> usuarioOpt = usuarioService.obtenerUsuarioPorId(idUsuario);

        if (serieOpt.isPresent() && usuarioOpt.isPresent()) {
            Serie serie = serieOpt.get();
            Usuario usuario = usuarioOpt.get();

            try {
                Puntuacion nuevaPuntuacion = new Puntuacion(serie, usuario, nota);
                serie.agregarPuntuacion(nuevaPuntuacion);

                serieService.actualizarSerie(idSerie, serie);
                return ResponseEntity.ok("Puntuación agregada con éxito. \nNueva nota media: " + serie.getPuntuacion());
            } catch (IllegalStateException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        } else {
            return ResponseEntity.badRequest().body("Serie o usuario no encontrado");
        }
    }
}