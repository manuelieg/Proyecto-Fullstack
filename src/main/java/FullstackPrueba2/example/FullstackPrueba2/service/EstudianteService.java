package FullstackPrueba2.example.FullstackPrueba2.service;

import FullstackPrueba2.example.FullstackPrueba2.model.Estudiante;
import FullstackPrueba2.example.FullstackPrueba2.repository.EstudianteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EstudianteService {

    @Autowired
    EstudianteRepository estudianteRepository;

    public String agregarEstudiante(Estudiante estudiante) {
        estudianteRepository.save(estudiante);
        return "Estudiante agregado con éxito";
    }

    public String listarEstudiantes() {
        String output = "";
        for (Estudiante estudiante : estudianteRepository.findAll()) {
            output += "ID Estudiante: " + estudiante.getId() + "\n";
            output += "Nombre: " + estudiante.getNombre() + "\n";
            output += "Apellido: " + estudiante.getApellido() + "\n";
            output += "Correo: " + estudiante.getCorreo() + "\n";
            output += "Teléfono: " + estudiante.getTelefono() + "\n";
        }

        if (output.isEmpty()) {
            return "No hay estudiantes";
        } else {
            return output;
        }
    }

    public String obtenerEstudiantePorID(int id) {
        String output = "";
        if (estudianteRepository.existsById(id)) {
            Estudiante estudiante = estudianteRepository.findById(id).get();
            output += "ID Estudiante: " + estudiante.getId() + "\n";
            output += "Nombre: " + estudiante.getNombre() + "\n";
            output += "Apellido: " + estudiante.getApellido() + "\n";
            output += "Correo: " + estudiante.getCorreo() + "\n";
            output += "Teléfono: " + estudiante.getTelefono() + "\n";
            return output;
        } else {
            return "No se encuentra estudiante";
        }
    }

    public String actualizarEstudiante(int id, Estudiante estudiante) {
        if (estudianteRepository.existsById(id)) {
            Estudiante actualizar = estudianteRepository.findById(id).get();
            actualizar.setNombre(estudiante.getNombre());
            actualizar.setApellido(estudiante.getApellido());
            actualizar.setCorreo(estudiante.getCorreo());
            actualizar.setTelefono(estudiante.getTelefono());
            estudianteRepository.save(actualizar);
            return "Estudiante actualizado con éxito";
        } else {
            return "Estudiante no encontrado";
        }
    }

    public String eliminarEstudiante(int id) {
        if (estudianteRepository.existsById(id)) {
            estudianteRepository.deleteById(id);
            return "Estudiante eliminado con éxito";
        } else {
            return "Estudiante no encontrado";
        }
    }
}