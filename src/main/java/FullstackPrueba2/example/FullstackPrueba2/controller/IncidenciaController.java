package FullstackPrueba2.example.FullstackPrueba2.controller;

import FullstackPrueba2.example.FullstackPrueba2.model.Incidencia;
import FullstackPrueba2.example.FullstackPrueba2.service.IncidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/incidencias")
public class IncidenciaController {

    @Autowired
    private IncidenciaService incidenciaService;

    @GetMapping
    public String getIncidencias() {
        return incidenciaService.listarIncidencias();
    }

    @PostMapping
    public String postIncidencia(@RequestBody Incidencia incidencia) {
        return incidenciaService.agregarIncidencia(incidencia);
    }

    @GetMapping("/{id}")
    public String getIncidenciaById(@PathVariable int id) {
        return incidenciaService.obtenerIncidenciaPorID(id);
    }

    @PutMapping("/{id}")
    public String updateIncidencia(@PathVariable int id, @RequestBody Incidencia incidencia) {
        return incidenciaService.actualizarIncidencia(id, incidencia);
    }

    @DeleteMapping("/{id}")
    public String deleteIncidencia(@PathVariable int id) {
        return incidenciaService.eliminarIncidencia(id);
    }
}