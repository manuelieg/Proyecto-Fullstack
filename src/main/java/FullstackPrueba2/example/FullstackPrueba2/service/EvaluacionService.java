package FullstackPrueba2.example.FullstackPrueba2.service;

import FullstackPrueba2.example.FullstackPrueba2.model.Evaluacion;
import FullstackPrueba2.example.FullstackPrueba2.repository.EvaluacionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EvaluacionService {

    @Autowired
    EvaluacionRepository evaluacionRepository;

    public String agregarEvaluacion(Evaluacion evaluacion) {
        evaluacionRepository.save(evaluacion);
        return "Evaluación agregada con éxito";
    }

    public String listarEvaluaciones() {
        String output = "";
        for (Evaluacion evaluacion : evaluacionRepository.findAll()) {
            output += "ID Evaluación: " + evaluacion.getId() + "\n";
            output += "Título: " + evaluacion.getTitulo() + "\n";
            output += "Descripción: " + evaluacion.getDescripcion() + "\n";
            output += "Fecha Inicio: " + evaluacion.getFechaInicio() + "\n";
            output += "Fecha Fin: " + evaluacion.getFechaFin() + "\n";
            output += "ID Curso: " + evaluacion.getCurso().getId() + "\n\n";
        }

        if (output.isEmpty()) {
            return "No hay evaluaciones";
        } else {
            return output;
        }
    }

    public String obtenerEvaluacionPorID(int id) {
        String output = "";
        if (evaluacionRepository.existsById(id)) {
            Evaluacion evaluacion = evaluacionRepository.findById(id).get();
            output += "ID Evaluación: " + evaluacion.getId() + "\n";
            output += "Título: " + evaluacion.getTitulo() + "\n";
            output += "Descripción: " + evaluacion.getDescripcion() + "\n";
            output += "Fecha Inicio: " + evaluacion.getFechaInicio() + "\n";
            output += "Fecha Fin: " + evaluacion.getFechaFin() + "\n";
            output += "ID Curso: " + evaluacion.getCurso().getId() + "\n";
            return output;
        } else {
            return "No se encuentra la evaluación";
        }
    }

    public String actualizarEvaluacion(int id, Evaluacion evaluacion) {
        if (evaluacionRepository.existsById(id)) {
            Evaluacion actualizar = evaluacionRepository.findById(id).get();
            actualizar.setTitulo(evaluacion.getTitulo());
            actualizar.setDescripcion(evaluacion.getDescripcion());
            actualizar.setFechaInicio(evaluacion.getFechaInicio());
            actualizar.setFechaFin(evaluacion.getFechaFin());
            actualizar.setCurso(evaluacion.getCurso());
            evaluacionRepository.save(actualizar);
            return "Evaluación actualizada con éxito";
        } else {
            return "No se encuentra la evaluación";
        }
    }

    public String eliminarEvaluacion(int id) {
        if (evaluacionRepository.existsById(id)) {
            evaluacionRepository.deleteById(id);
            return "Evaluación eliminada con éxito";
        } else {
            return "No se encuentra la evaluación";
        }
    }
}
