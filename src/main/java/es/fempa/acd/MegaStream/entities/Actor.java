package es.fempa.acd.MegaStream.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import es.fempa.acd.MegaStream.entities.views.Views;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "actor")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idActor")
    private Long idActor;

    @JsonView(Views.Resumen.class)
    @Column(name = "nombre")
    private String nombre;

    @ManyToMany(mappedBy = "actores")
    @JsonIgnore
    private List<Pelicula> peliculas;

    @ManyToMany(mappedBy = "actores")
    @JsonIgnore
    private List<Serie> series;

    // Constructor por defecto.
    public Actor() {}

    // Constructor sobrecargado.
    public Actor(String nombre) {
        this.nombre = nombre;
    }

    // Getters y setters
    public Long getIdActor() {
        return idActor;
    }

    public void setIdActor(Long idActor) {
        this.idActor = idActor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(List<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    @Override
    public String toString() {
        return "Actor{idActor=" + idActor + ", nombre='" + nombre + "'}";
    }
}