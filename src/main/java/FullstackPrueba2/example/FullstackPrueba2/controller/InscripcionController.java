package FullstackPrueba2.example.FullstackPrueba2.controller;

import FullstackPrueba2.example.FullstackPrueba2.model.Inscripcion;
import FullstackPrueba2.example.FullstackPrueba2.service.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inscripciones")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    @GetMapping
    public String getInscripciones() {
        return inscripcionService.listarInscripciones();
    }

    @PostMapping
    public String postInscripcion(@RequestBody Inscripcion inscripcion) {
        return inscripcionService.agregarInscripcion(inscripcion);
    }

    @GetMapping("/{id}")
    public String getInscripcionById(@PathVariable int id) {
        return inscripcionService.obtenerInscripcionPorID(id);
    }

    @DeleteMapping("/{id}")
    public String deleteInscripcion(@PathVariable int id) {
        return inscripcionService.eliminarInscripcion(id);
    }

    @PutMapping("/{id}")
    public String updateInscripcion(@PathVariable int id, @RequestBody Inscripcion inscripcion) {
        return inscripcionService.actualizarInscripcion(id, inscripcion);
    }
}