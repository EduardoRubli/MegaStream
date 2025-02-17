package es.fempa.acd.MegaStream.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import es.fempa.acd.MegaStream.entities.views.Views;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Resumen.class)
    @Column(name = "idUsuario")
    private Long idUsuario;
    @JsonView(Views.Resumen.class)
    @Column(name = "nombre")
    private String nombre;
    @JsonView(Views.Resumen.class)
    @Column(name = "email")
    private String email;
    @Enumerated(EnumType.STRING)
    @JsonView(Views.Resumen.class)
    @Column(name = "rol")
    private Rol rol;
    @Column(name = "password")
    private String password;
    @Column(name = "premium")
    private Boolean premium;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Puntuacion> puntuaciones;

    // Constructor por defecto.
    public Usuario() {
        this.comentarios = new ArrayList<>();
        this.puntuaciones = new ArrayList<>();
    }

    public Usuario(String nombre, String email, String password, Boolean premium, Rol rol) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.premium = premium;
        this.rol = rol;
        this.comentarios = new ArrayList<>();
        this.puntuaciones = new ArrayList<>();
    }

    // Getters y setters
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getPremium() {
        return premium;
    }

    public void setPremium(Boolean premium) {
        this.premium = premium;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public String toString() {
        return "Usuario{idUsuario=" + idUsuario + ", nombre='" + nombre + "', email='" + email + "', premium=" + premium + "}";
    }
}
