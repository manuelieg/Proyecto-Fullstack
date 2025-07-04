package FullstackPrueba2.example.FullstackPrueba2.assembler;

import FullstackPrueba2.example.FullstackPrueba2.controller.InscripcionController;
import FullstackPrueba2.example.FullstackPrueba2.model.Inscripcion;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class InscripcionModelAssembler implements RepresentationModelAssembler<Inscripcion, EntityModel<Inscripcion>> {

    @Override
    public EntityModel<Inscripcion> toModel(Inscripcion inscripcion) {
        return EntityModel.of(inscripcion,
                linkTo(methodOn(InscripcionController.class).getInscripcionById(inscripcion.getId())).withSelfRel(),
                linkTo(methodOn(InscripcionController.class).getAllInscripciones()).withRel("inscripciones"),
                linkTo(methodOn(InscripcionController.class).updateInscripcion(inscripcion.getId(), inscripcion)).withRel("PUT"),
                linkTo(methodOn(InscripcionController.class).deleteInscripcion(inscripcion.getId())).withRel("DELETE")
        );
    }
}
