package es.fempa.acd.MegaStream.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "puntuacion")
public class Puntuacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPuntuacion")
    private Long idPuntuacion;

    @ManyToOne
    @JoinColumn(name = "idPelicula")
    private Pelicula pelicula;

    @ManyToOne
    @JoinColumn(name = "idSerie")
    private Serie serie;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    private Double puntuacion;

    // Constructor por defecto.
    public Puntuacion() {}

    // Constructor de película.
    public Puntuacion(Pelicula pelicula, Usuario usuario, Double puntuacion) {
        this.pelicula = pelicula;
        this.usuario = usuario;
        this.puntuacion = puntuacion;
    }

    // Constructor de serie.
    public Puntuacion(Serie serie, Usuario usuario, Double puntuacion) {
        this.pelicula = pelicula;
        this.usuario = usuario;
        this.puntuacion = puntuacion;
    }

    // Getters y setters
    public Long getId() {
        return idPuntuacion;
    }

    public void setId(Long id) {
        this.idPuntuacion = idPuntuacion;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Double puntuacion) {
        this.puntuacion = puntuacion;
    }
}