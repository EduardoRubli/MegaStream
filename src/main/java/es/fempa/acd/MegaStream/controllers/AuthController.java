package es.fempa.acd.MegaStream.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    // Apunta a una plantilla Thymeleaf llamada login.html
    public String login() {
        return "login";
    }

    @GetMapping("/series")
    // Renderiza series.html desde templates.
    public String series() {
        return "series";
    }

    @GetMapping("/serie")
    // Renderiza serie.html desde templates.
    public String serie() {
        return "serie";
    }

    @GetMapping("/index")
    // Renderiza index.html desde templates.
    public String index() {
        return "index";
    }

    @GetMapping("/pelicula")
    // Renderiza pelicula.html desde templates.
    public String pelicula() {
        return "pelicula";
    }

    @GetMapping("/")
    // Renderiza login.html desde templates.
    public String home() {
        return "login";
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}
