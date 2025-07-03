package FullstackPrueba2.example.FullstackPrueba2.controller;

import FullstackPrueba2.example.FullstackPrueba2.assembler.CursoModelAssembler;
import FullstackPrueba2.example.FullstackPrueba2.model.Curso;
import FullstackPrueba2.example.FullstackPrueba2.service.CursoService;
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
@RequestMapping("/cursos")
@Tag(name = "Controlador de Cursos", description = "Servicio de gestion de cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @Autowired
    CursoModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Obtener Cursos", description = "Obtiene la lista de cursos del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna lista de cursos"),
            @ApiResponse(responseCode = "404", description = "No hay datos disponibles")
    })
    public ResponseEntity<CollectionModel<EntityModel<Curso>>> getAllCursos() {
        List<Curso> lista = cursoService.listarCursos();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(assembler.toCollectionModel(lista), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Curso por ID",description = "Obtiene curso segun su ID en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna curso"),
            @ApiResponse(responseCode = "404", description = "No hay datos disponibles")
    })
    @Parameter(description = "El ID del curso", example = "123")
    public ResponseEntity<EntityModel<Curso>> getCursoById (@PathVariable int id) {
        if (cursoService.obtenerCursoPorId(id).isPresent()) {
            Curso curso = cursoService.obtenerCursoPorId(id).get();
            return new ResponseEntity<>(assembler.toModel(curso), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Agregar Curso", description = "Permite registrar un curso en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Curso creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Curso.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    public ResponseEntity<EntityModel<Curso>> postCurso(@RequestBody Curso curso) {
        cursoService.agregarCurso(curso);
        if (cursoService.obtenerCursoPorId(curso.getId()).isPresent()) {
            return new ResponseEntity<>(assembler.toModel(curso), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Curso", description = "Permite actualizar los datos del curso segun su ID en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso modificado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Curso.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    @Parameter(description = "El ID del Curso", example = "123")
    public ResponseEntity<Curso> updateCurso(@PathVariable int id, @RequestBody Curso curso) {
        if (cursoService.obtenerCursoPorId(id).isPresent()) {
            cursoService.actualizarCurso(id, curso);
            return new ResponseEntity<>(curso, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary="Eliminar Curso por ID", description = "Elimina un curso segun su ID en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna Curso"),
            @ApiResponse(responseCode = "404", description = "No hay datos disponibles")
    })
    @Parameter(description = "El ID del curso", example = "123")
    public ResponseEntity<Void> deleteCurso(@PathVariable int id) {
        if (cursoService.obtenerCursoPorId(id).isPresent()) {
            cursoService.eliminarCurso(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}