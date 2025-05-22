package FullstackPrueba2.example.FullstackPrueba2.controller;

import FullstackPrueba2.example.FullstackPrueba2.model.Reseña;
import FullstackPrueba2.example.FullstackPrueba2.service.ReseñaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resenas")
public class ReseñaController {

    @Autowired
    private ReseñaService reseñaService;

    @GetMapping
    public String getResenas() {
        return reseñaService.listarReseñas();
    }

    @PostMapping
    public String postResena(@RequestBody Reseña reseña) {
        return reseñaService.agregarReseña(reseña);
    }

    @GetMapping("/{id}")
    public String getResenaById(@PathVariable int id) {
        return reseñaService.obtenerReseñaPorID(id);
    }

    @PutMapping("/{id}")
    public String updateResena(@PathVariable int id, @RequestBody Reseña reseña) {
        return reseñaService.actualizarReseña(id, reseña);
    }

    @DeleteMapping("/{id}")
    public String deleteResena(@PathVariable int id) {
        return reseñaService.eliminarReseña(id);
    }
}