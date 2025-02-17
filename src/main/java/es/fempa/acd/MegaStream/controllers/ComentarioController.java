package es.fempa.acd.MegaStream.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import es.fempa.acd.MegaStream.entities.Comentario;
import es.fempa.acd.MegaStream.entities.Pelicula;
import es.fempa.acd.MegaStream.entities.Serie;
import es.fempa.acd.MegaStream.entities.Usuario;
import es.fempa.acd.MegaStream.entities.views.Views;
import es.fempa.acd.MegaStream.services.ComentarioService;
import es.fempa.acd.MegaStream.services.PeliculaService;
import es.fempa.acd.MegaStream.services.SerieService;
import es.fempa.acd.MegaStream.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ComentarioController {

    private final ComentarioService comentarioService;
    private final PeliculaService peliculaService;
    private final UsuarioService usuarioService;
    private final SerieService serieService;

    @Autowired
    public ComentarioController(ComentarioService comentarioService, PeliculaService peliculaService, UsuarioService usuarioService, SerieService serieService) {
        this.comentarioService = comentarioService;
        this.peliculaService = peliculaService;
        this.usuarioService = usuarioService;
        this.serieService = serieService;
    }

    // Método para obtener usuario autenticado.
    private ResponseEntity<?> obtenerUsuarioAutenticado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Optional<Usuario> usuarioOpt = usuarioService.obtenerUsuarioPorEmail(email);

        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autenticado.");
        }

        return ResponseEntity.ok(usuarioOpt.get());
    }

    // Endpoint público que usa el método anterior.
    @GetMapping("/cliente/usuario/actual")
    public ResponseEntity<?> obtenerUsuarioActual() {
        return obtenerUsuarioAutenticado();
    }

    // Método para añadir comentario a una película o serie.
    @PostMapping("/cliente/comentarios/add")
    @JsonView(Views.Detalle.class)
    public ResponseEntity<?> addComentario(@RequestParam Long id,
                                           @RequestParam String texto,
                                           @RequestParam String tipo) {

        ResponseEntity<?> usuarioResponse = obtenerUsuarioAutenticado();
        if (usuarioResponse.getStatusCode() != HttpStatus.OK) {
            return usuarioResponse;
        }
        Usuario usuario = (Usuario) usuarioResponse.getBody();

        if ("pelicula".equalsIgnoreCase(tipo)) {
            Optional<Pelicula> peliculaOpt = peliculaService.obtenerPeliculaPorId(id);
            if (peliculaOpt.isPresent()) {
                Pelicula pelicula = peliculaOpt.get();
                Comentario comentario = new Comentario(texto, pelicula, null, usuario);
                Comentario comentarioGuardado = comentarioService.crearComentario(comentario);
                return ResponseEntity.ok(comentarioGuardado);
            } else {
                return ResponseEntity.badRequest().body("Película no encontrada.");
            }
        } else if ("serie".equalsIgnoreCase(tipo)) {
            Optional<Serie> serieOpt = serieService.obtenerSeriePorId(id);
            if (serieOpt.isPresent()) {
                Serie serie = serieOpt.get();
                Comentario comentario = new Comentario(texto, null, serie, usuario);
                Comentario comentarioGuardado = comentarioService.crearComentario(comentario);
                return ResponseEntity.ok(comentarioGuardado);
            } else {
                return ResponseEntity.badRequest().body("Serie no encontrada.");
            }
        } else {
            return ResponseEntity.badRequest().body("Tipo de contenido no válido. Use 'pelicula' o 'serie'.");
        }
    }

    // Solo permite eliminar comentarios asociados a un usuario.
    // Si el rol es admin permite eliminar cualquier comentario.
    @DeleteMapping("/cliente/comentarios/{id}")
    public ResponseEntity<?> eliminarComentario(@PathVariable Long id) {

        // Obtenemos usuario autenticado.
        ResponseEntity<?> usuarioResponse = obtenerUsuarioAutenticado();
        if (usuarioResponse.getStatusCode() != HttpStatus.OK) {
            return usuarioResponse;
        }
        Usuario usuario = (Usuario) usuarioResponse.getBody();

        System.out.println("Rol autenticado: " + usuario.getRol().name());
        System.out.println("Usuario autenticado: " + usuario.getEmail());
        boolean esAdmin = usuario.getRol().name().equalsIgnoreCase("ADMIN");

        // Buscar comentario
        Optional<Comentario> comentarioOpt = comentarioService.obtenerComentarioPorId(id);
        if (comentarioOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comentario no encontrado.");
        }

        Comentario comentario = comentarioOpt.get();

        // Verificamos si el usuario puede eliminar el comentario.
        if (esAdmin || comentario.getUsuario().getIdUsuario().equals(usuario.getIdUsuario())) {
            comentarioService.eliminarComentario(id);
            return ResponseEntity.ok().body("Comentario eliminado con éxito.");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permiso para eliminar este comentario.");
        }
    }

    @GetMapping("/cliente/comentarios/mostrar")
    @JsonView(Views.Detalle.class)
    public ResponseEntity<?> getComentarios(@RequestParam Long id, @RequestParam String tipo) {
        List<Comentario> comentarios;

        if ("pelicula".equalsIgnoreCase(tipo)) {
            Optional<Pelicula> peliculaOpt = peliculaService.obtenerPeliculaPorId(id);
            if (peliculaOpt.isPresent()) {
                comentarios = comentarioService.obtenerComentariosPorPelicula(peliculaOpt.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No hay comentarios");
            }
        } else if ("serie".equalsIgnoreCase(tipo)) {
            Optional<Serie> serieOpt = serieService.obtenerSeriePorId(id);
            if (serieOpt.isPresent()) {
                comentarios = comentarioService.obtenerComentariosPorSerie(serieOpt.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No hay comentarios");
            }
        } else {
            return ResponseEntity.badRequest().body("Tipo no válido.");
        }

        if (comentarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(comentarios);
    }
}