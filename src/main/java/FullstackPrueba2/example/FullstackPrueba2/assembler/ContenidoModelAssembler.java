package FullstackPrueba2.example.FullstackPrueba2.assembler;

import FullstackPrueba2.example.FullstackPrueba2.controller.ContenidoController;
import FullstackPrueba2.example.FullstackPrueba2.model.Contenido;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ContenidoModelAssembler implements RepresentationModelAssembler<Contenido, EntityModel<Contenido>> {

    @Override
    public EntityModel<Contenido> toModel(Contenido contenido) {
        return EntityModel.of(contenido,
                linkTo(methodOn(ContenidoController.class).getContenidoById(contenido.getId())).withSelfRel(),
                linkTo(methodOn(ContenidoController.class).getAllContenidos()).withRel("contenidos"),
                linkTo(methodOn(ContenidoController.class).updateContenido(contenido.getId(), contenido)).withRel("PUT"),
                linkTo(methodOn(ContenidoController.class).deleteContenido(contenido.getId())).withRel("DELETE")
        );
    }
}
