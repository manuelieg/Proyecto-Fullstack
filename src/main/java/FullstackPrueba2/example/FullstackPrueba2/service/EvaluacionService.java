package FullstackPrueba2.example.FullstackPrueba2.service;

import FullstackPrueba2.example.FullstackPrueba2.model.Evaluacion;
import FullstackPrueba2.example.FullstackPrueba2.repository.EvaluacionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EvaluacionService {

    @Autowired
    EvaluacionRepository evaluacionRepository;

    public List<Evaluacion> listarEvaluaciones() {
        return evaluacionRepository.findAll();
    }

    public Evaluacion agregarEvaluacion(Evaluacion evaluacion) {
        return evaluacionRepository.save(evaluacion);
    }

    public Optional<Evaluacion> obtenerEvaluacionPorId(int id) {
        return evaluacionRepository.findById(id);
    }

    public void actualizarEvaluacion(int id, Evaluacion evaluacion) {
        if (evaluacionRepository.existsById(id)) {
            Evaluacion actualizar = evaluacionRepository.findById(id).get();
            actualizar.setTitulo(evaluacion.getTitulo());
            actualizar.setDescripcion(evaluacion.getDescripcion());
            actualizar.setFechaInicio(evaluacion.getFechaInicio());
            actualizar.setFechaFin(evaluacion.getFechaFin());
            actualizar.setCurso(evaluacion.getCurso());
            evaluacionRepository.save(actualizar);
        }
    }

    public void eliminarEvaluacion(int id) {
        evaluacionRepository.deleteById(id);
    }
}
