package FullstackPrueba2.example.FullstackPrueba2.service;

import FullstackPrueba2.example.FullstackPrueba2.model.Contenido;
import FullstackPrueba2.example.FullstackPrueba2.repository.ContenidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ContenidoService {

    @Autowired
    ContenidoRepository contenidoRepository;

    public String agregarContenido(Contenido contenido) {
        contenidoRepository.save(contenido);
        return "Contenido agregado con éxito";
    }

    public String listarContenidos() {
        String output = "";
        for(Contenido contenido : contenidoRepository.findAll()) {
            output += "ID Contenido: " + contenido.getId() + "\n";
            output += "Título: " + contenido.getTitulo() + "\n";
            output += "Descripción: " + contenido.getDescripcion() + "\n";
            output += "URL: " + contenido.getUrl() + "\n";
            output += "Curso ID: " + (contenido.getCurso() != null ? contenido.getCurso().getId() : "N/A") + "\n";
        }
        if(output.isEmpty()){
            return "No hay contenidos";
        } else {
            return output;
        }
    }

    public String obtenerContenidoPorID(int id) {
        String output = "";
        if(contenidoRepository.existsById(id)){
            Contenido contenido = contenidoRepository.findById(id).get();
            output += "ID Contenido: " + contenido.getId() + "\n";
            output += "Título: " + contenido.getTitulo() + "\n";
            output += "Descripción: " + contenido.getDescripcion() + "\n";
            output += "URL: " + contenido.getUrl() + "\n";
            output += "Curso ID: " + (contenido.getCurso() != null ? contenido.getCurso().getId() : "N/A") + "\n";
            return output;
        } else {
            return "No se encuentra el contenido";
        }
    }

    public String actualizarContenido(int id, Contenido contenido) {
        if(contenidoRepository.existsById(id)) {
            Contenido actualizar = contenidoRepository.findById(id).get();
            actualizar.setTitulo(contenido.getTitulo());
            actualizar.setDescripcion(contenido.getDescripcion());
            actualizar.setUrl(contenido.getUrl());
            actualizar.setCurso(contenido.getCurso());
            contenidoRepository.save(actualizar);
            return "Contenido actualizado con éxito";
        } else {
            return "No se encuentra el contenido";
        }
    }

    public String eliminarContenido(int id) {
        if(contenidoRepository.existsById(id)) {
            contenidoRepository.deleteById(id);
            return "Contenido eliminado con éxito";
        } else {
            return "No se encuentra el contenido";
        }
    }
}