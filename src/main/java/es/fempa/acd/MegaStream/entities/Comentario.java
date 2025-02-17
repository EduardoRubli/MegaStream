package es.fempa.acd.MegaStream.entities;

import com.fasterxml.jackson.annotation.JsonView;
import es.fempa.acd.MegaStream.entities.views.Views;
import jakarta.persistence.*;

@Entity
@Table(name = "comentario")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Resumen.class)
    @Column(name = "idComentario")
    private Long idComentario;

    @JsonView(Views.Detalle.class)
    @Column(name = "comentario")
    private String comentario;

    @ManyToOne
    @JoinColumn(name = "idPelicula")
    private Pelicula pelicula;

    @ManyToOne
    @JoinColumn(name = "idSerie")
    private Serie serie;

    @ManyToOne
    @JsonView(Views.Detalle.class)
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    // Constructor por defecto.
    public Comentario() {}

    // Constructor sobrecargado.
    public Comentario(String comentario, Pelicula pelicula, Serie serie, Usuario usuario) {
        this.comentario = comentario;
        this.pelicula = pelicula;
        this.serie = serie;
        this.usuario = usuario;
    }

    // Getters y setters
    public Long getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(Long idComentario) {
        this.idComentario = idComentario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Comentario{idComentario=" + idComentario + ", comentario='" + comentario + "', pelicula=" + (pelicula != null ? pelicula.getNombre() : "N/A") + ", serie=" + (serie != null ? serie.getNombre() : "N/A") + ", usuario=" + usuario.getNombre() + "}";
    }
}