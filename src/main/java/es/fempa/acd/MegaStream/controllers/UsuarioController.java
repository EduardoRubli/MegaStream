package es.fempa.acd.MegaStream.controllers;

import ch.qos.logback.classic.encoder.JsonEncoder;
import es.fempa.acd.MegaStream.entities.Usuario;
import es.fempa.acd.MegaStream.entities.Rol;
import es.fempa.acd.MegaStream.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    //Endpoint para crear un nuevo usuario.
    @PostMapping("/public/usuarios/crear")
    public ResponseEntity<?> crearUsuario(@RequestParam String nombre,
                                          @RequestParam String email,
                                          @RequestParam String password,
                                          @RequestParam Boolean isPremium,
                                          @RequestParam Rol rol) {
        // Verificamos si el usuario ya existe.
        if (usuarioService.existeUsuarioPorEmail(email)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Ya existe un usuario con el email: " + email);
        }

        // Ciframos la contraseña antes de guardarla.
        String passwordCifrada = passwordEncoder.encode(password);

        Usuario usuario = new Usuario(nombre, email, passwordCifrada, isPremium, rol);
        Usuario usuarioCreado = usuarioService.crearUsuario(usuario);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Usuario con email: " + usuarioCreado.getEmail() + " registrado.");
    }

    // Endpoint para obtener todos los usuarios.
    @GetMapping("/admin/usuarios/mostrar")
    public @ResponseBody List<Usuario> getAllUsuarios() {
        return usuarioService.obtenerTodosLosUsuarios();
    }

    // Endpoint para buscar usuarios por nombre.
    @GetMapping("/admin/usuarios/buscarNombre")
    public ResponseEntity<List<Usuario>> buscarUsuariosPorNombreParcial(@RequestParam String nombre) {
        List<Usuario> usuarios = usuarioService.obtenerUsuariosPorNombreParcial(nombre);
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    // Endpoint para buscar usuario por email.
    @GetMapping("/admin/usuarios/buscarEmail")
    public ResponseEntity<?> buscarUsuarioPorEmail(@RequestParam String email) {
        Optional<Usuario> usuario = usuarioService.obtenerUsuarioPorEmail(email);

        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario con email "
                + email + " no encontrado.");
    }

    // Endpoint para buscar usuario por ID.
    @GetMapping("/admin/usuarios/{id}")
    public ResponseEntity<?> buscarUsuarioPorId(@PathVariable Long id) {
        // Verifica si el usuario existe
        if (usuarioService.existeUsuarioPorId(id)) {
            // Recupera el usuario y lo devuelve
            Usuario usuario = usuarioService.obtenerUsuarioPorId(id).get();
            return ResponseEntity.ok(usuario);
        }
        // Devuelve un error 404 si no existe.
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario con id " + id  + " no encontrado.");
    }
}