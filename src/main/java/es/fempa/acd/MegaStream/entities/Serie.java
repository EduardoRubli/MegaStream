package es.fempa.acd.MegaStream.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import es.fempa.acd.MegaStream.entities.views.Views;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "serie")
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Resumen.class)
    @Column(name = "idSerie")
    private Long idSerie;
    @JsonView(Views.Resumen.class)
    @Column(name = "nombre")
    private String nombre;
    @JsonView(Views.Resumen.class)
    @Column(name = "genero")
    private String genero;
    @JsonView(Views.Resumen.class)
    @Column(name = "premium")
    private Boolean premium;
    @JsonView(Views.Resumen.class)
    @Column(name = "puntuacion")
    private Double puntuacion;

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, orphanRemoval = true)
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
            name = "serie_actor",
            joinColumns = @JoinColumn(name = "idSerie"),
            inverseJoinColumns = @JoinColumn(name = "idActor")
    )
    @JsonView(Views.Resumen.class)
    private List<Actor> actores;

    @JsonView(Views.Detalle.class)
    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Capitulo> capitulos;

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Comentario> comentarios;

    // Constructor por defecto.
    public Serie() {
        this.comentarios = new ArrayList<>();
        this.puntuaciones = new ArrayList<>();
    }

    // Constructor sobrecargado.
    public Serie(String nombre, Boolean premium, Double puntuacion, Director director, List<Capitulo> capitulos) {
        this.nombre = nombre;
        this.premium = premium;
        this.puntuacion = puntuacion;
        this.director = director;
        this.capitulos = capitulos;
        this.comentarios = new ArrayList<>();
        this.puntuaciones = new ArrayList<>();
    }

    // Getters y setters
    public Long getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(Long idSerie) {
        this.idSerie = idSerie;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public List<Capitulo> getCapitulos() {
        return capitulos;
    }

    public void setCapitulos(List<Capitulo> capitulos) {
        this.capitulos = capitulos;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public String toString() {
        return "Serie{idSerie=" + idSerie + ", nombre='" + nombre + "', premium=" + premium + ", puntuacion=" + puntuacion + ", director=" + director.getNombre() + "}";
    }
}
