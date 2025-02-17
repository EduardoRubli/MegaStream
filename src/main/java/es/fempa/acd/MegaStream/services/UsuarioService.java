package es.fempa.acd.MegaStream.services;

import es.fempa.acd.MegaStream.entities.Usuario;
import es.fempa.acd.MegaStream.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Crear un nuevo usuario.
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Obtener todos los usuarios.
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    // Verificar si existe usuario por email (duplicado).
    public boolean existeUsuarioPorEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    // Verificar si existe un usuario concreto.
    public boolean existeUsuarioPorId(Long id) {
        return usuarioRepository.existsById(id);
    }

    // Obtener un usuario por ID.
    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    // Obtener un usuario por email.
    public Optional<Usuario> obtenerUsuarioPorEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return Optional.empty();
        }
        return usuarioRepository.findByEmailIgnoreCase(email);
    }

    public List<Usuario> obtenerUsuariosPorNombreParcial(String nombre) {
        return usuarioRepository.findByNombreContainingIgnoreCase(nombre);
    }

    // Actualizar un usuario.
    public Usuario actualizarUsuario(Long id, Usuario usuario) {
        if (usuarioRepository.existsById(id)) {
            usuario.setIdUsuario(id);
            return usuarioRepository.save(usuario);
        }
        return null;  // O lanzar una excepción si prefieres
    }

    // Eliminar un usuario.
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}