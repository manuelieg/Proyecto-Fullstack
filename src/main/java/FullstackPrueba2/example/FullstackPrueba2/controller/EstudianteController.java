package FullstackPrueba2.example.FullstackPrueba2.controller;

import FullstackPrueba2.example.FullstackPrueba2.model.Estudiante;
import FullstackPrueba2.example.FullstackPrueba2.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @GetMapping
    public String getEstudiantes() {
        return estudianteService.listarEstudiantes();
    }

    @PostMapping
    public String postEstudiante(@RequestBody Estudiante estudiante) {
        return estudianteService.agregarEstudiante(estudiante);
    }

    @GetMapping("/{id}")
    public String getEstudianteById(@PathVariable int id) {
        return estudianteService.obtenerEstudiantePorID(id);
    }

    @DeleteMapping("/{id}")
    public String deleteEstudiante(@PathVariable int id) {
        return estudianteService.eliminarEstudiante(id);
    }

    @PutMapping("/{id}")
    public String updateEstudiante(@PathVariable int id, @RequestBody Estudiante estudiante) {
        return estudianteService.actualizarEstudiante(id, estudiante);
    }
}