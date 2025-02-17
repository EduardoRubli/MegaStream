package es.fempa.acd.MegaStream.entities.views;

import com.fasterxml.jackson.annotation.JsonView;

public class Views {
    public static class Resumen {}
    public static class Detalle extends Resumen {}
}