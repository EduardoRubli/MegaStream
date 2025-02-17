package es.fempa.acd.MegaStream.entities;

import com.fasterxml.jackson.annotation.JsonView;
import es.fempa.acd.MegaStream.entities.views.Views;
import jakarta.persistence.*;

@Entity
@Table(name = "capitulo")
public class Capitulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Detalle.class)
    @Column(name = "idCapitulo")
    private Long idCapitulo;
    @JsonView(Views.Detalle.class)
    @Column(name = "nombre")
    private String nombre;
    @JsonView(Views.Detalle.class)
    @Column(name = "duracion")
    private Integer duracion;

    @ManyToOne
    @JoinColumn(name = "idSerie", nullable = false)
    private Serie serie;

    // Constructor por defecto.
    public Capitulo() {}

    // Constructor sobrecargado.
    public Capitulo(String nombre, Integer duracion, Serie serie) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.serie = serie;
    }

    // Getters y setters
    public Long getIdCapitulo() {
        return idCapitulo;
    }

    public void setIdCapitulo(Long idCapitulo) {
        this.idCapitulo = idCapitulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    @Override
    public String toString() {
        return "Capitulo{idCapitulo=" + idCapitulo + ", nombre='" + nombre + "', duracion=" + duracion + ", serie=" + (serie != null ? serie.getNombre() : "N/A") + "}";
    }
}