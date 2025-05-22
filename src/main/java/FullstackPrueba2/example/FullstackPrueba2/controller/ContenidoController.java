package FullstackPrueba2.example.FullstackPrueba2.controller;

import FullstackPrueba2.example.FullstackPrueba2.model.Contenido;
import FullstackPrueba2.example.FullstackPrueba2.service.ContenidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contenidos")
public class ContenidoController {

    @Autowired
    private ContenidoService contenidoService;

    @GetMapping
    public String getContenidos() {
        return contenidoService.listarContenidos();
    }

    @PostMapping
    public String postContenido(@RequestBody Contenido contenido) {
        return contenidoService.agregarContenido(contenido);
    }

    @GetMapping("/{id}")
    public String getContenidoById(@PathVariable int id) {
        return contenidoService.obtenerContenidoPorID(id);
    }

    @PutMapping("/{id}")
    public String updateContenido(@PathVariable int id, @RequestBody Contenido contenido) {
        return contenidoService.actualizarContenido(id, contenido);
    }

    @DeleteMapping("/{id}")
    public String deleteContenido(@PathVariable int id) {
        return contenidoService.eliminarContenido(id);
    }
}