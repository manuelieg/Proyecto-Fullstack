package FullstackPrueba2.example.FullstackPrueba2.controller;

import FullstackPrueba2.example.FullstackPrueba2.assembler.CuponDescuentoAssembler;
import FullstackPrueba2.example.FullstackPrueba2.model.CuponDescuento;
import FullstackPrueba2.example.FullstackPrueba2.service.CuponDescuentoService;
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
@RequestMapping("/cupones")
@Tag(name = "Controlador de Cupones", description = "Gestión de cupones de descuento")
public class CuponDescuentoController {

    @Autowired
    private CuponDescuentoService cuponDescuentoService;

    @Autowired
    private CuponDescuentoAssembler assembler;

    @GetMapping
    @Operation(summary = "Obtener Cupones", description = "Obtiene la lista de cupones del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna lista de cupones"),
            @ApiResponse(responseCode = "404", description = "No hay datos disponibles")
    })
    public ResponseEntity<CollectionModel<EntityModel<CuponDescuento>>> getAllCuponesDescuento() {
        List<CuponDescuento> lista = cuponDescuentoService.listarCupones();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(assembler.toCollectionModel(lista), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Cupón por ID", description = "Obtiene cupón según su ID en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna Cupón"),
            @ApiResponse(responseCode = "404", description = "No hay cupón disponible con ese ID")
    })
    @Parameter(description = "El ID del cupón", example = "123")
    public ResponseEntity<EntityModel<CuponDescuento>> getCuponById(@PathVariable int id) {
        Optional<CuponDescuento> cupon = cuponDescuentoService.obtenerCuponPorID(id);
        if (cupon.isPresent()) {
            return new ResponseEntity<>(assembler.toModel(cupon.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Agregar Cupón", description = "Permite registrar un cupón de descuento en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cupón creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CuponDescuento.class))),
            @ApiResponse(responseCode = "204", description = "No hay datos en la solicitud")
    })
    public ResponseEntity<EntityModel<CuponDescuento>> postCupon(@RequestBody CuponDescuento cupon) {
        CuponDescuento creado = cuponDescuentoService.agregarCupon(cupon);
        if (creado != null && creado.getId() > 0) {
            return new ResponseEntity<>(assembler.toModel(creado), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Cupón", description = "Permite actualizar los datos de un cupón según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cupón modificado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CuponDescuento.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    @Parameter(description = "El ID del cupón", example = "123")
    public ResponseEntity<CuponDescuento> updateCuponDescuento(@PathVariable int id, @RequestBody CuponDescuento cupon) {
        if (cuponDescuentoService.obtenerCuponPorID(id).isPresent()) {
            cuponDescuentoService.actualizarCupon(id, cupon);
            return new ResponseEntity<>(cupon, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Cupón por ID", description = "Elimina un cupón según su ID en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cupón eliminado"),
            @ApiResponse(responseCode = "404", description = "No hay datos disponibles")
    })
    @Parameter(description = "El ID del cupón", example = "123")
    public ResponseEntity<Void> deleteCuponDescuento(@PathVariable int id) {
        if (cuponDescuentoService.obtenerCuponPorID(id).isPresent()) {
            cuponDescuentoService.eliminarCupon(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}