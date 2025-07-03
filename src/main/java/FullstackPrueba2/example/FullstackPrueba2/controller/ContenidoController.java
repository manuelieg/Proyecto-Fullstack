package FullstackPrueba2.example.FullstackPrueba2.controller;

import FullstackPrueba2.example.FullstackPrueba2.assembler.ContenidoModelAssembler;
import FullstackPrueba2.example.FullstackPrueba2.model.Contenido;
import FullstackPrueba2.example.FullstackPrueba2.service.ContenidoService;
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
@RequestMapping("/contenidos")
@Tag(name = "Controlador de Contenidos", description = "Servicio de gestion de contenidos")
public class ContenidoController {

    @Autowired
    private ContenidoService contenidoService;

    @Autowired
    ContenidoModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Obtener Contenidos", description = "Obtiene la lista de contenidos del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna lista de contenidos"),
            @ApiResponse(responseCode = "404", description = "No hay datos disponibles")
    })
    public ResponseEntity<CollectionModel<EntityModel<Contenido>>> getAllContenidos() {
        List<Contenido> lista = contenidoService.listarContenidos();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(assembler.toCollectionModel(lista), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Contenido por ID", description = "Obtiene contenido segun su ID en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna Contenido"),
            @ApiResponse(responseCode = "404", description = "No hay datos disponibles")
    })
    @Parameter(description = "El ID del contenido", example = "123")
    public ResponseEntity<EntityModel<Contenido>> getContenidoById(@PathVariable int id) {
        if (contenidoService.obtenerContenidoPorID(id).isPresent()) {
            Contenido contenido = contenidoService.obtenerContenidoPorID(id).get();
            return new ResponseEntity<>(assembler.toModel(contenido), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Agregar Contenido", description = "Permite registrar un contenido en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Contenido creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Contenido.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    public ResponseEntity<EntityModel<Contenido>> postContenido(@RequestBody Contenido contenido) {
        contenidoService.agregarContenido(contenido);
        if (contenidoService.obtenerContenidoPorID(contenido.getId()).isPresent()) {
            return new ResponseEntity<>(assembler.toModel(contenido), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Contenido", description = "Permite actualizar los datos de contenido segun su ID en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contenido modificado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Contenido.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    @Parameter(description = "El ID del contenido", example = "123")
    public ResponseEntity<Contenido> updateContenido(@PathVariable int id, @RequestBody Contenido contenido) {
        if (contenidoService.obtenerContenidoPorID(id).isPresent()) {
            contenidoService.actualizarContenido(id, contenido);
            return new ResponseEntity<>(contenido, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Contenido por ID", description = "Elimina un contenido segun su ID en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna Contenido"),
            @ApiResponse(responseCode = "404", description = "No hay datos disponibles")
    })
    @Parameter(description = "El ID del contenido", example = "123")
    public ResponseEntity<Void> deleteContenido(@PathVariable int id) {
        if (contenidoService.obtenerContenidoPorID(id).isPresent()) {
            contenidoService.eliminarContenido(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
