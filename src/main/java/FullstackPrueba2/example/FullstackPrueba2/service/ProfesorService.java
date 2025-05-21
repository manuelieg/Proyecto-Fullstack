package FullstackPrueba2.example.FullstackPrueba2.service;

import FullstackPrueba2.example.FullstackPrueba2.model.Profesor;
import FullstackPrueba2.example.FullstackPrueba2.repository.ProfesorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProfesorService {

    @Autowired
    ProfesorRepository profesorRepository;

    public String agregarProfesor(Profesor profesor) {
        profesorRepository.save(profesor);
        return "Profesor agregado con éxito";
    }

    public String listarProfesores() {
        String output = "";
        for(Profesor profesor : profesorRepository.findAll()) {
            output += "ID Profesor: " + profesor.getId() + "\n";
            output += "Nombre: " + profesor.getNombre() + "\n";
            output += "Apellido: " + profesor.getApellido() + "\n";
            output += "Correo: " + profesor.getCorreo() + "\n";
            output += "Teléfono: " + profesor.getTelefono() + "\n\n";
        }

        if(output.isEmpty()) {
            return "No hay profesores";
        } else {
            return output;
        }
    }

    public String obtenerProfesorPorID(int id) {
        if(profesorRepository.existsById(id)) {
            Profesor profesor = profesorRepository.findById(id).get();
            String output = "ID Profesor: " + profesor.getId() + "\n";
            output += "Nombre: " + profesor.getNombre() + "\n";
            output += "Apellido: " + profesor.getApellido() + "\n";
            output += "Correo: " + profesor.getCorreo() + "\n";
            output += "Teléfono: " + profesor.getTelefono() + "\n";
            return output;
        } else {
            return "No se encuentra el profesor";
        }
    }

    public String actualizarProfesor(int id, Profesor profesor) {
        if(profesorRepository.existsById(id)) {
            Profesor actualizar = profesorRepository.findById(id).get();
            actualizar.setNombre(profesor.getNombre());
            actualizar.setApellido(profesor.getApellido());
            actualizar.setCorreo(profesor.getCorreo());
            actualizar.setTelefono(profesor.getTelefono());
            profesorRepository.save(actualizar);
            return "Profesor actualizado con éxito";
        } else {
            return "No se encuentra el profesor";
        }
    }

    public String eliminarProfesor(int id) {
        if(profesorRepository.existsById(id)) {
            profesorRepository.deleteById(id);
            return "Profesor eliminado con éxito";
        } else {
            return "No se encuentra el profesor";
        }
    }
}