package FullstackPrueba2.example.FullstackPrueba2.controller;

import FullstackPrueba2.example.FullstackPrueba2.assembler.ReseñaAssembler;
import FullstackPrueba2.example.FullstackPrueba2.model.Reseña;
import FullstackPrueba2.example.FullstackPrueba2.service.ReseñaService;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/resenas")
@Tag(name = "Controlador de Reseñas", description = "Servicio de gestión de reseñas")
public class ReseñaController {

    @Autowired
    private ReseñaService reseñaService;

    @Autowired
    private ReseñaAssembler assembler;

    @GetMapping
    @Operation(summary = "Obtener Reseñas", description = "Obtiene la lista de reseñas del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna lista de reseñas"),
            @ApiResponse(responseCode = "404", description = "No hay datos disponibles")
    })
    public ResponseEntity<CollectionModel<EntityModel<Reseña>>> getAllReseñas() {
        List<Reseña> lista = reseñaService.listarReseñas();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(assembler.toCollectionModel(lista), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Reseña por ID", description = "Obtiene reseña según su ID en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna Reseña"),
            @ApiResponse(responseCode = "404", description = "No hay reseña disponible con ese ID")
    })
    @Parameter(description = "El ID de la reseña", example = "123")
    public ResponseEntity<EntityModel<Reseña>> getReseñaById(@PathVariable int id) {
        Optional<Reseña> reseña = reseñaService.obtenerReseñaPorID(id);
        if (reseña.isPresent()) {
            return new ResponseEntity<>(assembler.toModel(reseña.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Agregar Reseña", description = "Permite registrar una reseña en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reseña creada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Reseña.class))),
            @ApiResponse(responseCode = "204", description = "No hay datos en la solicitud")
    })
    public ResponseEntity<EntityModel<Reseña>> postReseña(@RequestBody Reseña reseña) {
        Reseña creada = reseñaService.agregarReseña(reseña);
        if (creada != null && creada.getId() > 0) {
            return new ResponseEntity<>(assembler.toModel(creada), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Reseña", description = "Permite actualizar los datos de una reseña según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reseña modificada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Reseña.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    @Parameter(description = "El ID de la reseña", example = "123")
    public ResponseEntity<Reseña> updateReseña(@PathVariable int id, @RequestBody Reseña reseña) {
        if (reseñaService.obtenerReseñaPorID(id).isPresent()) {
            reseñaService.actualizarReseña(id, reseña);
            return new ResponseEntity<>(reseña, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Reseña por ID", description = "Elimina una reseña según su ID en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reseña eliminada"),
            @ApiResponse(responseCode = "404", description = "No hay datos disponibles")
    })
    @Parameter(description = "El ID de la reseña", example = "123")
    public ResponseEntity<Void> deleteReseña(@PathVariable int id) {
        if (reseñaService.obtenerReseñaPorID(id).isPresent()) {
            reseñaService.eliminarReseña(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}