package FullstackPrueba2.example.FullstackPrueba2.service;

import FullstackPrueba2.example.FullstackPrueba2.model.Inscripcion;
import FullstackPrueba2.example.FullstackPrueba2.repository.InscripcionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InscripcionService {

    @Autowired
    InscripcionRepository inscripcionRepository;

    public List<Inscripcion> listarInscripciones() {
        return inscripcionRepository.findAll();
    }

    public Inscripcion agregarInscripcion(Inscripcion inscripcion) {
        return inscripcionRepository.save(inscripcion);
    }

    public Optional<Inscripcion> obtenerInscripcionPorId(int id) {
        return inscripcionRepository.findById(id);
    }

    public void actualizarInscripcion(int id, Inscripcion inscripcion) {
        if(inscripcionRepository.existsById(id)){
            Inscripcion actualizar = inscripcionRepository.findById(id).get();
            actualizar.setEstudiante(inscripcion.getEstudiante());
            actualizar.setCurso(inscripcion.getCurso());
            inscripcionRepository.save(actualizar);
        }
    }

    public void eliminarInscripcion(int id) {
            inscripcionRepository.deleteById(id);
    }
}
