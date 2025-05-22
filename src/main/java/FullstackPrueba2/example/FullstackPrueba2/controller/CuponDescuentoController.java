package FullstackPrueba2.example.FullstackPrueba2.controller;

import FullstackPrueba2.example.FullstackPrueba2.model.CuponDescuento;
import FullstackPrueba2.example.FullstackPrueba2.service.CuponDescuentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cupones")
public class CuponDescuentoController {

    @Autowired
    private CuponDescuentoService cuponDescuentoService;

    @GetMapping
    public String getCupones() {
        return cuponDescuentoService.listarCupones();
    }

    @PostMapping
    public String postCupon(@RequestBody CuponDescuento cupon) {
        return cuponDescuentoService.agregarCupon(cupon);
    }

    @GetMapping("/{id}")
    public String getCuponById(@PathVariable int id) {
        return cuponDescuentoService.obtenerCuponPorID(id);
    }

    @PutMapping("/{id}")
    public String updateCupon(@PathVariable int id, @RequestBody CuponDescuento cupon) {
        return cuponDescuentoService.actualizarCupon(id, cupon);
    }

    @DeleteMapping("/{id}")
    public String deleteCupon(@PathVariable int id) {
        return cuponDescuentoService.eliminarCupon(id);
    }
}