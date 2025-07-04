package FullstackPrueba2.example.FullstackPrueba2.service;

import FullstackPrueba2.example.FullstackPrueba2.model.Incidencia;
import FullstackPrueba2.example.FullstackPrueba2.repository.IncidenciaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class IncidenciaService {

    @Autowired
    IncidenciaRepository incidenciaRepository;

    public List<Incidencia> listarIncidencias() {
        return incidenciaRepository.findAll();
    }

    public Incidencia agregarIncidencia(Incidencia incidencia) {
        return incidenciaRepository.save(incidencia);
    }

    public Optional<Incidencia> obtenerIncidenciaPorId(int id) {
        return incidenciaRepository.findById(id);
    }

    public void actualizarIncidencia(int id, Incidencia incidencia) {
        if(incidenciaRepository.existsById(id)){
            Incidencia actualizar = incidenciaRepository.findById(id).get();
            actualizar.setDescripcion(incidencia.getDescripcion());
            actualizar.setEstado(incidencia.getEstado());
            actualizar.setUsuarioReporta(incidencia.getUsuarioReporta());
            incidenciaRepository.save(actualizar);
        }
    }

    public void eliminarIncidencia(int id) {
        incidenciaRepository.deleteById(id);
    }
}