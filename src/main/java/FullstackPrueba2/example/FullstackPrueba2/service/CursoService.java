package FullstackPrueba2.example.FullstackPrueba2.service;

import FullstackPrueba2.example.FullstackPrueba2.model.Curso;
import FullstackPrueba2.example.FullstackPrueba2.repository.CursoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CursoService {

    @Autowired
    CursoRepository cursoRepository;

    public String agregarCurso(Curso curso) {
        cursoRepository.save(curso);
        return "Curso agregado con éxito";
    }

    public String listarCursos() {
        String output = "";
        for(Curso curso:cursoRepository.findAll()){
            output += "ID Curso: "+curso.getId()+"\n";
            output += "Titulo: "+curso.getTitulo()+"\n";
            output += "Descripcion: "+curso.getDescripcion()+"\n";
        }

        if(output.isEmpty()){
            return "No hay cursos";
        } else {
            return output;
        }
    }

    public String obtenerCursoPorId(int id){
        String output="";
        if(cursoRepository.existsById(id)){
            Curso curso = cursoRepository.findById(id).get();
            output += "ID Curso: "+curso.getId()+"\n";
            output += "Titulo: "+curso.getTitulo()+"\n";
            output += "Descripcion: "+curso.getDescripcion()+"\n";
            return output;
        } else {
            return "No se encuentra el usuario";
        }
    }

    public String actualizarCurso(int id, Curso curso) {
        if (cursoRepository.existsById(id)) {
            Curso existente = cursoRepository.findById(id).get();
            existente.setTitulo(curso.getTitulo());
            existente.setDescripcion(curso.getDescripcion());
            cursoRepository.save(existente);
            return "Curso actualizado con éxito";
        } else {
            return "Curso no encontrado";
        }
    }

    public String eliminarCurso(int id) {
        if (cursoRepository.existsById(id)) {
            cursoRepository.deleteById(id);
            return "Curso eliminado con éxito";
        } else {
            return "Curso no encontrado";
        }
    }
}