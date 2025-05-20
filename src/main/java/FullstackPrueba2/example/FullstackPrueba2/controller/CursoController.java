package FullstackPrueba2.example.FullstackPrueba2.controller;

import FullstackPrueba2.example.FullstackPrueba2.model.Curso;
import FullstackPrueba2.example.FullstackPrueba2.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public String getCurso() {
        return cursoService.listarCursos();
    }

    @PostMapping
    public String postCurso(@RequestBody Curso curso) {
        return cursoService.agregarCurso(curso);
    }

    @GetMapping("/{id}")
    public String getCursoById(@PathVariable int id) {
        return cursoService.obtenerCursoPorId(id);
    }

    @DeleteMapping("/{id}")
    public String deleteCurso(@PathVariable int id) {
        return cursoService.eliminarCurso(id);
    }

    @PutMapping("/{id}")
    public String updateCurso(@PathVariable int id, @RequestBody Curso curso) {
        return cursoService.actualizarCurso(id, curso);
    }
}