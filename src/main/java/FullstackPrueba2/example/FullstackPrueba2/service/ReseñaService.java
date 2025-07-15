package FullstackPrueba2.example.FullstackPrueba2.service;

import FullstackPrueba2.example.FullstackPrueba2.model.Reseña;
import FullstackPrueba2.example.FullstackPrueba2.repository.ReseñaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReseñaService {

    @Autowired
    private ReseñaRepository reseñaRepository;

    public List<Reseña> listarReseñas() {
        return reseñaRepository.findAll();
    }

    public Reseña agregarReseña(Reseña reseña) {
        return reseñaRepository.save(reseña);
    }

    public Optional<Reseña> obtenerReseñaPorID(int id) {
        return reseñaRepository.findById(id);
    }

    public void actualizarReseña(int id, Reseña reseña) {
        if (reseñaRepository.existsById(id)) {
            Reseña actualizar = reseñaRepository.findById(id).get();
            actualizar.setCalificacion(reseña.getCalificacion());
            actualizar.setComentario(reseña.getComentario());
            actualizar.setFecha(reseña.getFecha());
            actualizar.setEstudiante(reseña.getEstudiante());
            actualizar.setCurso(reseña.getCurso());
            reseñaRepository.save(actualizar);
        }
    }

    public void eliminarReseña(int id) {
        reseñaRepository.deleteById(id);
    }
}
