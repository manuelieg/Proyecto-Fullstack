package FullstackPrueba2.example.FullstackPrueba2.controller;

import FullstackPrueba2.example.FullstackPrueba2.assembler.EvaluacionModelAssembler;
import FullstackPrueba2.example.FullstackPrueba2.model.Evaluacion;
import FullstackPrueba2.example.FullstackPrueba2.service.EvaluacionService;
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
@RequestMapping("/evaluaciones")
@Tag(name = "Controlador de Evaluaciones", description = "Servicio de gestión de evaluaciones")
public class EvaluacionController {

    @Autowired
    private EvaluacionService evaluacionService;

    @Autowired
    EvaluacionModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Obtener Evaluaciones", description = "Obtiene la lista de evaluaciones del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna lista de evaluaciones"),
            @ApiResponse(responseCode = "404", description = "No hay datos disponibles")
    })
    public ResponseEntity<CollectionModel<EntityModel<Evaluacion>>> getAllEvaluaciones() {
        List<Evaluacion> lista = evaluacionService.listarEvaluaciones();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(assembler.toCollectionModel(lista), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Evaluación por ID", description = "Obtiene evaluación según su ID en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna evaluación"),
            @ApiResponse(responseCode = "404", description = "No hay datos disponibles")
    })
    @Parameter(description = "El ID de la evaluación", example = "123")
    public ResponseEntity<EntityModel<Evaluacion>> getEvaluacionById(@PathVariable int id) {
        if (evaluacionService.obtenerEvaluacionPorId(id).isPresent()) {
            Evaluacion evaluacion = evaluacionService.obtenerEvaluacionPorId(id).get();
            return new ResponseEntity<>(assembler.toModel(evaluacion), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Agregar Evaluación", description = "Permite registrar una evaluación en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Evaluación creada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Evaluacion.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    public ResponseEntity<EntityModel<Evaluacion>> postEvaluacion(@RequestBody Evaluacion evaluacion) {
        evaluacionService.agregarEvaluacion(evaluacion);
        if (evaluacionService.obtenerEvaluacionPorId(evaluacion.getId()).isPresent()) {
            return new ResponseEntity<>(assembler.toModel(evaluacion), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Evaluación", description = "Permite actualizar los datos de la evaluación según su ID en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evaluación modificada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Evaluacion.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    @Parameter(description = "El ID de la evaluación", example = "123")
    public ResponseEntity<Evaluacion> updateEvaluacion(@PathVariable int id, @RequestBody Evaluacion evaluacion) {
        if (evaluacionService.obtenerEvaluacionPorId(id).isPresent()) {
            evaluacionService.actualizarEvaluacion(id, evaluacion);
            return new ResponseEntity<>(evaluacion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Evaluación por ID", description = "Elimina una evaluación según su ID en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evaluación eliminada"),
            @ApiResponse(responseCode = "404", description = "No hay datos disponibles")
    })
    @Parameter(description = "El ID de la evaluación", example = "123")
    public ResponseEntity<Void> deleteEvaluacion(@PathVariable int id) {
        if (evaluacionService.obtenerEvaluacionPorId(id).isPresent()) {
            evaluacionService.eliminarEvaluacion(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}