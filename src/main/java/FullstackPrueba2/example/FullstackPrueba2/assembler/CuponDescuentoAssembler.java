package FullstackPrueba2.example.FullstackPrueba2.assembler;

import FullstackPrueba2.example.FullstackPrueba2.controller.CuponDescuentoController;
import FullstackPrueba2.example.FullstackPrueba2.model.CuponDescuento;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CuponDescuentoAssembler implements RepresentationModelAssembler<CuponDescuento, EntityModel<CuponDescuento>> {

    @Override
    public EntityModel<CuponDescuento> toModel(CuponDescuento cuponDescuento) {
        return EntityModel.of(cuponDescuento,
                linkTo(methodOn(CuponDescuentoController.class).getCuponById(cuponDescuento.getId())).withSelfRel(),
                linkTo(methodOn(CuponDescuentoController.class).getAllCuponesDescuento()).withRel("cupones"),
                linkTo(methodOn(CuponDescuentoController.class).updateCuponDescuento(cuponDescuento.getId(), cuponDescuento)).withRel("PUT"),
                linkTo(methodOn(CuponDescuentoController.class).deleteCuponDescuento(cuponDescuento.getId())).withRel("DELETE")
        );
    }
}
