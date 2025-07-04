package FullstackPrueba2.example.FullstackPrueba2.controller;

import FullstackPrueba2.example.FullstackPrueba2.assembler.InscripcionModelAssembler;
import FullstackPrueba2.example.FullstackPrueba2.model.Inscripcion;
import FullstackPrueba2.example.FullstackPrueba2.service.InscripcionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@RequestMapping("/inscripciones")
@Tag(name = "Controlador de Inscripciones", description = "Servicio de gestion de inscripciones")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    @Autowired
    private InscripcionModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Obtener Inscripciones", description = "Obtiene la lista de inscripciones del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna lista de inscripciones"),
            @ApiResponse(responseCode = "404", description = "No hay datos disponibles")
    })
    public ResponseEntity<CollectionModel<EntityModel<Inscripcion>>> getAllInscripciones() {
        List<Inscripcion> lista = inscripcionService.listarInscripciones();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(assembler.toCollectionModel(lista), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Inscripcion por ID",description = "Obtiene inscripcion segun su ID en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna inscripcion"),
            @ApiResponse(responseCode = "404", description = "No hay datos disponibles")
    })
    @Parameter(description = "El ID de la inscripcion", example = "123")
    public ResponseEntity<EntityModel<Inscripcion>> getInscripcionById (@PathVariable int id) {
        if (inscripcionService.obtenerInscripcionPorId(id).isPresent()) {
            Inscripcion inscripcion = inscripcionService.obtenerInscripcionPorId(id).get();
            return new ResponseEntity<>(assembler.toModel(inscripcion), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Agregar Inscripcion", description = "Permite registrar una inscripcion en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Inscripcion creada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inscripcion.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    public ResponseEntity<EntityModel<Inscripcion>> postInscripcion(@RequestBody Inscripcion inscripcion) {
        inscripcionService.agregarInscripcion(inscripcion);
        if (inscripcionService.obtenerInscripcionPorId(inscripcion.getId()).isPresent()) {
            return new ResponseEntity<>(assembler.toModel(inscripcion), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Inscripcion", description = "Permite actualizar los datos de inscripcion segun su ID en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inscripcion modificada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inscripcion.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    @Parameter(description = "El ID de la inscripcion", example = "123")
    public ResponseEntity<Inscripcion> updateInscripcion(@PathVariable int id, @RequestBody Inscripcion inscripcion) {
        if (inscripcionService.obtenerInscripcionPorId(id).isPresent()) {
            inscripcionService.actualizarInscripcion(id, inscripcion);
            return new ResponseEntity<>(inscripcion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary="Eliminar Inscripcion por ID", description = "Elimina una inscripcion segun su ID en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna Inscripcion"),
            @ApiResponse(responseCode = "404", description = "No hay datos disponibles")
    })
    @Parameter(description = "El ID de la inscripcion", example = "123")
    public ResponseEntity<Void> deleteInscripcion(@PathVariable int id) {
        if (inscripcionService.obtenerInscripcionPorId(id).isPresent()) {
            inscripcionService.eliminarInscripcion(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}