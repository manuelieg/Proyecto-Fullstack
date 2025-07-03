package FullstackPrueba2.example.FullstackPrueba2.assembler;

import FullstackPrueba2.example.FullstackPrueba2.controller.CursoController;
import FullstackPrueba2.example.FullstackPrueba2.controller.UsuarioController;
import FullstackPrueba2.example.FullstackPrueba2.model.Curso;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CursoModelAssembler implements RepresentationModelAssembler<Curso, EntityModel<Curso>> {

    @Override
    public EntityModel<Curso> toModel(Curso curso) {
        return EntityModel.of(curso,
                linkTo(methodOn(CursoController.class).getCursoById(curso.getId())).withSelfRel(),
                linkTo(methodOn(CursoController.class).getAllCursos()).withRel("cursos"),
                linkTo(methodOn(CursoController.class).updateCurso(curso.getId(), curso)).withRel("PUT"),
                linkTo(methodOn(CursoController.class).deleteCurso(curso.getId())).withRel("DELETE")
        );
    }
}