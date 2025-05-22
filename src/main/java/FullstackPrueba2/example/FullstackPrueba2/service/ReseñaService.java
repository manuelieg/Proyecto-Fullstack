package FullstackPrueba2.example.FullstackPrueba2.service;

import FullstackPrueba2.example.FullstackPrueba2.model.Reseña;
import FullstackPrueba2.example.FullstackPrueba2.repository.ReseñaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ReseñaService {

    @Autowired
    ReseñaRepository reseñaRepository;

    public String agregarReseña(Reseña reseña) {
        reseñaRepository.save(reseña);
        return "Reseña agregada con éxito";
    }

    public String listarReseñas() {
        String output = "";
        for(Reseña reseña : reseñaRepository.findAll()) {
            output += "ID Reseña: " + reseña.getId() + "\n";
            output += "Comentario: " + reseña.getComentario() + "\n";
            output += "Calificación: " + reseña.getCalificacion() + "\n";
            output += "Usuario ID: " + (reseña.getEstudiante() != null ? reseña.getEstudiante().getId() : "N/A") + "\n";
            output += "Curso ID: " + (reseña.getCurso() != null ? reseña.getCurso().getId() : "N/A") + "\n";
        }
        if(output.isEmpty()){
            return "No hay reseñas";
        } else {
            return output;
        }
    }

    public String obtenerReseñaPorID(int id) {
        String output = "";
        if(reseñaRepository.existsById(id)){
            Reseña reseña = reseñaRepository.findById(id).get();
            output += "ID Reseña: " + reseña.getId() + "\n";
            output += "Comentario: " + reseña.getComentario() + "\n";
            output += "Calificación: " + reseña.getCalificacion() + "\n";
            output += "Usuario ID: " + (reseña.getEstudiante() != null ? reseña.getEstudiante().getId() : "N/A") + "\n";
            output += "Curso ID: " + (reseña.getCurso() != null ? reseña.getCurso().getId() : "N/A") + "\n";
            return output;
        } else {
            return "No se encuentra la reseña";
        }
    }

    public String actualizarReseña(int id, Reseña reseña) {
        if(reseñaRepository.existsById(id)){
            Reseña actualizar = reseñaRepository.findById(id).get();
            actualizar.setComentario(reseña.getComentario());
            actualizar.setCalificacion(reseña.getCalificacion());
            actualizar.setEstudiante(reseña.getEstudiante());
            actualizar.setCurso(reseña.getCurso());
            reseñaRepository.save(actualizar);
            return "Reseña actualizada con éxito";
        } else {
            return "No se encuentra la reseña";
        }
    }

    public String eliminarReseña(int id) {
        if(reseñaRepository.existsById(id)){
            reseñaRepository.deleteById(id);
            return "Reseña eliminada con éxito";
        } else {
            return "No se encuentra la reseña";
        }
    }
}
