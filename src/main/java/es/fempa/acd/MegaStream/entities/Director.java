package es.fempa.acd.MegaStream.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import es.fempa.acd.MegaStream.entities.views.Views;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "director")
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDirector")
    private Long idDirector;

    @JsonView(Views.Resumen.class)
    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "director", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Pelicula> peliculas;

    @OneToMany(mappedBy = "director", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Serie> series;

    // Constructor por defecto.
    public Director() {}

    // Constructor sobrecargado.
    public Director(String nombre) {
        this.nombre = nombre;
    }

    // Getters y setters
    public Long getIdDirector() {
        return idDirector;
    }

    public void setIdDirector(Long idDirector) {
        this.idDirector = idDirector;
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

    public List<Serie> getSeries() {
        return series;
    }

    public void setSeries(List<Serie> series) {
        this.series = series;
    }

    @Override
    public String toString() {
        return "Director{idDirector=" + idDirector + ", nombre='" + nombre + "'}";
    }
}