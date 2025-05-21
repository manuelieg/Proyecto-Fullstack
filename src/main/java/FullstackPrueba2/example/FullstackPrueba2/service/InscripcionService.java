package FullstackPrueba2.example.FullstackPrueba2.service;

import FullstackPrueba2.example.FullstackPrueba2.model.Inscripcion;
import FullstackPrueba2.example.FullstackPrueba2.repository.InscripcionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class InscripcionService {

    @Autowired
    InscripcionRepository inscripcionRepository;

    public String agregarInscripcion(Inscripcion inscripcion) {
        inscripcionRepository.save(inscripcion);
        return "Inscripción agregada con éxito";
    }

    public String listarInscripciones() {
        String output = "";
        for(Inscripcion inscripcion : inscripcionRepository.findAll()){
            output += "ID Inscripción: " + inscripcion.getId() + "\n";
            output += "ID Estudiante: " + inscripcion.getEstudiante().getId() + "\n";
            output += "ID Curso: " + inscripcion.getCurso().getId() + "\n";
        }

        if(output.isEmpty()){
            return "No hay inscripciones";
        } else {
            return output;
        }
    }

    public String obtenerInscripcionPorID(int id) {
        if(inscripcionRepository.existsById(id)){
            Inscripcion inscripcion = inscripcionRepository.findById(id).get();
            String output = "ID Inscripción: " + inscripcion.getId() + "\n";
            output += "ID Estudiante: " + inscripcion.getEstudiante().getId() + "\n";
            output += "ID Curso: " + inscripcion.getCurso().getId() + "\n";
            return output;
        } else {
            return "No se encuentra la inscripción";
        }
    }

    public String actualizarInscripcion(int id, Inscripcion inscripcion) {
        if(inscripcionRepository.existsById(id)){
            Inscripcion actualizar = inscripcionRepository.findById(id).get();
            actualizar.setEstudiante(inscripcion.getEstudiante());
            actualizar.setCurso(inscripcion.getCurso());
            inscripcionRepository.save(actualizar);
            return "Inscripción actualizada con éxito";
        } else {
            return "No se encuentra la inscripción";
        }
    }

    public String eliminarInscripcion(int id) {
        if(inscripcionRepository.existsById(id)){
            inscripcionRepository.deleteById(id);
            return "Inscripción eliminada con éxito";
        } else {
            return "No se encuentra la inscripción";
        }
    }
}
