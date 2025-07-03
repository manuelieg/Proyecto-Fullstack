package FullstackPrueba2.example.FullstackPrueba2.service;

import FullstackPrueba2.example.FullstackPrueba2.model.Contenido;
import FullstackPrueba2.example.FullstackPrueba2.repository.ContenidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ContenidoService {

    @Autowired
    ContenidoRepository contenidoRepository;

    public List<Contenido> listarContenidos() {
        return contenidoRepository.findAll();
    }

    public Contenido agregarContenido(Contenido contenido) {
        return contenidoRepository.save(contenido);
    }

    public Optional<Contenido> obtenerContenidoPorID(int id) {
        return contenidoRepository.findById(id);
    }

    public void actualizarContenido(int id, Contenido contenido) {
        if(contenidoRepository.existsById(id)) {
            Contenido actualizar = contenidoRepository.findById(id).get();
            actualizar.setTitulo(contenido.getTitulo());
            actualizar.setDescripcion(contenido.getDescripcion());
            actualizar.setUrl(contenido.getUrl());
            actualizar.setCurso(contenido.getCurso());
            contenidoRepository.save(actualizar);
        }
    }

    public void eliminarContenido(int id) {
        contenidoRepository.deleteById(id);
    }
}