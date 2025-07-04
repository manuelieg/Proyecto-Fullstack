package FullstackPrueba2.example.FullstackPrueba2.controller;

import FullstackPrueba2.example.FullstackPrueba2.assembler.IncidenciaModelAssembler;
import FullstackPrueba2.example.FullstackPrueba2.model.Incidencia;
import FullstackPrueba2.example.FullstackPrueba2.service.IncidenciaService;
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

@RestController
@RequestMapping("/incidencias")
@Tag(name = "Controlador de Incidencias", description = "Servicio de gestión de incidencias")
public class IncidenciaController {

    @Autowired
    private IncidenciaService incidenciaService;

    @Autowired
    IncidenciaModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Obtener Incidencias", description = "Obtiene la lista de incidencias del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna lista de incidencias"),
            @ApiResponse(responseCode = "404", description = "No hay datos disponibles")
    })
    public ResponseEntity<CollectionModel<EntityModel<Incidencia>>> getAllIncidencias() {
        List<Incidencia> lista = incidenciaService.listarIncidencias();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(assembler.toCollectionModel(lista), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Incidencia por ID", description = "Obtiene una incidencia según su ID en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna incidencia"),
            @ApiResponse(responseCode = "404", description = "No hay datos disponibles")
    })
    @Parameter(description = "El ID de la incidencia", example = "123")
    public ResponseEntity<EntityModel<Incidencia>> getIncidenciaById(@PathVariable int id) {
        if (incidenciaService.obtenerIncidenciaPorId(id).isPresent()) {
            Incidencia incidencia = incidenciaService.obtenerIncidenciaPorId(id).get();
            return new ResponseEntity<>(assembler.toModel(incidencia), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Agregar Incidencia", description = "Permite registrar una incidencia en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Incidencia creada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Incidencia.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    public ResponseEntity<EntityModel<Incidencia>> postIncidencia(@RequestBody Incidencia incidencia) {
        incidenciaService.agregarIncidencia(incidencia);
        if (incidenciaService.obtenerIncidenciaPorId(incidencia.getId()).isPresent()) {
            return new ResponseEntity<>(assembler.toModel(incidencia), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Incidencia", description = "Permite actualizar los datos de la incidencia según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Incidencia modificada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Incidencia.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    @Parameter(description = "El ID de la incidencia", example = "123")
    public ResponseEntity<Incidencia> updateIncidencia(@PathVariable int id, @RequestBody Incidencia incidencia) {
        if (incidenciaService.obtenerIncidenciaPorId(id).isPresent()) {
            incidenciaService.actualizarIncidencia(id, incidencia);
            return new ResponseEntity<>(incidencia, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Incidencia por ID", description = "Elimina una incidencia según su ID en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Incidencia eliminada"),
            @ApiResponse(responseCode = "404", description = "No hay datos disponibles")
    })
    @Parameter(description = "El ID de la incidencia", example = "123")
    public ResponseEntity<Void> deleteIncidencia(@PathVariable int id) {
        if (incidenciaService.obtenerIncidenciaPorId(id).isPresent()) {
            incidenciaService.eliminarIncidencia(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}