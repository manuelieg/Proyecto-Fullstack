package FullstackPrueba2.example.FullstackPrueba2.controller;

import FullstackPrueba2.example.FullstackPrueba2.model.Usuario;
import FullstackPrueba2.example.FullstackPrueba2.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@Tag(name="Controlador Usuarios",description = "Servicio de gestion de usuarios Fullstack I")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @Operation(summary = "Obtener Usuarios",description = "Obtiene la lista de usuarios existentes en el sistema")
    @ApiResponse(responseCode="200",description = "Consulta Exitosa")
    public String getUsuario() {
        return usuarioService.listarUsuarios();
    }

    @PostMapping
    public String postUsuario(@RequestBody Usuario usuario) {
        return usuarioService.agregarUsuario(usuario);
    }

    @GetMapping("/{id}")
    public String getUsuarioById(@PathVariable int id) {
        return usuarioService.obtenerUsuarioPorID(id);
    }

    @PutMapping("/{id}")
    public String updateUsuario(@PathVariable int id, @RequestBody Usuario usuario) {
    return usuarioService.actualizarUsuario(id, usuario);
    }

    @DeleteMapping("/{id}")
    public String deleteUsuario(@PathVariable int id) {
        return usuarioService.eliminarUsuario(id);
    }
}
