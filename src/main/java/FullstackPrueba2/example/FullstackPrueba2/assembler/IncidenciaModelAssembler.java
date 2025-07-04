package FullstackPrueba2.example.FullstackPrueba2.assembler;

import FullstackPrueba2.example.FullstackPrueba2.controller.IncidenciaController;
import FullstackPrueba2.example.FullstackPrueba2.model.Incidencia;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class IncidenciaModelAssembler implements RepresentationModelAssembler<Incidencia, EntityModel<Incidencia>> {

    @Override
    public EntityModel<Incidencia> toModel(Incidencia incidencia) {
        return EntityModel.of(incidencia,
                linkTo(methodOn(IncidenciaController.class).getIncidenciaById(incidencia.getId())).withSelfRel(),
                linkTo(methodOn(IncidenciaController.class).getAllIncidencias()).withRel("incidencias"),
                linkTo(methodOn(IncidenciaController.class).updateIncidencia(incidencia.getId(), incidencia)).withRel("PUT"),
                linkTo(methodOn(IncidenciaController.class).deleteIncidencia(incidencia.getId())).withRel("DELETE")
        );
    }
}