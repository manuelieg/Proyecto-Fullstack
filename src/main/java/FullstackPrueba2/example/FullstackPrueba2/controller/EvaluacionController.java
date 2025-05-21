package FullstackPrueba2.example.FullstackPrueba2.controller;

import FullstackPrueba2.example.FullstackPrueba2.model.Evaluacion;
import FullstackPrueba2.example.FullstackPrueba2.service.EvaluacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/evaluaciones")
public class EvaluacionController {

    @Autowired
    private EvaluacionService evaluacionService;

    @GetMapping
    public String getEvaluaciones() {
        return evaluacionService.listarEvaluaciones();
    }

    @PostMapping
    public String postEvaluacion(@RequestBody Evaluacion evaluacion) {
        return evaluacionService.agregarEvaluacion(evaluacion);
    }

    @GetMapping("/{id}")
    public String getEvaluacionById(@PathVariable int id) {
        return evaluacionService.obtenerEvaluacionPorID(id);
    }

    @DeleteMapping("/{id}")
    public String deleteEvaluacion(@PathVariable int id) {
        return evaluacionService.eliminarEvaluacion(id);
    }

    @PutMapping("/{id}")
    public String updateEvaluacion(@PathVariable int id, @RequestBody Evaluacion evaluacion) {
        return evaluacionService.actualizarEvaluacion(id, evaluacion);
    }
}