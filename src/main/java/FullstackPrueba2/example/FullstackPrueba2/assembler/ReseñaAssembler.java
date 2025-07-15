package FullstackPrueba2.example.FullstackPrueba2.assembler;

import FullstackPrueba2.example.FullstackPrueba2.controller.ReseñaController;
import FullstackPrueba2.example.FullstackPrueba2.model.Reseña;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ReseñaAssembler implements RepresentationModelAssembler<Reseña, EntityModel<Reseña>> {

    @Override
    public EntityModel<Reseña> toModel(Reseña reseña) {
        return EntityModel.of(reseña,
                linkTo(methodOn(ReseñaController.class).getReseñaById(reseña.getId())).withSelfRel(),
                linkTo(methodOn(ReseñaController.class).getAllReseñas()).withRel("reseñas"),
                linkTo(methodOn(ReseñaController.class).updateReseña(reseña.getId(), reseña)).withRel("PUT"),
                linkTo(methodOn(ReseñaController.class).deleteReseña(reseña.getId())).withRel("DELETE")
        );
    }
}
