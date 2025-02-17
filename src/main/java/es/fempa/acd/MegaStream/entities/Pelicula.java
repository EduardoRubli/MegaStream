package es.fempa.acd.MegaStream.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import es.fempa.acd.MegaStream.entities.views.Views;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "pelicula")
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Resumen.class)
    @Column(name = "idPelicula")
    private Long idPelicula;

    @JsonView(Views.Resumen.class)
    @Column(name = "nombre")
    private String nombre;
    @JsonView(Views.Resumen.class)
    @Column(name = "genero")
    private String genero;
    @JsonView(Views.Resumen.class)
    @Column(name = "duracion")
    private Integer duracion;
    @JsonView(Views.Resumen.class)
    @Column(name = "premium")
    private Boolean premium;
    @JsonView(Views.Resumen.class)
    @Column(name = "puntuacion")
    private Double puntuacion;
    @OneToMany(mappedBy = "pelicula", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Puntuacion> puntuaciones;

    @ManyToOne
    @JsonView(Views.Resumen.class)
    @JoinColumn(name = "idDirector")
    private Director director;

    @JsonView(Views.Resumen.class)
    @Column(name = "imgURL")
    private String imgURL;

    @ManyToMany
    @JoinTable(
            name = "pelicula_actor",
            joinColumns = @JoinColumn(name = "idPelicula"),
            inverseJoinColumns = @JoinColumn(name = "idActor")
    )
    @JsonView(Views.Resumen.class)
    private List<Actor> actores;

    @OneToMany(mappedBy = "pelicula", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonView(Views.Detalle.class)
    private List<Comentario> comentarios;

    // Constructor por defecto.
    public Pelicula() {
        this.comentarios = new ArrayList<>();
        this.puntuaciones = new ArrayList<>();
    }

    public Pelicula(String nombre, Integer duracion, Boolean premium, Double puntuacion, Director director, List<Actor> actores) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.premium = premium;
        this.puntuacion = puntuacion;
        this.director = director;
        this.actores = actores;
        this.comentarios = new ArrayList<>();
        this.puntuaciones = new ArrayList<>();
    }

    // Getters y setters
    public Long getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Long idPelicula) {
        this.idPelicula = idPelicula;
    }

    // Agrega una puntuación a la lista puntuaciones.
    public void agregarPuntuacion(Puntuacion nuevaPuntuacion) {
        boolean puntuacionExistente = false;
        for (Puntuacion p : this.puntuaciones) {
            if (p.getUsuario().getIdUsuario().equals(nuevaPuntuacion.getUsuario().getIdUsuario())) {
                p.setPuntuacion(nuevaPuntuacion.getPuntuacion());
                puntuacionExistente = true;
                break;
            }
        }

        if (!puntuacionExistente) {
            this.puntuaciones.add(nuevaPuntuacion);
        }

        recalcularPuntuacionMedia();
    }

    // Recalcula la media de las puntuaciones.
    private void recalcularPuntuacionMedia() {
        if (this.puntuaciones.isEmpty()) {
            return; // Mantiene la puntuación actual predeterminada si no hay puntuaciones
        }

        double suma = 0.0;
        for (Puntuacion p : this.puntuaciones) {
            suma += p.getPuntuacion();
        }

        this.puntuacion = suma / this.puntuaciones.size();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Boolean getPremium() {
        return premium;
    }

    public void setPremium(Boolean premium) {
        this.premium = premium;
    }

    public Double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public List<Actor> getActores() {
        return actores;
    }

    public void setActores(List<Actor> actores) {
        this.actores = actores;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public String toString() {
        return "Pelicula{idPelicula=" + idPelicula + ", nombre='" + nombre + "', duracion=" + duracion + ", premium=" + premium + ", puntuacion=" + puntuacion + ", director=" + director.getNombre() +
                "\nComentarios:" + this.getComentarios() + "}";
    }
}