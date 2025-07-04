package FullstackPrueba2.example.FullstackPrueba2.assembler;

import FullstackPrueba2.example.FullstackPrueba2.controller.EvaluacionController;
import FullstackPrueba2.example.FullstackPrueba2.model.Evaluacion;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EvaluacionModelAssembler implements RepresentationModelAssembler<Evaluacion, EntityModel<Evaluacion>> {

    @Override
    public EntityModel<Evaluacion> toModel(Evaluacion evaluacion) {
        return EntityModel.of(evaluacion,
                linkTo(methodOn(EvaluacionController.class).getEvaluacionById(evaluacion.getId())).withSelfRel(),
                linkTo(methodOn(EvaluacionController.class).getAllEvaluaciones()).withRel("evaluaciones"),
                linkTo(methodOn(EvaluacionController.class).updateEvaluacion(evaluacion.getId(), evaluacion)).withRel("PUT"),
                linkTo(methodOn(EvaluacionController.class).deleteEvaluacion(evaluacion.getId())).withRel("DELETE")
        );
    }
}
