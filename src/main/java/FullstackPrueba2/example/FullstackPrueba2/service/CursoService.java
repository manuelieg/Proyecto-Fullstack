package FullstackPrueba2.example.FullstackPrueba2.service;

import FullstackPrueba2.example.FullstackPrueba2.model.Curso;
import FullstackPrueba2.example.FullstackPrueba2.repository.CursoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CursoService {

    @Autowired
    CursoRepository cursoRepository;

    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    public Curso agregarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Optional<Curso> obtenerCursoPorId(int id) {
        return cursoRepository.findById(id);
    }

    public void actualizarCurso(int id, Curso curso) {
        if (cursoRepository.existsById(id)) {
            Curso existente = cursoRepository.findById(id).get();
            existente.setTitulo(curso.getTitulo());
            existente.setDescripcion(curso.getDescripcion());
            cursoRepository.save(existente);
        }
    }

    public void eliminarCurso(int id) {
        cursoRepository.deleteById(id);
    }
}