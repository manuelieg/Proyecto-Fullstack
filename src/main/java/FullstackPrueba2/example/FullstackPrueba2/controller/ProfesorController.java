package FullstackPrueba2.example.FullstackPrueba2.controller;

import FullstackPrueba2.example.FullstackPrueba2.model.Profesor;
import FullstackPrueba2.example.FullstackPrueba2.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profesores")
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    @GetMapping
    public String getProfesores() {
        return profesorService.listarProfesores();
    }

    @PostMapping
    public String postProfesor(@RequestBody Profesor profesor) {
        return profesorService.agregarProfesor(profesor);
    }

    @GetMapping("/{id}")
    public String getProfesorById(@PathVariable int id) {
        return profesorService.obtenerProfesorPorID(id);
    }

    @DeleteMapping("/{id}")
    public String deleteProfesor(@PathVariable int id) {
        return profesorService.eliminarProfesor(id);
    }

    @PutMapping("/{id}")
    public String updateProfesor(@PathVariable int id, @RequestBody Profesor profesor) {
        return profesorService.actualizarProfesor(id, profesor);
    }
}